package extensions.anbui.daydream.project;

import android.util.Log;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.Map;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;

public class ProjectDataDayDream {

    public static String TAG = Configs.universalTAG + "ProjectDataDayDream";

    //Activity type

    public static String getActivityType(String projectID, String activityName) {
        Log.i(TAG, "getActivityType: " + projectID + " " + activityName);
        String result = getDataString(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "activityType");
        if (result == null) return "";
        return result;
    }

    public static void setActivityType(String projectID, String activityName, String value) {
        Log.i(TAG, "setActivityType: " + projectID + " " + activityName + " " + value);
        setDataString(projectID, activityName, "activityType", value);
    }

    //Activity settings

    public static boolean isEnableEdgeToEdge(String projectID, String activityName) {
        Log.i(TAG, "isEnableEdgeToEdge: " + projectID + " " + activityName);
        return getDataBoolean(projectID, activityName, "edgeToEdge");
    }

    public static void setEnableEdgeToEdge(String projectID, String activityName, boolean isEnable) {
        Log.i(TAG, "setEnableEdgeToEdge: " + projectID + " " + activityName + " " + isEnable);
        setDataBoolean(projectID, activityName, "edgeToEdge", isEnable);
    }

    public static boolean isEnableWindowInsetsHandling(String projectID, String activityName) {
        Log.i(TAG, "isEnableWindowInsetsHandling: " + projectID + " " + activityName);
        return getDataBoolean(projectID, activityName, "windowInsetsHandling");
    }

    public static void setEnableWindowInsetsHandling(String projectID, String activityName, boolean isEnable) {
        Log.i(TAG, "setEnableWindowInsetsHandling: " + projectID + " " + activityName + " " + isEnable);
        setDataBoolean(projectID, activityName, "windowInsetsHandling", isEnable);
    }

    public static boolean isDisableAutomaticPermissionRequests(String projectID, String activityName) {
        Log.i(TAG, "isDisableAutomaticPermissionRequests: " + projectID + " " + activityName);
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "disableAutomaticPermissionRequests");
    }

    public static void setDisableAutomaticPermissionRequests(String projectID, String activityName, boolean isEnable) {
        Log.i(TAG, "setDisableAutomaticPermissionRequests: " + projectID + " " + activityName + " " + isEnable);
        setDataBoolean(projectID, activityName, "disableAutomaticPermissionRequests", isEnable);
    }

    public static boolean isContentProtection(String projectID, String activityName) {
        Log.i(TAG, "isContentProtection: " + projectID + " " + activityName);
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "contentProtection");
    }

    public static void setContentProtection(String projectID, String activityName, boolean isEnable) {
        Log.i(TAG, "setContentProtection: " + projectID + " " + activityName + " " + isEnable);
        setDataBoolean(projectID, activityName, "contentProtection", isEnable);
    }

    public static boolean isImportWorkManager(String projectID, String activityName) {
        Log.i(TAG, "isImportWorkManager: " + projectID + " " + activityName);
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "importWorkManager");
    }

    public static void setImportWorkManager(String projectID, String activityName, boolean isEnable) {
        Log.i(TAG, "setImportWorkManager: " + projectID + " " + activityName + " " + isEnable);
        setDataBoolean(projectID, activityName, "importWorkManager", isEnable);
    }

    public static boolean isImportAndroidXMedia3(String projectID, String activityName) {
        Log.i(TAG, "isImportAndroidXMedia3: " + projectID + " " + activityName);
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "importAndroidXMedia3");
    }

    public static void setImportAndroidXMedia3(String projectID, String activityName, boolean isEnable) {
        Log.i(TAG, "setImportAndroidXMedia3: " + projectID + " " + activityName + " " + isEnable);
        setDataBoolean(projectID, activityName, "importAndroidXMedia3", isEnable);
    }

    public static boolean isImportAndroidXCredentialManager(String projectID, String activityName) {
        Log.i(TAG, "isImportAndroidXCredentialManager: " + projectID + " " + activityName);
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "importAndroidXCredentialManager");
    }

    public static void setImportAndroidXCredentialManager(String projectID, String activityName, boolean isEnable) {
        Log.i(TAG, "setImportAndroidXCredentialManager: " + projectID + " " + activityName + " " + isEnable);
        setDataBoolean(projectID, activityName, "importAndroidXCredentialManager", isEnable);
    }

    public static boolean isImportAndroidXBrowser(String projectID, String activityName) {
        Log.i(TAG, "isImportAndroidXBrowser: " + projectID + " " + activityName);
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "importAndroidXBrowser");
    }

    public static void setImportAndroidXBrowser(String projectID, String activityName, boolean isEnable) {
        Log.i(TAG, "setImportAndroidXBrowser: " + projectID + " " + activityName + " " + isEnable);
        setDataBoolean(projectID, activityName, "importAndroidXBrowser", isEnable);
    }

    public static boolean isImportShizuku(String projectID, String activityName) {
        Log.i(TAG, "isImportShizuku: " + projectID + " " + activityName);
        return getDataBoolean(projectID, ProjectUtils.convertJavaNameToXMLName(activityName), "importShizuku");
    }

    public static void setImportShizuku(String projectID, String activityName, boolean isEnable) {
        Log.i(TAG, "setImportShizuku: " + projectID + " " + activityName + " " + isEnable);
        setDataBoolean(projectID, activityName, "importShizuku", isEnable);
    }

    //Universal settings

    public static boolean isEnableDayDream(String projectID) {
        Log.i(TAG, "isEnableDayDream: " + projectID);
        return getUniversalSettings(projectID, "isEnable");
    }

    public static void setEnableDayDream(String projectID, boolean isEnable) {
        Log.i(TAG, "setEnableDayDream: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "isEnable", isEnable);
    }

    public static boolean isUniversalEdgeToEdge(String projectID) {
        Log.i(TAG, "isUniversalEdgeToEdge: " + projectID);
        return getUniversalSettings(projectID, "edgeToEgde");
    }

    public static void setUniversalEdgeToEdge(String projectID, boolean isEnable) {
        Log.i(TAG, "setUniversalEdgeToEdge: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "edgeToEgde", isEnable);
    }

    public static boolean isUniversalWindowInsetsHandling(String projectID) {
        Log.i(TAG, "isUniversalWindowInsetsHandling: " + projectID);
        return getUniversalSettings(projectID, "windowInsetsHandling");
    }

    public static void setUniversalWindowInsetsHandling(String projectID, boolean isEnable) {
        Log.i(TAG, "setUniversalWindowInsetsHandling: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "windowInsetsHandling", isEnable);
    }

    public static boolean isUniversalContentProtection(String projectID) {
        Log.i(TAG, "isUniversalContentProtection: " + projectID);
        return getUniversalSettings(projectID, "contentProtection");
    }

    public static void setUniversalContentProtection(String projectID, boolean isEnable) {
        Log.i(TAG, "setUniversalContentProtection: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "contentProtection", isEnable);
    }

    public static boolean isEnableAndroidTextColorRemoval(String projectID) {
        Log.i(TAG, "isEnableAndroidTextColorRemoval: " + projectID);
        return getUniversalSettings(projectID, "androidTextColorRemoval");
    }

    public static void setEnableAndroidTextColorRemoval(String projectID, boolean isEnable) {
        Log.i(TAG, "setEnableAndroidTextColorRemoval: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "androidTextColorRemoval", isEnable);
    }

    public static boolean isUniversalDisableAutomaticPermissionRequests(String projectID) {
        Log.i(TAG, "isUniversalDisableAutomaticPermissionRequests: " + projectID);
        return getUniversalSettings(projectID, "disableAutomaticPermissionRequests");
    }

    public static void setUniversalDisableAutomaticPermissionRequests(String projectID, boolean isEnable) {
        Log.i(TAG, "setUniversalDisableAutomaticPermissionRequests: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "disableAutomaticPermissionRequests", isEnable);
    }

    public static boolean isForceAddWorkManager(String projectID) {
        Log.i(TAG, "isForceAddWorkManager: " + projectID);
        return getUniversalSettings(projectID, "forceAddWorkManager");
    }

    public static void setForceAddWorkManager(String projectID, boolean isEnable) {
        Log.i(TAG, "setForceAddWorkManager: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "forceAddWorkManager", isEnable);
    }

    public static boolean isUniversalUseMedia3(String projectID) {
        Log.i(TAG, "isUniversalUseMedia3: " + projectID);
        return getUniversalSettings(projectID, "useMedia3");
    }

    public static void setUniversalUseMedia3(String projectID, boolean isEnable) {
        Log.i(TAG, "setUniversalUseMedia3: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "useMedia3", isEnable);
    }

    public static boolean isUniversalUseAndroidXBrowser(String projectID) {
        Log.i(TAG, "isUniversalUseAndroidXBrowser: " + projectID);
        return getUniversalSettings(projectID, "useAndroidXBrowser");
    }

    public static void setUniversalUseAndroidXBrowser(String projectID, boolean isEnable) {
        Log.i(TAG, "setUniversalUseAndroidXBrowser: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "useAndroidXBrowser", isEnable);
    }

    public static boolean isUniversalUseAndroidXCredentialManager(String projectID) {
        Log.i(TAG, "isUniversalUseAndroidXCredentialManager: " + projectID);
        return getUniversalSettings(projectID, "useAndroidXCredentialManager");
    }

    public static void setUniversalUseAndroidXCredentialManager(String projectID, boolean isEnable) {
        Log.i(TAG, "setUniversalUseAndroidXCredentialManager: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "useAndroidXCredentialManager", isEnable);
    }

    public static boolean isUninversalEnableOnBackInvokedCallback(String projectID) {
        Log.i(TAG, "isUninversalEnableOnBackInvokedCallback: " + projectID);
        return getUniversalSettings(projectID, "enableOnBackInvokedCallback");
    }

    public static void setUninversalEnableOnBackInvokedCallback(String projectID, boolean isEnable) {
        Log.i(TAG, "setUninversalEnableOnBackInvokedCallback: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "enableOnBackInvokedCallback", isEnable);
    }

    public static boolean isUseGoogleAnalytics(String projectID) {
        Log.i(TAG, "isUseGoogleAnalytics: " + projectID);
        return getUniversalSettings(projectID, "useGoogleAnalytics");
    }

    public static void setUseGoogleAnalytics(String projectID, boolean isEnable) {
        Log.i(TAG, "setUseGoogleAnalytics: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "useGoogleAnalytics", isEnable);
    }

    public static boolean isUseShizuku(String projectID) {
        Log.i(TAG, "isUseShizuku: " + projectID);
        return getUniversalSettings(projectID, "useShizuku");
    }

    public static void setUseShizuku(String projectID, boolean isEnable) {
        Log.i(TAG, "setUseShizuku: " + projectID + " " + isEnable);
        setUniversalSettings(projectID, "useShizuku", isEnable);
    }
    //Read and write universal settings
    public static boolean getUniversalSettings(String projectID, String settingName) {
        Log.i(TAG, "getUniversalSettings: " + projectID + " " + settingName);
        return getDataBoolean(projectID, "Universal", settingName);
    }

    public static void setUniversalSettings(String projectID, String settingName, boolean isEnable) {
        Log.i(TAG, "setUniversalSettings: " + projectID + " " + settingName + " " + isEnable);
        setDataBoolean(projectID, "Universal", settingName, isEnable);
    }

    //Read and write data

    public static boolean getDataBoolean(String projectID, String toplevelkey, String key) {
        Log.i(TAG, "getDataBoolean: " + projectID + " " + toplevelkey + " " + key);
        JsonObject json = JsonParser.parseString(readDayDreamDataFile(projectID)).getAsJsonObject();
        if (json.has(toplevelkey)) {
            JsonObject edge = json.getAsJsonObject(toplevelkey);
            try {
                Log.i(TAG, "getDataBoolean: " + edge.get(key).getAsBoolean());
                return edge.get(key).getAsBoolean();
            } catch (Exception e) {
                Log.e(TAG, "getDataBoolean: " + e.getMessage());
            }
        }
        return false;
    }

    public static void setDataBoolean(String projectID, String toplevelkey, String key, boolean value) {
        Log.i(TAG, "setDataBoolean: " + projectID + " " + toplevelkey + " " + key + " " + value);
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
        Log.i(TAG, "getDataString: " + projectID + " " + toplevelkey + " " + key);
        JsonObject json = JsonParser.parseString(readDayDreamDataFile(projectID)).getAsJsonObject();
        if (json.has(toplevelkey)) {
            JsonObject edge = json.getAsJsonObject(toplevelkey);
            try {
                Log.i(TAG, "getDataString: " + edge.get(key).getAsString());
                return edge.get(key).getAsString();
            } catch (Exception e) {
                Log.e(TAG, "getDataString: " + e.getMessage());
            }
        }
        return "";
    }

    public static void setDataString(String projectID, String toplevelkey, String key, String value) {
        Log.i(TAG, "setDataString: " + projectID + " " + toplevelkey + " " + key + " " + value);
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
        Log.i(TAG, "readData: " + projectID);
        return gson.fromJson(readDayDreamDataFile(projectID), type);
    }

    public static String readDayDreamDataFile(String projectID) {
        String contentProjectFile = FileUtils.readTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/DataDayDream.json");
        if (contentProjectFile.isEmpty()) contentProjectFile = "{}";
        Log.i(TAG, "readDayDreamDataFile: " + projectID + " " + contentProjectFile);
        return contentProjectFile;
    }

    public static void writeDayDreamDataFile(String projectID, String content) {
        Log.i(TAG, "writeDayDreamDataFile: " + projectID + " " + content);
        FileUtils.writeTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/DataDayDream.json", content);
    }
}
