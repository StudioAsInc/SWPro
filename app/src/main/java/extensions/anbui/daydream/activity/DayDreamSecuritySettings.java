package extensions.anbui.daydream.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

import java.util.Objects;

import extensions.anbui.daydream.project.ProjectDataDayDream;
import pro.sketchware.databinding.ActivityDaydreamSecuritySettingsBinding;

public class DayDreamSecuritySettings extends AppCompatActivity {

    private String projectID;
    private ActivityDaydreamSecuritySettingsBinding binding;

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
        binding = ActivityDaydreamSecuritySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        binding.swContentprotection.setChecked(ProjectDataDayDream.isUniversalContentProtection(projectID));
        binding.swContentprotection.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalContentProtection(projectID, isChecked));

        binding.lnContentprotection.setOnClickListener(v -> binding.swContentprotection.toggle());
    }
}
