package app.solutions.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;

public class Utility {

    /**
     * converts an object to JSON
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {

        Gson gson = new GsonBuilder().create();

        String json = gson.toJson(obj, obj.getClass());
        return json;
    }

    /**
     * creates an Java object from Json
     *
     * @param json
     * @param clazz
     * @return
     */
    public static Object fromJson(String json, Class clazz) {
        // JsonElement el = new JsonPrimitive(json);
        Gson gson = new GsonBuilder().create();

        Object obj = gson.fromJson(json, clazz);
        return obj;
    }


    public static Class getClassName(String collectionName)
            throws ClassNotFoundException {
        String className = "app.solutions.model." + collectionName;

        Class collectionClass = Class.forName(className);

        return collectionClass;
    }
}
