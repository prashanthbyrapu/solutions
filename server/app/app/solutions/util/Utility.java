package app.solutions.util;

import com.google.gson.*;
import org.bson.types.ObjectId;

import java.lang.reflect.Type;

public class Utility {

    /**
     * converts an object to JSON
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {

        Gson gson = new GsonBuilder().
                 registerTypeAdapter(ObjectId.class, new JsonSerializer<ObjectId>() {
                     @Override
                     public JsonElement serialize(ObjectId src, Type typeOfSrc, JsonSerializationContext context) {
                         return new JsonPrimitive(src.toString());
                     }
                 })
                .create();



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
        Gson gson = new GsonBuilder().
                registerTypeAdapter(ObjectId.class, new JsonDeserializer<ObjectId>() {
                    @Override
                    public ObjectId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new ObjectId(json.getAsJsonPrimitive().getAsString());
                    }
                })
                .create();

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
