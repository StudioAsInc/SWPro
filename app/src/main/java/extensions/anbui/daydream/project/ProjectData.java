package extensions.anbui.daydream.project;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.json.JsonUtils;
import shadow.bundletool.com.android.tools.r8.internal.S;

public class ProjectData {

    public static void setDataForFirstTimeProjectCreation(String projectID) {
        Configs.currentProjectID = projectID;
        //There is some code that will temporarily block after the project is created so wait a second.
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                ProjectDataBuildConfig.setDataForFirstTimeProjectCreation(projectID);
//                ProjectDataLocalLibraryConfig.setDataForFirstTimeProjectCreation(projectID);
            } catch (InterruptedException e) {
                Log.e("LibraryUtils", "ProjectDataLocalLibraryConfig: " + e.getMessage());
            }
        }).start();
    }

    public static Map<String, Object> readFirstLineDataWithDataTypeToMap(String dataType, String data) {
        return JsonUtils.covertoMap(readFirstLineDataWithDataType(dataType, data));
    }

    @Nullable
    public static String readFirstLineDataWithDataType(String dataType, String data) {
        //Find the location of type
        int compatIndex = data.indexOf(dataType);
        if (compatIndex == -1) return null;

        //Find the nearest { after @compat
        int jsonStart = data.indexOf("{", compatIndex);
        int braceCount = 0;
        int jsonEnd = -1;

        //Find the correct } at the end of JSON
        for (int i = jsonStart; i < data.length(); i++) {
            char c = data.charAt(i);
            if (c == '{') braceCount++;
            else if (c == '}') braceCount--;

            if (braceCount == 0) {
                jsonEnd = i;
                break;
            }
        }

        if (jsonStart == -1 || jsonEnd == -1) return null;

        return data.substring(jsonStart, jsonEnd + 1);
    }

    public static Map<String, Object> readFullDataWithDataTypeToMap(String dataType, String data) {
        return JsonUtils.covertoMap(readFullDataWithDataType(dataType, data));
    }

    @Nullable
    public static String readFullDataWithDataType(String dataType, String data) {
        //Find the starting position of the activity
        int startIndex = data.indexOf(dataType);
        if (startIndex == -1) return null;

        //Find the end position (when encountering a new activity or running out of data)
        int nextActivityIndex = data.indexOf("@", startIndex + dataType.length());
        if (nextActivityIndex == -1) {
            nextActivityIndex = data.length(); //No new activity, get all
        }

        //Cut the entire data block of the activity
        return data.substring(startIndex, nextActivityIndex).trim();
    }
}
