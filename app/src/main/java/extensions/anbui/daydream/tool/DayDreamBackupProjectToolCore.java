package extensions.anbui.daydream.tool;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.project.GetProjectInfo;
import extensions.anbui.daydream.project.ProjectDataDecryptor;
import extensions.anbui.daydream.project.ProjectDataLibrary;
import extensions.anbui.daydream.settings.DayDreamSkSettings;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class DayDreamBackupProjectToolCore {

    public static String backedupFilePath = "";

    public static boolean backup(String projectID, TextView statusTextView, String backupFileName, boolean locallibs, boolean customblocks, boolean includeApis) {
        boolean result = true;
        FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir));
        copyProjectData(projectID, statusTextView, customblocks);
        copyFonts(projectID, statusTextView);
        copyIcons(projectID, statusTextView);
        copyImages(projectID, statusTextView);
        copySounds(projectID, statusTextView);
        if (locallibs) copyUsingLocalLibraries(projectID, statusTextView);
        copyProjectInfo(projectID, statusTextView);

        if (!includeApis)
            ProjectDataDecryptor.saveEncryptedFile(FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir + "data/library", replaceLibraryData(projectID));

        String fileName = backupFileName + ".swb";
        if (backupFileName.isEmpty())
            fileName = GetProjectInfo.getProjectName(projectID) + "-" + GetProjectInfo.getVersionName(projectID) + "-" + GetProjectInfo.getVersionCode(projectID) + "-" + System.currentTimeMillis() / 1000L + ".swb";

        String backupDir = FileUtils.getInternalStorageDir() + DayDreamSkSettings.getBackupDir() + GetProjectInfo.getProjectName(projectID) + "/";
        if(!FileUtils.createDirectory(backupDir)) result = false;
        try {
            if (result) {
                updateStatus(statusTextView, "Creating backup...");
                ZipFile zipFile = new ZipFile(backupDir + fileName);
                ArrayList<String> filelist = new ArrayList<>();
                FileUtils.getFileListInDirectory(
                        FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir,
                        filelist
                );
                List<File> files = new ArrayList<>();
                for (String path : filelist) {
                    File currentfile = new File(path);
                    if (currentfile.isDirectory()) {
                        zipFile.addFolder(currentfile);
                    } else {
                        files.add(currentfile);
                    }
                }

                if (!files.isEmpty()) {
                    zipFile.addFiles(files);
                }
            }
            backedupFilePath = backupDir + fileName;
        } catch (ZipException e) {
            result = false;
            Log.e("backup", Objects.requireNonNull(e.getMessage()));
        } catch (Exception e) {
            result = false;
            Log.e("backup", Objects.requireNonNull(e.getMessage()));
        }
        updateStatus(statusTextView, "Cleaning up...");
        FileUtils.deleteRecursive(new File(FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir));
        return result;
    }

    public static void copyProjectData(String projectID, TextView statusTextView, boolean customblocks) {
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID,
                filelist
        );

        for (String filePath : filelist) {
            if (Objects.requireNonNull(Uri.parse(filePath).getLastPathSegment()).equals("custom_blocks")) {
                if (customblocks) {
                    updateStatus(statusTextView, "Copying project data: " + Uri.parse(filePath).getLastPathSegment());
                    FileUtils.copyFile(filePath, FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir + "data/");
                }
            } else {
                updateStatus(statusTextView, "Copying project data: " + Uri.parse(filePath).getLastPathSegment());
                FileUtils.copyFile(filePath, FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir + "data/");
            }
        }
    }

    public static void copyFonts(String projectID, TextView statusTextView) {
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.resFontsFolderDir + projectID,
                filelist
        );

        for (String filePath : filelist) {
            updateStatus(statusTextView, "Copying fonts: " + Uri.parse(filePath).getLastPathSegment());
            FileUtils.copyFile(filePath, FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir + "resources/fonts/");
        }
    }

    public static void copyIcons(String projectID, TextView statusTextView) {
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.resIconsFolderDir + projectID,
                filelist
        );

        for (String filePath : filelist) {
            updateStatus(statusTextView, "Copying icons: " + Uri.parse(filePath).getLastPathSegment());
            FileUtils.copyFile(filePath, FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir + "resources/icons/");
        }
    }

    public static void copyImages(String projectID, TextView statusTextView) {
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.resImagesFolderDir + projectID,
                filelist
        );

        for (String filePath : filelist) {
            updateStatus(statusTextView, "Copying images: " + Uri.parse(filePath).getLastPathSegment());
            FileUtils.copyFile(filePath, FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir + "resources/images/");
        }
    }

    public static void copySounds(String projectID, TextView statusTextView) {
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.resSoundsFolderDir + projectID,
                filelist
        );

        for (String filePath : filelist) {
            updateStatus(statusTextView, "Copying sounds: " + Uri.parse(filePath).getLastPathSegment());
            FileUtils.copyFile(filePath, FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir + "resources/sounds/");
        }
    }

    public static void copyUsingLocalLibraries(String projectID, TextView statusTextView) {
        String usingLocalLib = FileUtils.readTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/local_library");
        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.projectLocalLibFolderDir,
                filelist
        );

        for (String filePath : filelist) {
            if (FileUtils.isFileExist(filePath)) {
                if (usingLocalLib.contains(filePath)) {
                    updateStatus(statusTextView, "Copying libraries: " + Uri.parse(filePath).getLastPathSegment());
                    try {
                        FileUtils.copyDirectory(new File(filePath), new File(FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir + "local_libs/" + Uri.parse(filePath).getLastPathSegment()));
                    } catch (Exception e) {
                        Log.e("copyUsingLocalLibToBackupProject", Objects.requireNonNull(e.getMessage()));
                    }
                }
            }
        }
    }

    public static void copyProjectInfo(String projectID, TextView statusTextView) {
        updateStatus(statusTextView, "Copying project info: project");
        FileUtils.copyFile(FileUtils.getInternalStorageDir() + Configs.projectInfoFolderDir + projectID + "/project", FileUtils.getInternalStorageDir() + Configs.tempBackupDayDreamFolderDir);
    }

    public static String replaceLibraryData(String projectID) {
        String result = replaceFirebaseLibraryData(ProjectDataLibrary.isEnabledFirebase(projectID)) + "\n";
        result += replaceAppCompatLibraryData(ProjectDataLibrary.isEnabledAppCompat(projectID)) + "\n";
        result += replaceAdmobLibraryData(ProjectDataLibrary.isEnabledAdmob(projectID)) + "\n";
        result += replaceGoogleMapLibraryData(ProjectDataLibrary.isEnabledGoogleMap(projectID));
        return result;
    }

    public static String replaceFirebaseLibraryData(boolean isUsingFirebase) {
        if (isUsingFirebase) {
            return "@firebaseDB\n" +
                    "{\"adUnits\":[],\"data\":\"xxxxx-xxxxx-default-rtdb.firebaseio.com\",\"libType\":0,\"reserved1\":\"0:0000000000000:android:0000000000000000000000\",\"reserved2\":\"0000000000000000000000000000000000000\",\"reserved3\":\"xxxxx-xxxxx.appspot.com\",\"testDevices\":[],\"useYn\":\"Y\"}";
        } else {
            return "@firebaseDB\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":0,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}";
        }
    }

    public static String replaceAppCompatLibraryData(boolean isUsingAppCompat) {
        if (isUsingAppCompat) {
            return "@compat\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":1,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"Y\"}";
        } else {
            return "@compat\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":1,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}";
        }
    }

    public static String replaceAdmobLibraryData(boolean isUsingAdmob) {
        if (isUsingAdmob) {
            return "@admob\n" +
                    "{\"adUnits\":[{\"id\":\"ca-app-pub-0000000000000000/0000000000\",\"name\":\"bn\"},{\"id\":\"ca-app-pub-0000000000000000/0000000000\",\"name\":\"tg\"},{\"id\":\"ca-app-pub-0000000000000000/0000000000\",\"name\":\"ctt\"}],\"appId\":\"ca-app-pub-0000000000000000~0000000000\",\"data\":\"\",\"libType\":2,\"reserved1\":\"bn : ca-app-pub-0000000000000000/0000000000\",\"reserved2\":\"tg : ca-app-pub-0000000000000000/0000000000\",\"reserved3\":\"ctt : ca-app-pub-0000000000000000/0000000000\",\"testDevices\":[],\"useYn\":\"Y\"}";
        } else {
            return "@admob\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":2,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}";
        }
    }

    public static String replaceGoogleMapLibraryData(boolean isUsingGoogleMap) {
        if (isUsingGoogleMap) {
            return "@googleMap\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"AIzaSyA-00000000000000000000000000000\\n\",\"libType\":3,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"Y\"}";
        } else {
            return "@googleMap\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":3,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}";
        }
    }

    private static void updateStatus(TextView statusTextView, String msg) {
        if (statusTextView == null || statusTextView.getContext() == null) return;
        ((Activity) statusTextView.getContext()).runOnUiThread(() -> statusTextView.setText(msg));
    }
}
