package extensions.anbui.daydream.git;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.besome.sketch.design.DesignActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.dialog.DialogUtils;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.file.FilesTools;
import mod.hey.studios.project.ProjectTracker;
import pro.sketchware.R;
import pro.sketchware.databinding.ActivityDaydreamGitActionsBinding;

public class DayDreamGitActionsActivity extends AppCompatActivity {

    private String TAG = Configs.universalTAG + "DayDreamGitActionsActivity";
    private String projectID;
    private ActivityDaydreamGitActionsBinding binding;
    private boolean isDiff = false;
    private boolean isDiffChecked = false;
    private boolean isCompleteAnAction = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("sc_id")) {
            projectID = getIntent().getStringExtra("sc_id");

            if (DayDreamGitConfigs.getGitHubToken(projectID).isEmpty()) {
                Intent intent = new Intent(this, DayDreamGitConfigsActivity.class);
                intent.putExtra("sc_id", projectID);
                startActivity(intent);
                finish();
                return;
            }
        } else {
            finish();
            return;
        }
        EdgeToEdge.enable(this);
        binding = ActivityDaydreamGitActionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        uiController(true);
    }

    private void initialize() {
        binding.lnChangeinfo.setOnClickListener(v -> {
            Intent intent = new Intent(this, DayDreamGitConfigsActivity.class);
            intent.putExtra("sc_id", projectID);
            startActivity(intent);
        });

        binding.lnNeedpull.setOnClickListener(v -> pullProject());
        binding.lnClone.setOnClickListener(v -> cloneProject());
        binding.lnPush.setOnClickListener(v -> pushProject());
//        binding.lnPull.setOnClickListener(v -> pullProject());
        binding.lnApply.setOnClickListener(v -> applyProject());
        binding.lnRemove.setOnClickListener(v -> removeLink());
        binding.lnExplorer.setOnClickListener(v -> FilesTools.openFolder(this, FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID));
    }

    private void uiController(boolean showDialog) {
        boolean isReady = FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + "/.git");
        binding.lnClone.setVisibility(isReady ? View.GONE : View.VISIBLE);
//        binding.lnPull.setVisibility(isReady ? View.VISIBLE : View.GONE);
        binding.lnPush.setVisibility(isReady ? View.VISIBLE : View.GONE);
        binding.lnApply.setVisibility(isReady ? View.VISIBLE : View.GONE);
        binding.lnExplorer.setVisibility(isReady ? View.VISIBLE : View.GONE);
        binding.lnRemove.setVisibility(isReady ? View.VISIBLE : View.GONE);

        if (!isReady) {
            if (showDialog)
               cloneProject();
        } else {
            checkDiff();
        }
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

                if (getIntent().hasExtra("action") && !isCompleteAnAction) {
                    isCompleteAnAction = true;
                    String action = getIntent().getStringExtra("action");
                    assert action != null;
                    switch (action) {
                        case "pull" -> pullProject();
                        case "apply" -> applyProject();
                        case "pullapply" -> startPullProject(true);
                    }
                }
            });
        }).start();
    }

    private void cloneProject() {
        DialogUtils.twoDialog(this,
                "Clone",
                "Clone your GitHub repository to your device.",
                "Clone",
                "Cancel",
                true,
                R.drawable.ic_mtrl_download,
                true,
                this::startCloneProject, null, null);
    }

    private void startCloneProject() {
        try {
        FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID));
        } catch (Exception e) {
            Log.e(TAG, "startCloneProject: " + e.getMessage());
        }

        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0,0,0,0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Cloning...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            boolean result = GitUtils.clone(projectID);

            runOnUiThread(() -> {
                if (result) {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Done",
                            "Your GitHub repository has been cloned to your device.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_check,
                            true, null, null);
                } else {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Unable to clone your GitHub repository. Please try again later.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }

                uiController(false);
            });
        }).start();
    }

    private void pushProject() {
        Intent intent = new Intent(this, DayDreamGitPushActivity.class);
        intent.putExtra("sc_id", projectID);
        startActivity(intent);
    }

    private void pullProject() {
        DialogUtils.twoDialog(this,
                "Pull",
                "Pull changes from the GitHub repository down to the cloned repository on your device.",
                "Pull",
                "Cancel",
                true,
                R.drawable.ic_mtrl_download,
                true,
                () -> startPullProject(false), null, null);
    }

    private void startPullProject(boolean isApplyAfterPull) {
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
                    if (isApplyAfterPull) {
                        startApplyProject();
                    } else {
                        DialogUtils.twoDialog(this,
                                "Done",
                                "Pulled from your GitHub repository to the cloned repository on the device. Apply to your project now?",
                                "Apply",
                                "Later",
                                true,
                                R.drawable.ic_mtrl_check,
                                true, this::startApplyProject, null, null);
                    }
                } else {
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Unable to pull from GitHub repository. Please try again later.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }
                uiController(false);
            });
        }).start();
    }

    private void applyProject() {
        if (isDiff && !isDiffChecked) {
            DialogUtils.twoDialog(this,
                    "Apply",
                    "Apply changes from the cloned repository on your device to your project. You should pull before applying so that your project has new updates from the GitHub repository.",
                    "Pull and apply",
                    "Skip and apply",
                    true,
                    R.drawable.sync_24px,
                    true,
                    () -> startPullProject(true), this::startApplyProject, null);
        } else {
            DialogUtils.twoDialog(this,
                    "Apply",
                    "Apply changes from the cloned repository on your device to your project.",
                    "Apply",
                    "Cancel",
                    true,
                    R.drawable.sync_24px,
                    true,
                    this::startApplyProject, null, null);
        }
    }

    private void startApplyProject() {
        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0,0,0,0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Applying...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            boolean result = GitApplyUtils.apply(projectID, progress_text);

            runOnUiThread(() -> {
                if (result) {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Done",
                            "Applied to your project.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_check,
                            true, null, this::afterApply);
                } else {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Not applicable to your project. Please try again later.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }
            });
        }).start();
    }

    private void afterApply() {
        if (getIntent().hasExtra("action")) {
            if (Objects.equals(getIntent().getStringExtra("action"), "pullapply")) {
                Intent intent = new Intent(this, DesignActivity.class);
                ProjectTracker.setScId(projectID);
                intent.putExtra("sc_id", projectID);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        }
    }

    private void removeLink() {
        DialogUtils.twoDialog(this,
                "Remove",
                "Remove the cloned repository on your device and the GitHub settings for this project.",
                "Remove",
                "Cancel",
                true,
                R.drawable.ic_mtrl_close,
                true,
                this::startRemove, null, null);
    }

    private void startRemove() {
        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0,0,0,0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Removing...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            DayDreamGitConfigs.deleteConfigs(projectID);
            try {
                FilesTools.deleteFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID));
            } catch (Exception e) {
                Log.e(TAG, "startRemove: " + e.getMessage());
            }

            runOnUiThread(() -> {
                progressDialog.dismiss();
                Intent intent = new Intent(this, DayDreamGitConfigsActivity.class);
                intent.putExtra("sc_id", projectID);
                startActivity(intent);
                finish();
            });
        }).start();
    }
}