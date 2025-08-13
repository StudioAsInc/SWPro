package extensions.anbui.daydream.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.dialog.DialogUtils;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.tool.DayDreamTool;
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
        binding.lnCleanuplocallibrary.setOnClickListener(v -> cleanUpLocalLib());
        binding.lnCleanouttherecyclingbin.setOnClickListener(v -> cleanOutTheRecyclingBin());
    }

    private void cleanUpLocalLib() {
        DialogUtils.twoDialog(DayDreamTools.this,
                "Clean up local library",
                "Find and move unused local libraries to the recycling bin.",
                "Clean up",
                "Cancel",
                true,
                R.drawable.ic_mtrl_box,
                true,
                this::startleanUpLocalLib, null, null);
    }

    private void startleanUpLocalLib() {
        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Cleaning up...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            String message;
            int cleared = DayDreamTool.cleanupLocalLib();
            if (cleared > 0)
                message = "Cleaned up " + cleared + " local libraries. And those libraries have been moved to"
                        + FileUtils.getInternalStorageDir() + Configs.recycleBinDayDreamFolderDir + "local_library.";
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
                "Clean out the recycling bin",
                "All files in the recycling bin will be permanently deleted.",
                "Clean out",
                "Cancel",
                true,
                R.drawable.ic_mtrl_delete,
                true,
                this::startcleanOutTheRecyclingBin, null, null);
    }

    private void startcleanOutTheRecyclingBin() {
        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Cleaning up...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            DayDreamTool.cleanOutTheRecyclingBin();

            runOnUiThread(() -> {
                progressDialog.dismiss();
                DialogUtils.oneDialog(DayDreamTools.this,
                        "Done",
                        "Cleaned the recycling bin.",
                        "OK",
                        true,
                        R.drawable.ic_mtrl_check,
                        true, null, null);
                binding.lnCleanouttherecyclingbin.setVisibility(View.GONE);
            });
        }).start();
    }
}