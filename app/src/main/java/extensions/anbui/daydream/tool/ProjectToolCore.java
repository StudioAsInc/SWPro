package extensions.anbui.daydream.tool;

import android.util.Log;

import java.io.File;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;

public class ProjectToolCore {

    public static String TAG = Configs.universalTAG + "ProjectToolCore";

    public static boolean cleanTemporaryFiles(String projectID) {
        try {
            FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID + "/"));
            Log.i(TAG, "Cleaned: " + FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID + "/");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Clean failed: " + e.getMessage(), e);
            return false;
        }
    }

    public static boolean clone(String projectID) {
        String newID = String.valueOf(ToolCore.getLastID() + 1);

        if (!FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID)) return false;
        if (FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + newID + "/logic")) return false;

        try {
            FileUtils.copyDirectory(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/", FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + newID + "/");
            FileUtils.copyFile(FileUtils.getInternalStorageDir() + Configs.projectInfoFolderDir + projectID + "/project", FileUtils.getInternalStorageDir() + Configs.projectInfoFolderDir + newID + "/");
            FileUtils.copyDirectory(FileUtils.getInternalStorageDir() + Configs.resFontsFolderDir + projectID + "/", FileUtils.getInternalStorageDir() + Configs.resFontsFolderDir + newID + "/");
            FileUtils.copyDirectory(FileUtils.getInternalStorageDir() + Configs.resIconsFolderDir + projectID + "/", FileUtils.getInternalStorageDir() + Configs.resIconsFolderDir + newID + "/");
            FileUtils.copyDirectory(FileUtils.getInternalStorageDir() + Configs.resImagesFolderDir + projectID + "/", FileUtils.getInternalStorageDir() + Configs.resImagesFolderDir + newID + "/");
            FileUtils.copyDirectory(FileUtils.getInternalStorageDir() + Configs.resSoundsFolderDir + projectID + "/", FileUtils.getInternalStorageDir() + Configs.resSoundsFolderDir + newID + "/");
            ToolCore.fixID(newID);
            Log.i(TAG, "Cloned: " + FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + newID + "/");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Clone failed: " + e.getMessage(), e);
            return false;
        }
    }
}
