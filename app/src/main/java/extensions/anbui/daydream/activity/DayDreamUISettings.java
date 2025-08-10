package extensions.anbui.daydream.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import extensions.anbui.daydream.project.ProjectDataBuildConfig;
import extensions.anbui.daydream.project.ProjectDataDayDream;
import extensions.anbui.daydream.project.ProjectDataLibrary;
import pro.sketchware.databinding.ActivityDaydreamUiSettingsBinding;

public class DayDreamUISettings extends AppCompatActivity {

    private String projectID;
    private ActivityDaydreamUiSettingsBinding binding;

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
        binding = ActivityDaydreamUiSettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        binding.swEdgetoedge.setChecked(ProjectDataDayDream.isUniversalEdgeToEdge(projectID));
        binding.swEdgetoedge.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalEdgeToEdge(projectID, isChecked));

        binding.swWindowinsetshandling.setChecked(ProjectDataDayDream.isUniversalWindowInsetsHandling(projectID));
        binding.swWindowinsetshandling.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUniversalWindowInsetsHandling(projectID, isChecked));

        binding.swEnableandroidtextcolorremoval.setChecked(ProjectDataDayDream.isEnableAndroidTextColorRemoval(projectID));
        binding.swEnableandroidtextcolorremoval.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setEnableAndroidTextColorRemoval(projectID, isChecked));

        binding.swEnableOnBackInvokedCallback.setChecked(ProjectDataDayDream.isUninversalEnableOnBackInvokedCallback(projectID));
        binding.swEnableOnBackInvokedCallback.setOnCheckedChangeListener((buttonView, isChecked) -> ProjectDataDayDream.setUninversalEnableOnBackInvokedCallback(projectID, isChecked));

        binding.lnEdgetoedge.setOnClickListener(v -> binding.swEdgetoedge.toggle());
        binding.lnWindowinsetshandling.setOnClickListener(v -> binding.swWindowinsetshandling.toggle());
        binding.lnEnableandroidtextcolorremoval.setOnClickListener(v -> binding.swEnableandroidtextcolorremoval.toggle());
        binding.lnEnableOnBackInvokedCallback.setOnClickListener(v -> binding.swEnableOnBackInvokedCallback.toggle());

        initializeEdgeToEdge();
        initializeWindowinsetshandling();
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
}