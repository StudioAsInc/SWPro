package extensions.anbui.daydream.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

import java.util.Objects;

import extensions.anbui.daydream.project.ProjectDataBuildConfig;
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

        binding.lnEnabled.setOnClickListener(v -> binding.swEnabled.toggle());
        binding.lnEdgetoedge.setOnClickListener(v -> binding.swEdgetoedge.toggle());
        binding.lnWindowinsetshandling.setOnClickListener(v -> binding.swWindowinsetshandling.toggle());
        binding.lnEnableandroidtextcolorremoval.setOnClickListener(v -> binding.swEnableandroidtextcolorremoval.toggle());
        binding.lnDisableautomaticpermissionrequests.setOnClickListener(v -> binding.swDisableautomaticpermissionrequests.toggle());
        binding.lnContentprotection.setOnClickListener(v -> binding.swContentprotection.toggle());
        binding.lnForceaddworkmanager.setOnClickListener(v -> binding.swForceaddworkmanager.toggle());

        universalUIController(binding.swEnabled.isChecked());
    }

    private void universalUIController(boolean isEnable) {
        binding.lnAllOptions.setAlpha(isEnable ? 1 : 0.5f);
        if (!ProjectDataLibrary.isEnabledAppCompat(projectID)) {
            binding.lnEdgetoedge.setEnabled(false);
            binding.lnWindowinsetshandling.setEnabled(false);
            binding.lnForceaddworkmanager.setEnabled(false);
            binding.lnEdgetoedge.setAlpha(isEnable ? 0.5f : 1);
            binding.lnWindowinsetshandling.setAlpha(isEnable ? 0.5f : 1);
            binding.lnForceaddworkmanager.setAlpha(isEnable ? 0.5f : 1);
            if (!binding.tvEdgetoedgenote.getText().toString().contains("AppCompat")) {
                binding.tvEdgetoedgenote.setText("To use, enable AppCompat. " + binding.tvEdgetoedgenote.getText().toString());
                binding.tvWindowinsetshandlingnote.setText("To use, enable AppCompat. " + binding.tvWindowinsetshandlingnote.getText().toString());
                binding.tvForceaddworkmanagernote.setText("To use, enable AppCompat. " + binding.tvForceaddworkmanagernote.getText().toString());
            }

        } else {
            binding.lnEdgetoedge.setEnabled(isEnable);
            if (ProjectDataBuildConfig.isUseJava7(projectID)) {
                binding.lnWindowinsetshandling.setEnabled(false);
                binding.lnWindowinsetshandling.setAlpha(isEnable ? 0.5f : 1);
                if (!binding.tvWindowinsetshandlingnote.getText().toString().contains("Java"))
                    binding.tvWindowinsetshandlingnote.setText("To use, use a newer version of Java. " + binding.tvWindowinsetshandlingnote.getText().toString());
            } else {
                binding.lnWindowinsetshandling.setEnabled(isEnable);
            }
        }
        binding.lnEnableandroidtextcolorremoval.setEnabled(isEnable);
        binding.lnDisableautomaticpermissionrequests.setEnabled(isEnable);
        binding.lnContentprotection.setEnabled(isEnable);
    }
}
