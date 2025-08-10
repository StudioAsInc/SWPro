package extensions.anbui.daydream.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.EdgeToEdge;

import java.util.Objects;

import extensions.anbui.daydream.library.LibraryUtils;
import extensions.anbui.daydream.project.ProjectDataDayDream;
import pro.sketchware.databinding.ActivityDaydreamGoogleSettingsBinding;

public class DayDreamGoogleSettings extends AppCompatActivity {

    private String projectID;
    private ActivityDaydreamGoogleSettingsBinding binding;

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
        binding = ActivityDaydreamGoogleSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        binding.swAnalytics.setChecked(ProjectDataDayDream.isUseGoogleAnalytics(projectID));
        binding.swAnalytics.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUseGoogleAnalytics(projectID, isChecked));

        binding.lnAnalytics.setOnClickListener(v -> binding.swAnalytics.toggle());

        initializeAnalytics();
    }

    public void initializeAnalytics() {
        if (!LibraryUtils.isAllowUseGoogleAnalytics(projectID)) {
            binding.lnAnalytics.setEnabled(false);
            binding.lnAnalytics.setAlpha(0.5f);
            binding.tvAnalyticsnote.setText("To use, turn on Firebase. " + binding.tvAnalyticsnote.getText().toString());
        }
    }
}
