package extensions.anbui.daydream.configs;

import android.app.Activity;

import pro.sketchware.SketchApplication;

public class Configs {
    public static final String universalTAG = "DayDream";
    public static final String mainDataDir = "/.sketchware/";
    public static final String projectMySourceFolderDir = mainDataDir + "mysc/";
    public static final String projectInfoFolderDir = mainDataDir + "mysc/list/";
    public static final String projectDataFolderDir = mainDataDir + "data/";
    public static final String projectLocalLibFolderDir = mainDataDir + "libs/local_libs/";
    public static final String resFolderDir = mainDataDir + "resources/";
    public static final String resBlocksFolderDir = resFolderDir + "block/";
    public static final String resFontsFolderDir = resFolderDir + "fonts/";
    public static final String resIconsFolderDir = resFolderDir + "icons/";
    public static final String resImagesFolderDir = resFolderDir + "images/";
    public static final String resSoundsFolderDir = resFolderDir + "sounds/";
    public static final String resWidgetsFolderDir = resFolderDir + "widgets/";
    public static final String appDataFolderDir = SketchApplication.getContext().getFilesDir().toString();
    public static final String jarBuiltInLibFolderDir = appDataFolderDir + "/libs/libs/";
    public static final String dexBuiltInLibFolderDir = appDataFolderDir + "/libs/dexs/";
    public static final String tempDayDreamFolderDir = mainDataDir + "daydreamtemp/";
    public static final String tempBackupDayDreamFolderDir = mainDataDir + "daydreamtemp/backup/";
    public static final String recycleBinDayDreamFolderDir = mainDataDir + "recyclebin/";
    public static final String encryptionKey = "sketchwaresecure";
    public static String currentProjectID = "";
    public static Activity mainActivity;
}
