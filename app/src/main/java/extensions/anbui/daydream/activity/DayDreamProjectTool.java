package extensions.anbui.daydream.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

import extensions.anbui.daydream.dialog.DialogUtils;
import extensions.anbui.daydream.git.DayDreamGitActionsActivity;
import extensions.anbui.daydream.library.LibraryUtils;
import extensions.anbui.daydream.tool.ProjectToolCore;
import pro.sketchware.R;
import pro.sketchware.activities.main.activities.MainActivity;
import pro.sketchware.databinding.ActivityDaydreamProjectToolBinding;

public class DayDreamProjectTool extends AppCompatActivity {

    private ActivityDaydreamProjectToolBinding binding;
    private String projectID;

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
        binding = ActivityDaydreamProjectToolBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        binding.lnBackup.setOnClickListener(v -> backup());
        binding.lnClone.setOnClickListener(v -> cloneProject());
        binding.lnCleanuptemporaryfiles.setOnClickListener(v -> cleanUpTemporaryFiles());

        if (LibraryUtils.isAllowUseGit()) {
            binding.lnGit.setOnClickListener(v -> {
                Intent intent = new Intent(this, DayDreamGitActionsActivity.class);
                intent.putExtra("sc_id", projectID);
                startActivity(intent);
            });
        } else {
            binding.tvGitnote.setText("Android 13+ required to use this feature.");
            binding.lnGit.setAlpha(0.5f);
            binding.lnGit.setEnabled(false);
        }
    }

    private void backup() {
        Intent intent = new Intent(this, DayDreamBackupTool.class);
        intent.putExtra("sc_id", projectID);
        startActivity(intent);
    }

    private void cloneProject() {
        DialogUtils.twoDialog(this,
                "Clone",
                "Clone your project.",
                "Clone",
                "Cancel",
                true,
                R.drawable.content_copy_24px,
                true,
                this::startCloneProject, null, null);
    }

    private void startCloneProject() {
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
            boolean result = ProjectToolCore.clone(projectID);
            runOnUiThread(() -> {
                if (result) {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Done",
                            "Cloned your project.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_check,
                            true, null, null);

                    MainActivity.needRefreshProjectList = true;
                } else {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Unable to clone your project.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }

            });
        }).start();
    }

    private void cleanUpTemporaryFiles() {
        DialogUtils.twoDialog(this,
                "Clean up temporary files",
                "Clean up temporary files generated during build to free up storage space.",
                "Clean up",
                "Cancel",
                true,
                R.drawable.ic_mtrl_android,
                true,
                this::startcleanUpTemporaryFiles, null, null);
    }

    private void startcleanUpTemporaryFiles() {
        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0,0,0,0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Cleaning up...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            boolean result = ProjectToolCore.cleanTemporaryFiles(projectID);
            runOnUiThread(() -> {
                if (result) {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Done",
                            "Cleaned up temporary files.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_check,
                            true, null, null);
                } else {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Unable to clean up temporary files.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                }

            });
        }).start();
    }
}