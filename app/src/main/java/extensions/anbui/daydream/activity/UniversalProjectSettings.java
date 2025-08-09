package extensions.anbui.daydream.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

import java.util.Objects;

import extensions.anbui.daydream.library.LibraryUtils;
import extensions.anbui.daydream.project.ProjectDataBuildConfig;
import extensions.anbui.daydream.project.ProjectDataConfig;
import extensions.anbui.daydream.project.ProjectDataDayDream;
import extensions.anbui.daydream.project.ProjectDataLibrary;
import pro.sketchware.databinding.ActivityUniversalProjectSettingsBinding;

public class UniversalProjectSettings extends AppCompatActivity {

    private String projectID;
    private ActivityUniversalProjectSettingsBinding binding;

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
        binding = ActivityUniversalProjectSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        binding.swEnabled.setChecked(ProjectDataDayDream.isEnableDayDream(projectID));
        binding.swEnabled.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ProjectDataDayDream.setEnableDayDream(projectID, isChecked);
            universalUIController(isChecked);
        });

        binding.swEdgetoedge.setChecked(ProjectDataDayDream.isUniversalEdgeToEdge(projectID));
        binding.swEdgetoedge.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalEdgeToEdge(projectID, isChecked));

        binding.swWindowinsetshandling.setChecked(ProjectDataDayDream.isUniversalWindowInsetsHandling(projectID));
        binding.swWindowinsetshandling.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalWindowInsetsHandling(projectID, isChecked));

        binding.swEnableandroidtextcolorremoval.setChecked(ProjectDataDayDream.isEnableAndroidTextColorRemoval(projectID));
        binding.swEnableandroidtextcolorremoval.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setEnableAndroidTextColorRemoval(projectID, isChecked));

        binding.swDisableautomaticpermissionrequests.setChecked(ProjectDataDayDream.isUniversalDisableAutomaticPermissionRequests(projectID));
        binding.swDisableautomaticpermissionrequests.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalDisableAutomaticPermissionRequests(projectID, isChecked));

        binding.swContentprotection.setChecked(ProjectDataDayDream.isUniversalContentProtection(projectID));
        binding.swContentprotection.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalContentProtection(projectID, isChecked));

        binding.swForceaddworkmanager.setChecked(ProjectDataDayDream.isForceAddWorkManager(projectID));
        binding.swForceaddworkmanager.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setForceAddWorkManager(projectID, isChecked));

        binding.swUsemedia3.setChecked((ProjectDataDayDream.isUniversalUseMedia3(projectID)));
        binding.swUsemedia3.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalUseMedia3(projectID, isChecked));

        binding.swEnableOnBackInvokedCallback.setChecked(ProjectDataDayDream.isUninversalEnableOnBackInvokedCallback(projectID));
        binding.swEnableOnBackInvokedCallback.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUninversalEnableOnBackInvokedCallback(projectID, isChecked));

        binding.lnEnabled.setOnClickListener(v -> binding.swEnabled.toggle());
        binding.lnEdgetoedge.setOnClickListener(v -> binding.swEdgetoedge.toggle());
        binding.lnWindowinsetshandling.setOnClickListener(v -> binding.swWindowinsetshandling.toggle());
        binding.lnEnableandroidtextcolorremoval.setOnClickListener(v -> binding.swEnableandroidtextcolorremoval.toggle());
        binding.lnDisableautomaticpermissionrequests.setOnClickListener(v -> binding.swDisableautomaticpermissionrequests.toggle());
        binding.lnContentprotection.setOnClickListener(v -> binding.swContentprotection.toggle());
        binding.lnForceaddworkmanager.setOnClickListener(v -> binding.swForceaddworkmanager.toggle());
        binding.lnUsemedia3.setOnClickListener(v -> binding.swUsemedia3.toggle());
        binding.lnEnableOnBackInvokedCallback.setOnClickListener(v -> binding.swEnableOnBackInvokedCallback.toggle());

        universalUIController(binding.swEnabled.isChecked());
        initializeEdgeToEdge();
        initializeWindowinsetshandling();
        initializeForceAddWorkmanager();
        initializeUseMedia3();
    }

    private void universalUIController(boolean isEnable) {
        binding.lnAllOptions.setAlpha(isEnable ? 1 : 0.5f);

        binding.lnEdgetoedge.setEnabled(isEnable && ProjectDataLibrary.isEnabledAppCompat(projectID));
        binding.lnWindowinsetshandling.setEnabled(isEnable && LibraryUtils.isAllowUseWindowInsetsHandling(projectID));
        binding.lnEnableandroidtextcolorremoval.setEnabled(isEnable);
        binding.lnDisableautomaticpermissionrequests.setEnabled(isEnable);
        binding.lnContentprotection.setEnabled(isEnable);
        binding.lnForceaddworkmanager.setEnabled(isEnable && ProjectDataLibrary.isEnabledAppCompat(projectID));
        binding.lnUsemedia3.setEnabled(isEnable && LibraryUtils.isAllowUseAndroidXMedia3(projectID));
        binding.lnEnableOnBackInvokedCallback.setEnabled(isEnable);
    }

    private void initializeEdgeToEdge() {
        if (!ProjectDataLibrary.isEnabledAppCompat(projectID)) {
            binding.lnEdgetoedge.setEnabled(false);
            binding.lnEdgetoedge.setAlpha(0.5f);
            binding.tvEdgetoedgenote.setText("To use, enable AppCompat. " + binding.tvEdgetoedgenote.getText().toString());
        }
    }

    private void initializeWindowinsetshandling() {
        boolean finalstatus = true;
        if (!ProjectDataLibrary.isEnabledAppCompat(projectID)) {
            finalstatus = false;
            binding.tvWindowinsetshandlingnote.setText("To use, enable AppCompat. " + binding.tvWindowinsetshandlingnote.getText().toString());
        } else if (ProjectDataBuildConfig.isUseJava7(projectID)) {
            finalstatus = false;
            binding.tvWindowinsetshandlingnote.setText("To use, use a newer version of Java. " + binding.tvWindowinsetshandlingnote.getText().toString());
        }

        binding.lnWindowinsetshandling.setEnabled(finalstatus);
        binding.lnWindowinsetshandling.setAlpha(finalstatus ? 1 : 0.5f);
    }

    private void initializeForceAddWorkmanager() {
         if (!ProjectDataLibrary.isEnabledAppCompat(projectID)) {
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
}
