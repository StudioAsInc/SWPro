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

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.dialog.DialogUtils;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.git.DayDreamGitCloneActivity;
import extensions.anbui.daydream.library.LibraryUtils;
import extensions.anbui.daydream.tool.ToolCore;
import pro.sketchware.R;
import pro.sketchware.databinding.ActivityDaydreamToolsBinding;

public class DayDreamTools extends AppCompatActivity {
    private ActivityDaydreamToolsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDaydreamToolsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        binding.lnCleanuptemporaryfiles.setOnClickListener(v -> cleanUpTemporaryFiles());
        binding.lnCleanuplocallibrary.setOnClickListener(v -> cleanUpLocalLib());
        binding.lnCleanouttherecyclingbin.setOnClickListener(v -> cleanOutTheRecyclingBin());

        if (LibraryUtils.isAllowUseGit()){
            binding.lnGitclone.setOnClickListener(v -> {
                Intent intent = new Intent(this, DayDreamGitCloneActivity.class);
                startActivity(intent);
            });
        } else {
            binding.tvGitclonenote.setText("Android 13+ required to use this feature.");
            binding.lnGitclone.setAlpha(0.5f);
            binding.lnGitclone.setEnabled(false);
        }
    }

    private void cleanUpLocalLib() {
        DialogUtils.twoDialog(DayDreamTools.this,
                "Clean up local library",
                "Find and move unused local libraries to the recycle bin.",
                "Clean up",
                "Cancel",
                true,
                R.drawable.ic_mtrl_box,
                true,
                this::startleanUpLocalLib, null, null);
    }

    private void startleanUpLocalLib() {
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
            String message;
            int cleared = ToolCore.cleanupLocalLib();
            if (cleared > 0)
                message = "Cleaned up " + cleared + " local libraries. And those libraries have been moved to"
                        + FileUtils.getInternalStorageDir() + Configs.recycleBinDayDreamFolderDir + "local_libs.";
            else {
                message = "No libraries need to be cleaned.";
            }

            runOnUiThread(() -> {
                progressDialog.dismiss();
                DialogUtils.oneDialog(DayDreamTools.this,
                        "Done",
                        message,
                        "OK",
                        true,
                        R.drawable.ic_mtrl_check,
                        true, null, null);
            });
        }).start();
    }

    private void cleanOutTheRecyclingBin() {
        DialogUtils.twoDialog(DayDreamTools.this,
                "Clean up the recycle bin",
                "All files in the recycle bin will be permanently deleted.",
                "Clean up",
                "Cancel",
                true,
                R.drawable.ic_mtrl_delete,
                true,
                this::startcleanOutTheRecyclingBin, null, null);
    }

    private void startcleanOutTheRecyclingBin() {
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
            ToolCore.cleanOutTheRecyclingBin();

            runOnUiThread(() -> {
                progressDialog.dismiss();
                DialogUtils.oneDialog(DayDreamTools.this,
                        "Done",
                        "Cleaned the recycle bin.",
                        "OK",
                        true,
                        R.drawable.ic_mtrl_check,
                        true, null, null);
//                binding.lnCleanouttherecyclingbin.setVisibility(View.GONE);
            });
        }).start();
    }

    private void cleanUpTemporaryFiles() {
        DialogUtils.twoDialog(DayDreamTools.this,
                "Clean up temporary files",
                "Clean up temporary files generated during build in all projects to free up storage space.",
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
            String message;
            int cleared = ToolCore.cleanUpTemporaryFiles();
            if (cleared > 0)
                message = "Cleaned up temporary files in " + cleared + " projects.";
            else {
                message = "There is nothing to clean up.";
            }
            runOnUiThread(() -> {
                progressDialog.dismiss();
                DialogUtils.oneDialog(DayDreamTools.this,
                        "Done",
                        message,
                        "OK",
                        true,
                        R.drawable.ic_mtrl_check,
                        true, null, null);
            });
        }).start();
    }
}