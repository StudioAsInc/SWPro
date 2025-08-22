package extensions.anbui.daydream.git;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.file.FilesTools;
import extensions.anbui.daydream.project.ProjectDataDecryptor;
import extensions.anbui.daydream.tool.ToolCore;

public class GitApplyUtils {
    public static String TAG = Configs.universalTAG + "GitApplyUtils";

    public static boolean apply(String projectID, TextView statusTextView) {
        Log.i(TAG, "apply: " + projectID);
        try {
        cleanUpProject(projectID);
        copyProjectData(projectID);
        copyFonts(projectID, statusTextView);
        copyIcons(projectID, statusTextView);
        copyImages(projectID, statusTextView);
        copySounds(projectID, statusTextView);
        copyProjectInfo(projectID, statusTextView);
        ToolCore.fixID(projectID);
        return true;
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
            return false;
        }
    }

    public static void cleanUpProject(String projectID) {
        Log.i(TAG, "cleanUpProject: " + projectID);
        try {
        FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/files/"));

        FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.resFontsFolderDir + projectID + "/"));
        FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.resIconsFolderDir + projectID + "/"));
        FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.resImagesFolderDir + projectID + "/"));
        FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.resSoundsFolderDir + projectID + "/"));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copyProjectData(String projectID) {
        Log.i(TAG, "copyProjectData: " + projectID);

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID);
        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/files");

        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/data",
                filelist
        );

        for (String filePath : filelist) {
            if (Objects.requireNonNull(Uri.parse(filePath).getLastPathSegment()).equals("DataDayDreamGit.json")) {
                Log.i(TAG, "copyProjectData: Skiped " + Uri.parse(filePath).getLastPathSegment());
            } else if (Objects.requireNonNull(Uri.parse(filePath).getLastPathSegment()).equals("library")) {
                String content = ProjectDataDecryptor.decryptProjectFile(filePath);
                if (!(content.contains("ca-app-pub-00000") || content.contains("xxxxx-xxxxx") || content.contains("AIzaSyA-00000"))) {
                    try {
                        FilesTools.startCopy(filePath, FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID);
                        Log.i(TAG, "copyProjectData: " + filePath);
                    } catch (Exception e) {
                        Log.e(TAG, "copyProjectData: " + e.getMessage());
                    }
                }
            } else {
                try {
                    FilesTools.startCopy(filePath, FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID);
                    Log.i(TAG, "copyProjectData: " + filePath);
                } catch (Exception e) {
                    Log.e(TAG, "copyProjectData: " + e.getMessage());
                }
            }
        }
    }

    public static void copyFonts(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyFonts: " + projectID);

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.resFontsFolderDir + projectID);

        try {
            FilesTools.copyFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/fonts/"),
                    Path.of(FileUtils.getInternalStorageDir() + Configs.resFontsFolderDir + projectID));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copyIcons(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyIcons: " + projectID);

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.resIconsFolderDir + projectID);

        try {
            FilesTools.copyFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/icons/"),
                    Path.of(FileUtils.getInternalStorageDir() + Configs.resIconsFolderDir + projectID));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copyImages(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyImages: " + projectID);

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.resImagesFolderDir + projectID);

        try {
            FilesTools.copyFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/images/"),
                    Path.of(FileUtils.getInternalStorageDir() + Configs.resImagesFolderDir + projectID));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copySounds(String projectID, TextView statusTextView) {
        Log.i(TAG, "copySounds: " + projectID);

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.resSoundsFolderDir + projectID);

        try {
            FilesTools.copyFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/sounds/"),
                    Path.of(FileUtils.getInternalStorageDir() + Configs.resSoundsFolderDir + projectID));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copyProjectInfo(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyProjectInfo: " + projectID);
        updateStatus(statusTextView, "Copying project info: project");
        FileUtils.copyFile(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/project", FileUtils.getInternalStorageDir() + Configs.projectInfoFolderDir + projectID + "/");
    }

    public static void copyLibrary(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyLibrary: " + projectID);

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.projectLocalLibFolderDir);

        try {
            FilesTools.copyFileOrDirectory(Path.of(Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/local_libs/"),
                    Path.of(FileUtils.getInternalStorageDir() + Configs.projectLocalLibFolderDir));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    private static void updateStatus(TextView statusTextView, String msg) {
        Log.i(TAG, "updateStatus: " + msg);
        if (statusTextView == null || statusTextView.getContext() == null) return;
        ((Activity) statusTextView.getContext()).runOnUiThread(() -> statusTextView.setText(msg));
    }
}
