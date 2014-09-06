package info.testing.automated.utils;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * Author: Serhii Kuts
 * Date: 9/6/2014
 * Time: 6:09 PM
 */
public final class JsonUtil {

    private JsonUtil() {
    }

    public static <T> T fromJSON(final TypeReference<T> type, final String jsonObject) {
        T data;

        try {
            data = new ObjectMapper().readValue(jsonObject, type);
        } catch (Exception ignore) {
            data = null;
        }

        return data;
    }
}
