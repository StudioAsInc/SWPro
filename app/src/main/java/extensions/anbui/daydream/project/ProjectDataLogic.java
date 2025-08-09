package extensions.anbui.daydream.project;

import android.util.Log;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.file.FileUtils;
import extensions.anbui.daydream.tool.DayDreamTool;

public class ProjectDataLogic {
    public static boolean isThisActivityHaveOnBackPressed(String projectID, String activityName) {
        //activityName format: MainActivity
        String result = readActivityData(projectID, ProjectUtils.convertXMLNameToJavaName(activityName, false));
        if (result == null) return false;
        return result.contains("onBackPressed");

    }

    public static String readActivityData(String projectID, String activityName) {
        return ProjectData.readFullDataWithDataType(activityName, read(projectID));
    }

    public static String read(String projectID) {
        DayDreamTool.copyToTemp(FileUtils.getInternalStorageDir() + Configs.projectDataFolderDir + projectID + "/logic");
        return ProjectDataDecryptor.decryptProjectFile(DayDreamTool.getTempFilePath("logic"));
    }
}
