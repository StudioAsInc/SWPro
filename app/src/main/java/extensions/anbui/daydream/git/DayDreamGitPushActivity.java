package extensions.anbui.daydream.git;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.dialog.DialogUtils;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.project.GetProjectInfo;
import extensions.anbui.daydream.settings.SkSettings;
import extensions.anbui.daydream.tool.BackupProjectToolCore;
import mod.hey.studios.util.Helper;
import pro.sketchware.R;
import pro.sketchware.databinding.ActivityDaydreamBackupToolBinding;
import pro.sketchware.databinding.ActivityDaydreamGitPushBinding;

public class DayDreamGitPushActivity extends AppCompatActivity {

    private ActivityDaydreamGitPushBinding binding;
    private String projectID;
    private boolean pushProject = true;
    private boolean pushSourceCode = true;
    private boolean isDiff = false;
    private boolean isDiffChecked = false;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(1, 1, 1, getString(R.string.common_word_create)).setShortcut('3', 'c').setIcon(R.drawable.ic_mtrl_check).setShowAsAction(1);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return switch (item.getItemId()) {
            case 1 -> {
                pushProject();
                yield true;
            }
            case android.R.id.home -> {
                onBackPressed();
                yield true;
            }
            default -> super.onOptionsItemSelected(item);
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("sc_id")) {
            projectID = getIntent().getStringExtra("sc_id");
        } else {
            finish();
            return;
        }
        EdgeToEdge.enable(this);
        binding = ActivityDaydreamGitPushBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + "/.git"))
            finish();
        else
            checkDiff();
    }

    private void initialize() {
        binding.swGradlefiles.setChecked(!FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitSourceFolderName + "build.gradle"));

        binding.lnGradlefiles.setOnClickListener(v -> binding.swGradlefiles.toggle());
        binding.lnLocallibraries.setOnClickListener(v -> binding.swLocallibraries.toggle());
        binding.lnCustomblocks.setOnClickListener(v -> binding.swCustomblocks.toggle());
        binding.lnApis.setOnClickListener(v -> binding.swApis.toggle());

        String[] options = new String[] {"All", "Project only", "Source code only"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                options
        );


        binding.filledExposedDropdown.setAdapter(adapter);

        binding.filledExposedDropdown.setOnItemClickListener((parent, view, position, id) -> {
            switch ((String) parent.getItemAtPosition(position)) {
                case "Project only":
                    pushProject = true;
                    pushSourceCode = false;
                    binding.lnGradlefiles.setVisibility(View.GONE);
                    binding.lnLocallibraries.setVisibility(View.VISIBLE);
                    binding.lnCustomblocks.setVisibility(View.VISIBLE);
                    break;
                case "Source code only":
                    pushProject = false;
                    pushSourceCode = true;
                    binding.lnGradlefiles.setVisibility(View.VISIBLE);
                    binding.lnLocallibraries.setVisibility(View.GONE);
                    binding.lnCustomblocks.setVisibility(View.GONE);
                    break;
                default:
                    pushProject = true;
                    pushSourceCode = true;
                    binding.lnGradlefiles.setVisibility(View.VISIBLE);
                    binding.lnLocallibraries.setVisibility(View.VISIBLE);
                    binding.lnCustomblocks.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }

    private void checkDiff() {
        new Thread(() -> {
            isDiff = GitUtils.quickGetDiff(projectID);

            runOnUiThread(() -> {
                if (isDiff)
                    binding.cvNeedpull.setVisibility(View.VISIBLE);
                else
                    binding.cvNeedpull.setVisibility(View.GONE);

                isDiffChecked = true;
            });
        }).start();
    }

    private void pushProject() {
        if (isDiff || !isDiffChecked) {
            startPullProject();
        } else {
            startPushProject();
        }
    }

    private void startPushProject() {
        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0,0,0,0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Pushing...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            boolean result = GitUtils.push(this, projectID, progress_text, Objects.requireNonNull(binding.edTitle.getText()).toString(),  Objects.requireNonNull(binding.edDescription.getText()).toString(), binding.swLocallibraries.isChecked(), binding.swCustomblocks.isChecked(), binding.swApis.isChecked(), pushProject, pushSourceCode, binding.swKeepoldfiles.isChecked(), binding.swGradlefiles.isChecked());

            runOnUiThread(() -> {
                if (result) {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Done",
                            "Pushed to your GitHub repository.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_check,
                            true, this::finish, this::finish);
                } else {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Error",
                            "There was an error pushing to your GitHub repository. Please try again later.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }
                checkDiff();
            });
        }).start();
    }

    private void startPullProject() {
        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0,0,0,0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Pulling...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            boolean result = GitUtils.pull(projectID);

            runOnUiThread(() -> {
                progressDialog.dismiss();
                if (result) {
                    startPushProject();
                } else {
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Unable to pull from your GitHub repository. Please try again later.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }
                checkDiff();
            });
        }).start();
    }
}