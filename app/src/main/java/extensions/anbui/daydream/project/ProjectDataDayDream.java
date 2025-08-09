package extensions.anbui.daydream.project;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.Map;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;

public class ProjectDataDayDream {

    //Activity type

    public static String getActivityType(String projectID, String activityName) {
        String result = getDataString(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "activityType");
        if (result == null) return "";
        return result;
    }

    public static void setActivityType(String projectID, String activityName, String value) {
        setDataString(projectID, activityName, "activityType", value);
    }

    //Activity settings

    public static boolean isEnableEdgeToEdge(String projectID, String activityName) {
        return getDataBoolean(projectID, activityName, "edgeToEdge");
    }

    public static void setEnableEdgeToEdge(String projectID, String activityName, boolean isEnable) {
        setDataBoolean(projectID, activityName, "edgeToEdge", isEnable);
    }

    public static boolean isEnableWindowInsetsHandling(String projectID, String activityName) {
        return getDataBoolean(projectID, activityName, "windowInsetsHandling");
    }

    public static void setEnableWindowInsetsHandling(String projectID, String activityName, boolean isEnable) {
        setDataBoolean(projectID, activityName, "windowInsetsHandling", isEnable);
    }

    public static boolean isDisableAutomaticPermissionRequests(String projectID, String activityName) {
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "disableAutomaticPermissionRequests");
    }

    public static void setDisableAutomaticPermissionRequests(String projectID, String activityName, boolean isEnable) {
        setDataBoolean(projectID, activityName, "disableAutomaticPermissionRequests", isEnable);
    }

    public static boolean isContentProtection(String projectID, String activityName) {
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "contentProtection");
    }

    public static void setContentProtection(String projectID, String activityName, boolean isEnable) {
        setDataBoolean(projectID, activityName, "contentProtection", isEnable);
    }

    public static boolean isImportWorkManager(String projectID, String activityName) {
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "importWorkManager");
    }

    public static void setImportWorkManager(String projectID, String activityName, boolean isEnable) {
        setDataBoolean(projectID, activityName, "importWorkManager", isEnable);
    }

    public static boolean isImportAndroidXMedia3(String projectID, String activityName) {
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "importAndroidXMedia3");
    }

    public static void setImportAndroidXMedia3(String projectID, String activityName, boolean isEnable) {
        setDataBoolean(projectID, activityName, "importAndroidXMedia3", isEnable);
    }

    //Universal settings

    public static boolean isEnableDayDream(String projectID) {
        return getUniversalSettings(projectID, "isEnable");
    }

    public static void setEnableDayDream(String projectID, boolean isEnable) {
        setUniversalSettings(projectID, "isEnable", isEnable);
    }

    public static boolean isUniversalEdgeToEdge(String projectID) {
        return getUniversalSettings(projectID, "edgeToEgde");
    }

    public static void setUniversalEdgeToEdge(String projectID, boolean isEnable) {
        setUniversalSettings(projectID, "edgeToEgde", isEnable);
    }

    public static boolean isUniversalWindowInsetsHandling(String projectID) {
        return getUniversalSettings(projectID, "windowInsetsHandling");
    }

    public static void setUniversalWindowInsetsHandling(String projectID, boolean isEnable) {
        setUniversalSettings(projectID, "windowInsetsHandling", isEnable);
    }

    public static boolean isUniversalContentProtection(String projectID) {
        return getUniversalSettings(projectID, "contentProtection");
    }

    public static void setUniversalContentProtection(String projectID, boolean isEnable) {
        setUniversalSettings(projectID, "contentProtection", isEnable);
    }

    public static boolean isEnableAndroidTextColorRemoval(String projectID) {
        return getUniversalSettings(projectID, "androidTextColorRemoval");
    }

    public static void setEnableAndroidTextColorRemoval(String projectID, boolean isEnable) {
        setUniversalSettings(projectID, "androidTextColorRemoval", isEnable);
    }

    public static boolean isUniversalDisableAutomaticPermissionRequests(String projectID) {
        return getUniversalSettings(projectID, "disableAutomaticPermissionRequests");
    }

    public static void setUniversalDisableAutomaticPermissionRequests(String projectID, boolean isEnable) {
        setUniversalSettings(projectID, "disableAutomaticPermissionRequests", isEnable);
    }

    public static boolean isForceAddWorkManager(String projectID) {
        return getUniversalSettings(projectID, "forceAddWorkManager");
    }

    public static void setForceAddWorkManager(String projectID, boolean isEnable) {
        setUniversalSettings(projectID, "forceAddWorkManager", isEnable);
    }

    public static boolean isUniversalUseMedia3(String projectID) {
        return getUniversalSettings(projectID, "useMedia3");
    }

    public static void setUniversalUseMedia3(String projectID, boolean isEnable) {
        setUniversalSettings(projectID, "useMedia3", isEnable);
    }

    public static boolean isUninversalEnableOnBackInvokedCallback(String projectID) {
        return getUniversalSettings(projectID, "enableOnBackInvokedCallback");
    }

    public static void setUninversalEnableOnBackInvokedCallback(String projectID, boolean isEnable) {
        setUniversalSettings(projectID, "enableOnBackInvokedCallback", isEnable);
    }
    //Read and write universal settings
    public static boolean getUniversalSettings(String projectID, String settingName) {
        return getDataBoolean(projectID, "Universal", settingName);
    }

    public static void setUniversalSettings(String projectID, String settingName, boolean isEnable) {
        setDataBoolean(projectID, "Universal", settingName, isEnable);
    }

    //Read and write data

    public static boolean getDataBoolean(String projectID, String toplevelkey, String key) {
        JsonObject json = JsonParser.parseString(readDayDreamDataFile(projectID)).getAsJsonObject();
        if (json.has(toplevelkey)) {
            JsonObject edge = json.getAsJsonObject(toplevelkey);
            try {
                return edge.get(key).getAsBoolean();
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static void setDataBoolean(String projectID, String toplevelkey, String key, boolean value) {
        JsonObject json = JsonParser.parseString(readDayDreamDataFile(projectID)).getAsJsonObject();
        if (!json.has(toplevelkey)) {
            JsonObject edge = new JsonObject();
            edge.addProperty(key, value);
            json.add(toplevelkey, edge);
        } else {
            JsonObject edge = json.getAsJsonObject(toplevelkey);
            edge.addProperty(key, value);
        }
        writeDayDreamDataFile(projectID, new Gson().toJson(json));
    }

    public static String getDataString(String projectID, String toplevelkey, String key) {
        JsonObject json = JsonParser.parseString(readDayDreamDataFile(projectID)).getAsJsonObject();
        if (json.has(toplevelkey)) {
            JsonObject edge = json.getAsJsonObject(toplevelkey);
            try {
                return edge.get(key).getAsString();
            } catch (Exception ignored) {
            }
        }
        return "";
    }

    public static void setDataString(String projectID, String toplevelkey, String key, String value) {
        JsonObject json = JsonParser.parseString(readDayDreamDataFile(projectID)).getAsJsonObject();
        if (!json.has(toplevelkey)) {
            JsonObject edge = new JsonObject();
            edge.addProperty(key, value);
            json.add(toplevelkey, edge);
        } else {
            JsonObject edge = json.getAsJsonObject(toplevelkey);
            edge.addProperty(key, value);
        }
        writeDayDreamDataFile(projectID, new Gson().toJson(json));
    }

    public static Map<String, Object> readData(String projectID) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(readDayDreamDataFile(projectID), type);
    }

    public static String readDayDreamDataFile(String projectID) {
        String contentProjectFile = FileUtils.readTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/DataDayDream.json");
        if (contentProjectFile.isEmpty()) contentProjectFile = "{}";
        return contentProjectFile;
    }

    public static void writeDayDreamDataFile(String projectID, String content) {
        FileUtils.writeTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/DataDayDream.json", content);
    }
}
