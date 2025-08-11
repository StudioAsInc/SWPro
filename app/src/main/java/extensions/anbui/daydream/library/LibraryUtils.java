package extensions.anbui.daydream.library;

import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import extensions.anbui.daydream.configs.Configs;
import extensions.anbui.daydream.project.ProjectDataBuildConfig;
import extensions.anbui.daydream.project.ProjectDataConfig;
import extensions.anbui.daydream.project.ProjectDataDayDream;
import extensions.anbui.daydream.project.ProjectDataLibrary;

public class LibraryUtils {

    public static boolean isAllowUseWindowInsetsHandling(String projectID) {
        return (ProjectDataLibrary.isEnabledAppCompat(projectID)
                && !ProjectDataBuildConfig.isUseJava7(projectID));
    }

    public static boolean isAllowUseAndroidXWorkManager(String projectID) {
        return ProjectDataLibrary.isEnabledAppCompat(projectID);
    }

   public static boolean isAllowUseAndroidXMedia3(String projectID) {
       return (ProjectDataLibrary.isEnabledAppCompat(projectID)
               && ProjectDataConfig.isMinSDKNewerThan23(projectID)
               && !ProjectDataBuildConfig.isUseJava7(projectID));
   }

    public static boolean isAllowUseAndroidXBrowser(String projectID) {
        return ProjectDataLibrary.isEnabledAppCompat(projectID);
    }

    public static boolean isAllowUseAndroidXCredentialManager(String projectID) {
        return (ProjectDataLibrary.isEnabledAppCompat(projectID)
                && ProjectDataConfig.isMinSDKNewerThan23(projectID)
                && !ProjectDataBuildConfig.isUseJava7(projectID));
    }

    public static boolean isAllowUseGoogleAnalytics(String projectID) {
        return (ProjectDataLibrary.isEnabledAppCompat(projectID)
                && ProjectDataConfig.isMinSDKNewerThan23(projectID)
                && !ProjectDataBuildConfig.isUseJava7(projectID)
                && ProjectDataLibrary.isEnabledFirebase(projectID));
    }

    public static boolean isAllowUseShizuku(String projectID) {
        return ProjectDataLibrary.isEnabledAppCompat(projectID);
    }
}
