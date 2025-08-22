package extensions.anbui.daydream.git;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.dialog.DialogUtils;
import extensions.anbui.daydream.file.FileUtils;
import pro.sketchware.R;
import pro.sketchware.databinding.ActivityDaydreamGitConfigsBinding;

public class DayDreamGitConfigsActivity extends AppCompatActivity {

    public static final String TAG = "DayDreamGitConfigsActivity";
    private String projectID;
    private ActivityDaydreamGitConfigsBinding binding;
    private boolean isNew = false;

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
                if (isChanged()) {
                    startSwitch();
                } else {
                    saveData();
                    if (isNew) startActivity(new Intent(this, DayDreamGitActionsActivity.class).putExtra("sc_id", projectID));
                    onBackPressed();
                }
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
        binding = ActivityDaydreamGitConfigsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        if (!FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/DataDayDreamGit.json"))
            isNew = true;

        binding.etToken.setText(DayDreamGitConfigs.getGitHubToken(projectID));
        binding.etRurl.setText(DayDreamGitConfigs.getRemoteUrl(projectID));
        binding.etBranch.setText(DayDreamGitConfigs.getBranch(projectID));
    }

    private void saveData() {
        DayDreamGitConfigs.setGitHubToken(projectID, Objects.requireNonNull(binding.etToken.getText()).toString());
        DayDreamGitConfigs.setRemoteUrl(projectID, Objects.requireNonNull(binding.etRurl.getText()).toString());
        DayDreamGitConfigs.setBranch(projectID, Objects.requireNonNull(binding.etBranch.getText()).toString());
    }

    private boolean isChanged() {
        if (!FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID))
            return false;

        if (!Objects.requireNonNull(binding.etToken.getText()).toString().equals(DayDreamGitConfigs.getGitHubToken(projectID)))
            return true;
        if (!Objects.requireNonNull(binding.etRurl.getText()).toString().equals(DayDreamGitConfigs.getRemoteUrl(projectID)))
            return true;
        if (!Objects.requireNonNull(binding.etBranch.getText()).toString().equals(DayDreamGitConfigs.getBranch(projectID)))
            return true;
        return false;
    }

    private void startSwitch() {
        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0,0,0,0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Switching...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            boolean result = GitUtils.startSwitch(projectID,
                    Objects.requireNonNull(binding.etRurl.getText()).toString(),
                    Objects.requireNonNull(binding.etToken.getText()).toString(),
                    Objects.requireNonNull(binding.etBranch.getText()).toString());

            runOnUiThread(() -> {
                progressDialog.dismiss();
                if (result) {
                    saveData();
                    onBackPressed();
                } else {
                    startCloneProject();
                }
            });
        }).start();
    }

    private void startCloneProject() {

        try {
            FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID));
        } catch (Exception e) {
            Log.e(TAG, "startCloneProject: " + e.getMessage());
        }

        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0, 0, 0, 0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Trying again...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            boolean result = GitUtils.startClone(projectID,
                    Objects.requireNonNull(binding.etRurl.getText()).toString(),
                    Objects.requireNonNull(binding.etToken.getText()).toString(),
                    Objects.requireNonNull(binding.etBranch.getText()).toString());

            runOnUiThread(() -> {
                progressDialog.dismiss();
                if (result) {
                    saveData();
                    onBackPressed();
                } else {
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Cannot switch, current information is not saved. Please try again later.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }
            });
        }).start();
    }
}