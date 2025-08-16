package extensions.anbui.daydream.project;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.json.JsonUtils;

public class ProjectDataConfig {

    public static void setDataForFirstTimeProjectCreation(String projectID, boolean enableViewBinding, boolean minsdk24) {
        String finalresult = "{";
        if (minsdk24) {
            finalresult += "\"min_sdk\":\"24\"";
        } else {
            finalresult += "\"min_sdk\":\"21\"";
        }
        if (enableViewBinding) {
            finalresult += ",\"enable_viewbinding\":\"true\"";
        } else {
            finalresult += ",\"enable_viewbinding\":\"false\"";
        }
        finalresult += ",\"xml_command\":\"true\"}";
        writeDataFile(projectID, finalresult);

    }

    public static boolean isMinSDKNewerThan23(String projectID) {
        Map<String, Object> map = getProjectConfigData(projectID);
        if (map == null) return false;
        if (!map.containsKey("min_sdk")) return false;
        return Integer.parseInt(Objects.requireNonNull(map.get("min_sdk")).toString()) > 23;
    }

    public static void setDataString(String projectID, String key, String value) {
        JsonObject json = JsonParser.parseString(readDataFile(projectID)).getAsJsonObject();
        json.addProperty(key, value);
        writeDataFile(projectID, new Gson().toJson(json));
    }

    public static Map<String, Object> getProjectConfigData(String projectID) {
        return JsonUtils.covertoMap(readDataFile(projectID));
    }

    public static String readDataFile(String projectID) {
        String contentProjectFile = FileUtils.readTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/project_config");
        if (contentProjectFile.isEmpty()) contentProjectFile = "{}";
        return contentProjectFile;
    }

    public static void writeDataFile(String projectID, String content) {
        FileUtils.writeTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/project_config", content);
    }
}
