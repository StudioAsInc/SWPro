package extensions.anbui.daydream.tool;

import java.io.File;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;

public class DayDreamTool {
    public static void copyToTemp(String path) {
        FileUtils.copyFile(path, FileUtils.getInternalStorageDir() + Configs.tempDayDreamFolderDir);
    }
    public static String getTempFilePath(String path) {
        return FileUtils.getInternalStorageDir() + Configs.tempDayDreamFolderDir + path;
    }
}
