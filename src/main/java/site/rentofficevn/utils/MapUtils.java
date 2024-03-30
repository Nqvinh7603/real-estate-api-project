package site.rentofficevn.utils;

import java.util.Map;

public class MapUtils {
    public static <T> T getObject(Map<String, Object> maps, String key, Class<T> tClass) {
        try {
            Object obj = maps.getOrDefault(key, null);
            if (obj != null) {
                if (tClass.getTypeName().equals("java.lang.Long")) {
                    obj = Long.valueOf(obj.toString());
                } else if (tClass.getTypeName().equals("java.lang.Integer")) {
                    obj = Integer.valueOf(obj.toString());
                }
                return obj != null ? tClass.cast(obj) : null;
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }
}
