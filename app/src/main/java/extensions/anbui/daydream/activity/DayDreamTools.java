package extensions.anbui.daydream.activity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.dialog.DialogUtils;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.tool.DayDreamTool;
import pro.sketchware.R;
import pro.sketchware.databinding.ActivityDaydreamToolsBinding;

public class DayDreamTools extends AppCompatActivity {
    private ActivityDaydreamToolsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDaydreamToolsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationOnClickListener(view -> onBackPressed());
        initialize();
    }

    private void initialize() {
        binding.lnCleanuplocallibrary.setOnClickListener(v -> cleanUpLocalLib());
    }

    private void cleanUpLocalLib() {
        DialogUtils.twoDialog(DayDreamTools.this,
                "Clean up local library",
                "Find and move unused local libraries to the recycling bin.",
                "Clean up",
                "Cancel",
                true,
                R.drawable.ic_mtrl_box,
                true,
                this::startleanUpLocalLib, null, null);
    }

    private void startleanUpLocalLib() {
        String message = "No libraries need to be cleaned.";
        int cleared = DayDreamTool.cleanupLocalLib();
        if (cleared > 0 ) message = "Cleaned up " + cleared + " local libraries. And those libraries have been moved to"
                + FileUtils.getInternalStorageDir() + Configs.recycleBinDayDreamFolderDir + "local_library.";
        DialogUtils.oneDialog(DayDreamTools.this,
                "Done",
                message,
                "OK",
                true,
                R.drawable.ic_mtrl_check,
                true, null, null);
    }
}