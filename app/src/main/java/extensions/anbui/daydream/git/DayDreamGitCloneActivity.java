package extensions.anbui.daydream.git;

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
import extensions.anbui.daydream.tool.ToolCore;
import pro.sketchware.R;
import pro.sketchware.activities.main.activities.MainActivity;
import pro.sketchware.databinding.ActivityDaydreamGitCloneBinding;

public class DayDreamGitCloneActivity extends AppCompatActivity {

    private String TAG = Configs.universalTAG + "DayDreamGitCloneActivity";
    private String projectID;
    private ActivityDaydreamGitCloneBinding binding;

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
                projectID = String.valueOf(ToolCore.getLastID() + 1);
                startCloneProject();
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
        EdgeToEdge.enable(this);
        binding = ActivityDaydreamGitCloneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void startCloneProject() {

        DayDreamGitConfigs.setGitHubToken(projectID, "");
        DayDreamGitConfigs.setRemoteUrl(projectID, Objects.requireNonNull(binding.etRurl.getText()).toString());
        DayDreamGitConfigs.setBranch(projectID, Objects.requireNonNull(binding.etBranch.getText()).toString());

        try {
            FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID));
        } catch (Exception e) {
            Log.e(TAG, "startCloneProject: " + e.getMessage());
        }

        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0, 0, 0, 0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Cloning...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            boolean result = GitUtils.startClone(projectID,
                    Objects.requireNonNull(binding.etRurl.getText()).toString(),
                    "",
                    Objects.requireNonNull(binding.etBranch.getText()).toString());

            runOnUiThread(() -> {
                progressDialog.dismiss();
                if (result) {
                    startApplyProject();
                } else {
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Unable to clone from GitHub repository. Please try again later.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }
            });
        }).start();
    }

    private void startApplyProject() {
        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0,0,0,0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Creating project...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            boolean result = GitApplyUtils.apply(projectID, progress_text);

            runOnUiThread(() -> {
                progressDialog.dismiss();
                if (result) {
                    DialogUtils.oneDialog(this,
                            "Done",
                            "Project created successfully.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_check,
                            true, null, null);

                    MainActivity.needRefreshProjectList = true;
                } else {
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Unable to create project.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }
                GitUtils.remove(projectID);
            });
        }).start();
    }
}