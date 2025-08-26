package extensions.anbui.daydream.project;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.json.JsonUtils;

public class ProjectDataBuildConfig {

    public static String TAG = Configs.universalTAG + "ProjectDataBuildConfig";

    public static void setDataForFirstTimeProjectCreation(String projectID) {
        Log.i(TAG, "setDataForFirstTimeProjectCreation: " + projectID);
        writeDataFile(projectID, "{\"dexer\":\"D8\",\"classpath\":\"\",\"enable_logcat\":\"true\",\"no_http_legacy\":\"false\",\"android_jar\":\"\",\"no_warn\":\"true\",\"java_ver\":\"17\"}");
    }

    public static boolean isUseJava7(String projectID) {
        Log.i(TAG, "isUseJava7: " + projectID);
        Map<String, Object> map = getBuildConfigData(projectID);
        if (map == null) return true;
        return Objects.equals(map.get("java_ver"), "1.7");
    }

    public static void setDataString(String projectID, String key, String value) {
        Log.i(TAG, "setDataString: " + projectID + " " + key + " " + value);
        JsonObject json = JsonParser.parseString(readDataFile(projectID)).getAsJsonObject();
        json.addProperty(key, value);
        writeDataFile(projectID, new Gson().toJson(json));
    }

    public static Map<String, Object> getBuildConfigData(String projectID) {
        Log.i(TAG, "getBuildConfigData: " + projectID);
        return JsonUtils.covertoMap(readDataFile(projectID));
    }

    public static String readDataFile(String projectID) {
        String contentProjectFile = FileUtils.readTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/build_config");
        if (contentProjectFile.isEmpty()) contentProjectFile = "{}";
        Log.i(TAG, "readDataFile: " + contentProjectFile);
        return contentProjectFile;
    }

    public static void writeDataFile(String projectID, String content) {
        Log.i(TAG, "writeDataFile: " + projectID + " " + content);
        FileUtils.writeTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/build_config", content);
    }
}
