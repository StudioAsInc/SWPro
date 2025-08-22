package extensions.anbui.daydream.project;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;

public class ProjectDataLocalLibraryConfig {

    public static String TAG = Configs.universalTAG + "ProjectDataLocalLibraryConfig";
    public static void setDataForFirstTimeProjectCreation(String projectID) {
        Log.i(TAG, "setDataForFirstTimeProjectCreation: " + projectID);
        writeDataFile(projectID, "[{\"dependency\":\"androidx.activity:activity:1.10.1\",\"dexPath\":\"" + Configs.dexBuiltInLibFolderDir + "activity-1.10.1.dex\",\"jarPath\":\"" + Configs.jarBuiltInLibFolderDir + "activity-1.10.1/classes.jar\",\"name\":\"activity-1.10.1\",\"manifestPath\":\"" + Configs.jarBuiltInLibFolderDir + "activity-1.10.1/AndroidManifest.xml\",\"packageName\":\"androidx.activity\",\"resPath\":\"" + Configs.jarBuiltInLibFolderDir + "activity-1.10.1/res\"}]");
    }

    public static void writeDataFile(String projectID, String content) {
        Log.i(TAG, "writeDataFile: " + " " + projectID + " " + content);
        FileUtils.writeTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/local_library", content);
    }
}
