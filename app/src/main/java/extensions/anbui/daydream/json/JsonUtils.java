package extensions.anbui.daydream.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonUtils {
    public static Map<String, Object> covertoMap(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
