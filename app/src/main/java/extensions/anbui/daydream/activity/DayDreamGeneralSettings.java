package extensions.anbui.daydream.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

import java.util.Objects;

import extensions.anbui.daydream.project.ProjectDataDayDream;
import pro.sketchware.databinding.ActivityDaydreamGeneralSettingsBinding;

public class DayDreamGeneralSettings extends AppCompatActivity {

    private String projectID;
    private ActivityDaydreamGeneralSettingsBinding binding;

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
        binding = ActivityDaydreamGeneralSettingsBinding.inflate(getLayoutInflater());
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

        binding.lnEnabled.setOnClickListener(v -> binding.swEnabled.toggle());

        binding.lnLibrarysettings.setOnClickListener(v -> {
            goToSettings(DayDreamLibrarySettings.class);
        });

        binding.lnUisettings.setOnClickListener(v -> {
            goToSettings(DayDreamUISettings.class);
        });

        binding.lnSecuritysettings.setOnClickListener(v -> {
            goToSettings(DayDreamSecuritySettings.class);
        });

        binding.lnPermissionsettings.setOnClickListener(v -> {
            goToSettings(DayDreamPermissionSettings.class);
        });

        universalUIController(binding.swEnabled.isChecked());
    }

    private void universalUIController(boolean isEnable) {
        binding.lnAllOptions.setAlpha(isEnable ? 1 : 0.5f);

        binding.lnUisettings.setEnabled(isEnable);
        binding.lnLibrarysettings.setEnabled(isEnable);
        binding.lnPermissionsettings.setEnabled(isEnable);
        binding.lnSecuritysettings.setEnabled(isEnable);
    }

    private void goToSettings(Class<?> cls) {
        Intent intent = new Intent(DayDreamGeneralSettings.this, cls);
        intent.putExtra("sc_id", projectID);
        startActivity(intent);
    }
}
