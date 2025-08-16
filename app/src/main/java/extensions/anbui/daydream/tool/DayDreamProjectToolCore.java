package extensions.anbui.daydream.tool;

import android.util.Log;

import java.io.File;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;

public class DayDreamProjectToolCore {
    public static boolean cleanTemporaryFiles(String projectID) {
        try {
            FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID + "/"));
            return true;
        } catch (Exception e) {
            Log.e("CleanError", "Clean failed: " + e.getMessage(), e);
            return false;
        }
    }

    public static boolean clone(String projectID) {
        String newID = String.valueOf(DayDreamTool.getLastID() + 1);

        if (!FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID)) return false;
        if (FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + newID + "/logic")) return false;

        try {
            FileUtils.copyDirectory(new File(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/"), new File(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + newID + "/"));
            FileUtils.copyFile(FileUtils.getInternalStorageDir() + Configs.projectInfoFolderDir + projectID + "/project", FileUtils.getInternalStorageDir() + Configs.projectInfoFolderDir + newID + "/");
            FileUtils.copyDirectory(new File(FileUtils.getInternalStorageDir() + Configs.resFontsFolderDir + projectID + "/"), new File(FileUtils.getInternalStorageDir() + Configs.resFontsFolderDir + newID + "/"));
            FileUtils.copyDirectory(new File(FileUtils.getInternalStorageDir() + Configs.resIconsFolderDir + projectID + "/"), new File(FileUtils.getInternalStorageDir() + Configs.resIconsFolderDir + newID + "/"));
            FileUtils.copyDirectory(new File(FileUtils.getInternalStorageDir() + Configs.resImagesFolderDir + projectID + "/"), new File(FileUtils.getInternalStorageDir() + Configs.resImagesFolderDir + newID + "/"));
            FileUtils.copyDirectory(new File(FileUtils.getInternalStorageDir() + Configs.resSoundsFolderDir + projectID + "/"), new File(FileUtils.getInternalStorageDir() + Configs.resSoundsFolderDir + newID + "/"));
            DayDreamTool.fixID(newID);
            return true;
        } catch (Exception e) {
            Log.e("CloneError", "Clone failed: " + e.getMessage(), e);
            return false;
        }
    }
}
