package extensions.anbui.daydream.json;

import android.util.Log;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

import extensions.anbui.daydream.configs.Configs;

public class JsonUtils {
    public static String TAG = Configs.universalTAG + "JsonUtils";
    public static Map<String, Object> covertoMap(String json) {
        Log.i(TAG, "covertoMap: " + json);
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
