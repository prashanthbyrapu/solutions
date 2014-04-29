package app.solutions.util;

import com.google.gson.*;
import org.bson.types.ObjectId;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {


    public static String getTextField(String field) {
        return field + "_text";
    }

    /**
     * converts an object to JSON
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {

        if (obj == null) return "";

        Gson gson = new GsonBuilder().
                registerTypeAdapter(ObjectId.class, new JsonSerializer<ObjectId>() {
                    @Override
                    public JsonElement serialize(ObjectId src, Type typeOfSrc, JsonSerializationContext context) {
                        return new JsonPrimitive(src.toString());
                    }
                })
                .setDateFormat("MMM dd, yyyy")
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
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        String dateString = json.getAsString();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                        Date resultDate = null;
                        try {
                            resultDate = sdf.parse(dateString);
                        } catch (ParseException e) {
                            // leave it.
                        }

                        sdf = new SimpleDateFormat("MMM dd, yyyy HH:mm:ss a");
                        try {
                            resultDate = sdf.parse(dateString);
                        } catch (ParseException e) {
                            // leave it.
                        }

                        sdf = new SimpleDateFormat("MMM dd, yyyy");
                        try {
                            resultDate = sdf.parse(dateString);
                        } catch (ParseException e) {
                            // leave it.
                        }
                        return resultDate;
                    }
                })
                        //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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
