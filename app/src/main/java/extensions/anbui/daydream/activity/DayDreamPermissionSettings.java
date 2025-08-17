package extensions.anbui.daydream.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import extensions.anbui.daydream.project.ProjectDataDayDream;
import pro.sketchware.databinding.ActivityDaydreamPermissionSettingsBinding;

public class DayDreamPermissionSettings extends AppCompatActivity {

    private String projectID;
    private ActivityDaydreamPermissionSettingsBinding binding;

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
        binding = ActivityDaydreamPermissionSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        binding.swDisableautomaticpermissionrequests.setChecked(ProjectDataDayDream.isUniversalDisableAutomaticPermissionRequests(projectID));
        binding.swDisableautomaticpermissionrequests.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalDisableAutomaticPermissionRequests(projectID, isChecked));

        binding.lnDisableautomaticpermissionrequests.setOnClickListener(v -> binding.swDisableautomaticpermissionrequests.toggle());
    }
}