package extensions.anbui.daydream.git;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;
import android.widget.TextView;

import com.besome.sketch.export.ExportSource;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.file.FilesTools;
import extensions.anbui.daydream.project.ProjectDataDecryptor;
import extensions.anbui.daydream.project.ProjectDataLibrary;

public class GitPushUtils {
    public static final String TAG = "GitPushUtils";

    public static void quickPrepareFiles(Activity activity, String projectID) {
        Log.i(TAG, "quickPrepareFiles: " + projectID);

        preparefiles(activity, projectID, null, false, false, false, true, true, false, false);
    }

    public static boolean preparefiles(Activity activity, String projectID, TextView statusTextView, boolean locallibs, boolean customblocks, boolean includeApis, boolean pushProject, boolean pushSource, boolean keepFiles, boolean gradlefiles) {
        Log.i(TAG, "preparefiles: " + projectID);

        boolean result = FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName);

        if (!result) return false;

        if (!keepFiles)
            cleanUpGitFolder(projectID, statusTextView, pushProject, pushSource);

        if (pushProject) {
            copyProjectData(projectID, statusTextView, customblocks);
            copyFonts(projectID, statusTextView);
            copyIcons(projectID, statusTextView);
            copyImages(projectID, statusTextView);
            copySounds(projectID, statusTextView);
            if (locallibs) copyUsingLocalLibraries(projectID, statusTextView);
            copyProjectInfo(projectID, statusTextView);

            if (!includeApis)
                ProjectDataDecryptor.saveEncryptedFile(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/data/library", replaceLibraryData(projectID));
        }

        if (pushSource) {
            updateStatus(statusTextView, "Exporting source...");
            ExportSource.startExport(activity, projectID, statusTextView);
            if (gradlefiles) gradleFiles(projectID);
            copyProjectSource(projectID, statusTextView);

            if (!includeApis && FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitSourceFolderName + "/app/src/main/res/values/secrets.xml"))
                FileUtils.writeTextFile(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitSourceFolderName + "/app/src/main/res/values/secrets.xml", hideSecrets(projectID));
        }

        updateStatus(statusTextView, "File prepared...");
        return true;
    }

    public static void cleanUpGitFolder(String projectID, TextView statusTextView, boolean pushProject, boolean pushSource) {
        Log.i(TAG, "cleanUpGitFolder: " + projectID);

        if (!FileUtils.isFileExist(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName))
            return;

        try {
            if (!pushProject)
                FilesTools.deleteFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/"));
            if (!pushSource)
                FilesTools.deleteFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitSourceFolderName + "/"));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copyProjectData(String projectID, TextView statusTextView, boolean customblocks) {
        Log.i(TAG, "copyProjectData: " + projectID);

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/data");

        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID,
                filelist
        );

        for (String filePath : filelist) {
            if (Objects.requireNonNull(Uri.parse(filePath).getLastPathSegment()).equals("DataDayDreamGit.json")) {
                Log.i(TAG, "copyProjectData: Skiped " + Uri.parse(filePath).getLastPathSegment());
            } else if (Objects.requireNonNull(Uri.parse(filePath).getLastPathSegment()).equals("custom_blocks")) {
                if (customblocks) {
                    updateStatus(statusTextView, "Copying project data: " + Uri.parse(filePath).getLastPathSegment());
                    try {
                        FilesTools.startCopy(filePath, FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/data");
                    } catch (Exception e) {
                        Log.e("copyProjectData", Objects.requireNonNull(e.getMessage()));
                    }
                }
            } else {
                updateStatus(statusTextView, "Copying project data: " + Uri.parse(filePath).getLastPathSegment());
                try {
                    FilesTools.startCopy(filePath, FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/data");
                } catch (Exception e) {
                    Log.e("copyProjectData", Objects.requireNonNull(e.getMessage()));
                }
            }
        }
    }

    public static void copyFonts(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyFonts: " + projectID);
        updateStatus(statusTextView, "Copying fonts...");

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/fonts");

        try {
            FilesTools.copyFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.resFontsFolderDir + projectID),
                    Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/fonts/"));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copyIcons(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyIcons: " + projectID);
        updateStatus(statusTextView, "Copying icons...");

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/icons");

        try {
            FilesTools.copyFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.resIconsFolderDir + projectID),
                    Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/icons/"));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copyImages(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyImages: " + projectID);
        updateStatus(statusTextView, "Copying images...");

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/images");

        try {
            FilesTools.copyFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.resImagesFolderDir + projectID),
                    Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/images/"));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copySounds(String projectID, TextView statusTextView) {
        Log.i(TAG, "copySounds: " + projectID);
        updateStatus(statusTextView, "Copying sounds...");

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/sounds");

        try {
            FilesTools.copyFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.resSoundsFolderDir + projectID),
                    Path.of(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/resources/sounds/"));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static void copyUsingLocalLibraries(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyUsingLocalLibraries: " + projectID);
        updateStatus(statusTextView, "Copying libraries...");

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
                        FileUtils.copyDirectory(filePath, FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName + "/local_libs/" + Uri.parse(filePath).getLastPathSegment());
                    } catch (Exception e) {
                        Log.e("copyUsingLocalLibToBackupProject", Objects.requireNonNull(e.getMessage()));
                    }
                }
            }
        }
    }

    public static void copyProjectInfo(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyProjectInfo: " + projectID);
        updateStatus(statusTextView, "Copying project info...");
        FileUtils.copyFile(FileUtils.getInternalStorageDir() + Configs.projectInfoFolderDir + projectID + "/project", FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitProjectFolderName);
    }

    public static void copyProjectSource(String projectID, TextView statusTextView) {
        Log.i(TAG, "copyProjectData: " + projectID);

        FileUtils.createDirectory(FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitSourceFolderName + "/");

        ArrayList<String> filelist = new ArrayList<>();
        FileUtils.getFileListInDirectory(
                FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID,
                filelist
        );

        for (String filePath : filelist) {
            if (Objects.requireNonNull(Uri.parse(filePath).getLastPathSegment()).equals("bin")
                    || Objects.requireNonNull(Uri.parse(filePath).getLastPathSegment()).equals("gen")) {
                Log.i(TAG, "copyProjectData: Skiped " + Uri.parse(filePath).getLastPathSegment());
            } else {
                updateStatus(statusTextView, "Copying project data: " + Uri.parse(filePath).getLastPathSegment());
                try {
                    FilesTools.startCopy(filePath, FileUtils.getInternalStorageDir() + Configs.gitFolderDir + projectID + Configs.gitSourceFolderName + "/");
                } catch (Exception e) {
                    Log.e("copyProjectData", Objects.requireNonNull(e.getMessage()));
                }
            }
        }
    }

    public static void gradleFiles(String projectID) {
        Log.i(TAG, "gradleFiles: " + projectID);
        try {
            FilesTools.deleteFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID + "/build.gradle"));
            FilesTools.deleteFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID + "/gradle.properties"));
            FilesTools.deleteFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID + "/settings.gradle"));
            FilesTools.deleteFileOrDirectory(Path.of(FileUtils.getInternalStorageDir() + Configs.projectMySourceFolderDir + projectID + "/app/build.gradle"));
        } catch (Exception e) {
            Log.e(TAG, Objects.requireNonNull(e.getMessage()));
        }
    }

    public static String replaceLibraryData(String projectID) {
        Log.i(TAG, "replaceLibraryData: " + projectID);
        String result = replaceFirebaseLibraryData(ProjectDataLibrary.isEnabledFirebase(projectID)) + "\n";
        result += replaceAppCompatLibraryData(ProjectDataLibrary.isEnabledAppCompat(projectID)) + "\n";
        result += replaceAdmobLibraryData(ProjectDataLibrary.isEnabledAdmob(projectID)) + "\n";
        result += replaceGoogleMapLibraryData(ProjectDataLibrary.isEnabledGoogleMap(projectID));
        return result;
    }

    public static String replaceFirebaseLibraryData(boolean isUsingFirebase) {
        Log.i(TAG, "replaceFirebaseLibraryData: " + isUsingFirebase);
        if (isUsingFirebase) {
            return "@firebaseDB\n" +
                    "{\"adUnits\":[],\"data\":\"xxxxx-xxxxx-default-rtdb.firebaseio.com\",\"libType\":0,\"reserved1\":\"0:0000000000000:android:0000000000000000000000\",\"reserved2\":\"0000000000000000000000000000000000000\",\"reserved3\":\"xxxxx-xxxxx.appspot.com\",\"testDevices\":[],\"useYn\":\"Y\"}";
        } else {
            return "@firebaseDB\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":0,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}";
        }
    }

    public static String replaceAppCompatLibraryData(boolean isUsingAppCompat) {
        Log.i(TAG, "replaceAppCompatLibraryData: " + isUsingAppCompat);
        if (isUsingAppCompat) {
            return "@compat\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":1,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"Y\"}";
        } else {
            return "@compat\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":1,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}";
        }
    }

    public static String replaceAdmobLibraryData(boolean isUsingAdmob) {
        Log.i(TAG, "replaceAdmobLibraryData: " + isUsingAdmob);
        if (isUsingAdmob) {
            return "@admob\n" +
                    "{\"adUnits\":[{\"id\":\"ca-app-pub-0000000000000000/0000000000\",\"name\":\"bn\"},{\"id\":\"ca-app-pub-0000000000000000/0000000000\",\"name\":\"tg\"},{\"id\":\"ca-app-pub-0000000000000000/0000000000\",\"name\":\"ctt\"}],\"appId\":\"ca-app-pub-0000000000000000~0000000000\",\"data\":\"\",\"libType\":2,\"reserved1\":\"bn : ca-app-pub-0000000000000000/0000000000\",\"reserved2\":\"tg : ca-app-pub-0000000000000000/0000000000\",\"reserved3\":\"ctt : ca-app-pub-0000000000000000/0000000000\",\"testDevices\":[],\"useYn\":\"Y\"}";
        } else {
            return "@admob\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":2,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}";
        }
    }

    public static String replaceGoogleMapLibraryData(boolean isUsingGoogleMap) {
        Log.i(TAG, "replaceGoogleMapLibraryData: " + isUsingGoogleMap);
        if (isUsingGoogleMap) {
            return "@googleMap\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"AIzaSyA-00000000000000000000000000000\\n\",\"libType\":3,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"Y\"}";
        } else {
            return "@googleMap\n" +
                    "{\"adUnits\":[],\"appId\":\"\",\"configurations\":{},\"data\":\"\",\"libType\":3,\"reserved1\":\"\",\"reserved2\":\"\",\"reserved3\":\"\",\"testDevices\":[],\"useYn\":\"N\"}";
        }
    }

    public static String hideSecrets(String projectID) {
        Log.i(TAG, "hideSecrets: " + projectID);
        return """
                <resources>
                \t<integer name="google_play_services_version">12451000</integer>
                \t<string name="firebase_database_url" translatable="false">https://xxxxx-xxxxx-default-rtdb.firebaseio.com</string>
                \t<string name="project_id" translatable="false">xxxxx-xxxxx</string>
                \t<string name="google_app_id" translatable="false">0:0000000000000:android:0000000000000000000000</string>
                \t<string name="google_api_key" translatable="false">0000000000000000000000000000000000000</string>
                \t<string name="google_storage_bucket" translatable="false">xxxxx-xxxxx.appspot.com</string>
                \t<string name="google_maps_key" translatable="false">AIzaSyA-00000000000000000000000000000</string>
                </resources>
                """;
    }

    private static void updateStatus(TextView statusTextView, String msg) {
        Log.i(TAG, "updateStatus: " + msg);
        if (statusTextView == null || statusTextView.getContext() == null) return;
        ((Activity) statusTextView.getContext()).runOnUiThread(() -> statusTextView.setText(msg));
    }
}
