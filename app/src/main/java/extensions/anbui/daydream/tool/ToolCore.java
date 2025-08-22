package extensions.anbui.daydream.tool;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.project.ProjectDataDecryptor;

public class ToolCore {

    public static String TAG = Configs.universalTAG + "ToolCore";

    public static void copyToTemp(String path) {
        Log.i(TAG, "copyToTemp: " + path);
        FileUtils.copyFile(path, FileUtils.getInternalStorageDir() + Configs.tempDayDreamFolderDir);
    }
    public static String getTempFilePath(String path) {
        Log.i(TAG, "getTempFilePath: " + path);
        return FileUtils.getInternalStorageDir() + Configs.tempDayDreamFolderDir + path;
    }

    public static void cleanOutTheRecyclingBin() {
        Log.i(TAG, "cleanOutTheRecyclingBin");
        try {
            FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.recycleBinDayDreamFolderDir));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static int cleanUpTemporaryFiles() {
        int cleaned = 0;
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir,
                filelist
        );

        for (String filePath : filelist) {
            if (FileUtils.isFileExist(filePath)) {
                if (!filePath.contains("list")) {
                    try {
                        FileUtils.deleteRecursive(new File(filePath));
                    } catch (Exception e) {
                        Log.e(TAG, Objects.requireNonNull(e.getMessage()));
                    }
                    cleaned++;
                }
            }
        }
        Log.i(TAG, "Cleaned: " + cleaned);
        return cleaned;
    }

    public static int cleanupLocalLib() {
        int moved = 0;
        String allUsingLocalLib = getAllUsingLocalLib();
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.projectLocalLibFolderDir,
                filelist
        );

        for (String filePath : filelist) {
            if (FileUtils.isFileExist(filePath)) {
                if (!allUsingLocalLib.contains(filePath)) {
                    FileUtils.moveAFile(filePath, FileUtils.getInternalStorageDir() + Configs.recycleBinDayDreamFolderDir + "local_libs/");
                    moved++;
                }
            }
        }
        Log.i(TAG, "Moved: " + moved);
        return moved;
    }

    public static String getAllUsingLocalLib() {
        StringBuilder result = new StringBuilder();
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir,
                filelist
        );

        for (String filePath : filelist) {
            String localLibPath = filePath + "/local_library";
            if (FileUtils.isFileExist(localLibPath)) {
                String content = FileUtils.readTextFile(localLibPath);
                result.append(content).append("\n");
            }
        }
        Log.i(TAG, "getAllUsingLocalLib: " + result.toString());
        return result.toString();
    }

    public static int getLastID() {
        int result = 0;
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir,
                filelist
        );

        for (String filePath : filelist) {
            if (FileUtils.isFileExist(filePath)) {
                String folderName = new File(filePath).getName().replaceAll("/", "");
                if (folderName.matches("\\d+") && Integer.parseInt(folderName) > result) {
                    result = Integer.parseInt(folderName);
                }
            }
        }
        Log.i(TAG, "getLastID: " + result);
        return result;
    }

    public static void fixID(String projectID) {
        Log.i(TAG, "fixID: " + projectID);
        String projectInfo = ProjectDataDecryptor.decryptProjectFile(FileUtils.getInternalStorageDir() + Configs.projectInfoFolderDir + projectID + "/project");
        JsonObject json = JsonParser.parseString(projectInfo).getAsJsonObject();
        json.addProperty("sc_id", projectID);
        ProjectDataDecryptor.saveEncryptedFile(FileUtils.getInternalStorageDir() + Configs.projectInfoFolderDir + projectID + "/project", new Gson().toJson(json));
    }
}
