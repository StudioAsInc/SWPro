package extensions.anbui.daydream.settings;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;
import java.util.Objects;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.json.JsonUtils;

public class DayDreamSkSettings {

    public static String getBackupDir() {
        Map<String, Object> map = getSettingsData();
        if (map == null) return "/.sketchware/backups/";
        if (!map.containsKey("backup-dir")) return "/.sketchware/backups/";
        return Objects.requireNonNull(map.get("backup-dir")).toString();
    }

    public static void setDataString(String key, String value) {
        JsonObject json = JsonParser.parseString(readDataFile()).getAsJsonObject();
        json.addProperty(key, value);
        writeDataFile(new Gson().toJson(json));
    }

    public static Map<String, Object> getSettingsData() {
        return JsonUtils.covertoMap(readDataFile());
    }

    public static String readDataFile() {
        String contentProjectFile = FileUtils.readTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + "/settings.json");
        if (contentProjectFile.isEmpty()) contentProjectFile = "{}";
        return contentProjectFile;
    }

    public static void writeDataFile(String content) {
        FileUtils.writeTextFile(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + "/settings.json", content);
    }
}
