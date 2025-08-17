package extensions.anbui.daydream.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.io.File;
import java.util.Objects;

import extensions.anbui.daydream.dialog.DialogUtils;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.project.GetProjectInfo;
import extensions.anbui.daydream.settings.SkSettings;
import extensions.anbui.daydream.tool.BackupProjectToolCore;
import mod.hey.studios.util.Helper;
import pro.sketchware.R;
import pro.sketchware.databinding.ActivityDaydreamBackupToolBinding;

public class DayDreamBackupTool extends AppCompatActivity {

    private ActivityDaydreamBackupToolBinding binding;
    private String projectID;

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
                startBackup();
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
        binding = ActivityDaydreamBackupToolBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        initialize();
    }

    private void initialize() {
        binding.lnLocallibraries.setOnClickListener(v -> binding.swLocallibraries.toggle());
        binding.lnCustomblocks.setOnClickListener(v -> binding.swCustomblocks.toggle());
        binding.lnApis.setOnClickListener(v -> binding.swApis.toggle());

        binding.edBackupfilename.setText(GetProjectInfo.getProjectName(projectID) + "-" + GetProjectInfo.getVersionName(projectID) + "-" + GetProjectInfo.getVersionCode(projectID) + "-" + System.currentTimeMillis() / 1000L);
    }

    private void startBackup () {
        if (!Objects.requireNonNull(binding.edBackupfilename.getText()).toString().isEmpty()) {
            if (FileUtils.isFileExist(FileUtils.getInternalStorageDir() + SkSettings.getBackupDir() + GetProjectInfo.getProjectName(projectID) + "/" + Objects.requireNonNull(binding.edBackupfilename.getText()).toString() + ".swb")) {
                DialogUtils.oneDialog(this,
                        "Unable to backup",
                        "This name has already been used for one of your previous backups. Please use a different name.",
                        "OK",
                        true,
                        R.drawable.ic_mtrl_warning,
                        true, null, null);
                return;
            }
        }

        View progressView = LayoutInflater.from(this).inflate(R.layout.progress_msg_box, null);
        LinearLayout linear_progress = progressView.findViewById(R.id.layout_progress);
        linear_progress.setPadding(0,0,0,0);
        TextView progress_text = progressView.findViewById(R.id.tv_progress);
        progress_text.setText("Backing up...");
        AlertDialog progressDialog = new MaterialAlertDialogBuilder(this)
                .setView(progressView)
                .setCancelable(false)
                .create();
        progressDialog.show();

        new Thread(() -> {
            if (BackupProjectToolCore.backup(projectID, progress_text, Objects.requireNonNull(binding.edBackupfilename.getText()).toString(), binding.swLocallibraries.isChecked(), binding.swCustomblocks.isChecked(), binding.swApis.isChecked())) {
                runOnUiThread(() -> {
                    progressDialog.dismiss();
                    DialogUtils.threeDialog(this,
                            "Done",
                            "Saved in " + BackupProjectToolCore.backedupFilePath + ".",
                            "OK",
                            "Exit",
                            "Share",
                            true,
                            R.drawable.ic_mtrl_check,
                            true, null, this::finish, this::share, null);
                });
            } else {
                runOnUiThread(() -> {
                    progressDialog.dismiss();
                    DialogUtils.oneDialog(this,
                            "Error",
                            "Something went wrong and could not be backed up.",
                            "OK",
                            true,
                            R.drawable.ic_mtrl_warning,
                            true, null, null);
                });
            }
        }).start();
    }

    private void share() {
        if (BackupProjectToolCore.backedupFilePath.isEmpty()) {
            DialogUtils.oneDialog(this,
                    "Cannot share",
                    "File does not exist.",
                    "OK",
                    true,
                    R.drawable.ic_mtrl_warning,
                    true, null, null);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_SUBJECT, "This is my project.");
        intent.putExtra(Intent.EXTRA_TEXT, Uri.parse(BackupProjectToolCore.backedupFilePath).getLastPathSegment());
        intent.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", new File(BackupProjectToolCore.backedupFilePath)));
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(Intent.createChooser(intent, Helper.getResString(R.string.myprojects_export_src_chooser_title_email)));
    }
}