package extensions.anbui.daydream.library;

import android.util.Log;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LibraryUtils {
    private static final Set<String> FORCE_ADD_RES_BUILT_IN_LIBS_LIST = new HashSet<>(Arrays.asList(
            "activity-1.10.1",
            "exoplayer-1.0.0"
    ));

    public static boolean isForeAddResourcesFromBuiltInLib(String libraryName) {
        Log.i("LibraryUtils", "isForeAddResourcesFromBuiltInLib: " + libraryName);
        return FORCE_ADD_RES_BUILT_IN_LIBS_LIST.contains(libraryName);
    }

    public static String getPackageNameFromBuiltInLib(String libraryName) {
        return switch (libraryName) {
            case "activity-1.10.1" -> "androidx.activity";
            case "exoplayer-1.0.0" -> "androidx.media.exoplayer";
            default -> "";
        };
    }
}
