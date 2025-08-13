package extensions.anbui.daydream.tool;

import java.io.File;
import java.util.ArrayList;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;

public class DayDreamTool {
    public static void copyToTemp(String path) {
        FileUtils.copyFile(path, FileUtils.getInternalStorageDir() + Configs.tempDayDreamFolderDir);
    }
    public static String getTempFilePath(String path) {
        return FileUtils.getInternalStorageDir() + Configs.tempDayDreamFolderDir + path;
    }

    public static void cleanOutTheRecyclingBin() {
        FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.recycleBinDayDreamFolderDir));
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
                    FileUtils.deleteRecursive(new File(filePath));
                    cleaned++;
                }
            }
        }
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
        return result.toString();
    }
}
