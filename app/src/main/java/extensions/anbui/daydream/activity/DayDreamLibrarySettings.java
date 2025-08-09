package extensions.anbui.daydream.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.library.LibraryUtils;
import extensions.anbui.daydream.project.ProjectDataBuildConfig;
import extensions.anbui.daydream.project.ProjectDataConfig;
import extensions.anbui.daydream.project.ProjectDataDayDream;
import extensions.anbui.daydream.project.ProjectDataLibrary;

import pro.sketchware.databinding.ActivityDaydreamLibrarySettingsBinding;

public class DayDreamLibrarySettings extends AppCompatActivity {

    private String projectID;
    private ActivityDaydreamLibrarySettingsBinding binding;

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
        binding = ActivityDaydreamLibrarySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        binding.swForceaddworkmanager.setChecked(ProjectDataDayDream.isForceAddWorkManager(projectID));
        binding.swForceaddworkmanager.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setForceAddWorkManager(projectID, isChecked));
        binding.swUseandroixbrowser.setChecked(ProjectDataDayDream.isUniversalUseAndroidXBrowser(projectID));

        binding.swUsemedia3.setChecked((ProjectDataDayDream.isUniversalUseMedia3(projectID)));
        binding.swUsemedia3.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalUseMedia3(projectID, isChecked));
        binding.swUseandroixbrowser.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalUseAndroidXBrowser(projectID, isChecked));


        binding.lnForceaddworkmanager.setOnClickListener(v -> binding.swForceaddworkmanager.toggle());
        binding.lnUsemedia3.setOnClickListener(v -> binding.swUsemedia3.toggle());
        binding.lnUseandroixbrowser.setOnClickListener(v -> binding.swUseandroixbrowser.toggle());


        initializeForceAddWorkmanager();
        initializeUseMedia3();
        initializeUseAndroidXBrowser();
    }

    private void initializeForceAddWorkmanager() {
        if (!LibraryUtils.isAllowUseAndroidXWorkManager(Configs.currentProjectID)) {
            binding.lnForceaddworkmanager.setEnabled(false);
            binding.lnForceaddworkmanager.setAlpha(0.5f);
            binding.tvForceaddworkmanagernote.setText("To use, enable AppCompat. " + binding.tvForceaddworkmanagernote.getText().toString());
        }
    }

    private void initializeUseMedia3() {
        boolean finalstatus = true;
        if (!ProjectDataConfig.isMinSDKNewerThan23(projectID)) {
            finalstatus = false;
            binding.tvUsemedia3note.setText("To use, min SDK required is 24 or newer (Android 7+). " + binding.tvUsemedia3note.getText().toString());
        } else if (!ProjectDataLibrary.isEnabledAppCompat(projectID)) {
            finalstatus = false;
            binding.tvUsemedia3note.setText("To use, enable AppCompat. " + binding.tvUsemedia3note.getText().toString());
        } else if (ProjectDataBuildConfig.isUseJava7(projectID)) {
            finalstatus = false;
            binding.tvUsemedia3note.setText("To use, use a newer version of Java. " + binding.tvUsemedia3note.getText().toString());
        }

        binding.lnUsemedia3.setEnabled(finalstatus);
        binding.lnUsemedia3.setAlpha(finalstatus ? 1 : 0.5f);
    }

    private void initializeUseAndroidXBrowser() {
        if (!LibraryUtils.isAllowUseAndroidXWorkManager(Configs.currentProjectID)) {
            binding.lnUseandroixbrowser.setEnabled(false);
            binding.lnUseandroixbrowser.setAlpha(0.5f);
            binding.tvUseandroixbrowsernote.setText("To use, enable AppCompat. " + binding.tvForceaddworkmanagernote.getText().toString());
        }
    }
}