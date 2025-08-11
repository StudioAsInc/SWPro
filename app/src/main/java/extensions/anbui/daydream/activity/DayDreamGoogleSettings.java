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
        boolean finalstatus = true;
        if (!ProjectDataConfig.isMinSDKNewerThan23(projectID)) {
            finalstatus = false;
            binding.tvAnalyticsnote.setText("To use, min SDK required is 24 or newer (Android 7+). " + binding.tvAnalyticsnote.getText().toString());
        }  else if (ProjectDataBuildConfig.isUseJava7(projectID)) {
            finalstatus = false;
            binding.tvAnalyticsnote.setText("To use, use a newer version of Java. " + binding.tvAnalyticsnote.getText().toString());
        } else if (!ProjectDataLibrary.isEnabledFirebase(projectID)) {
            finalstatus = false;
            binding.tvAnalyticsnote.setText("To use, turn on Firebase. " + binding.tvAnalyticsnote.getText().toString());
        }

        if (!finalstatus) {
            binding.lnAnalytics.setEnabled(false);
            binding.lnAnalytics.setAlpha(0.5f);
        }
    }
}
