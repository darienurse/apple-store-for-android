package com.itunesstoreviewer.app.ItunesRssItemClasses;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Certain Categories like Movies and Songs store their Link field as JsonArray whereas others store it as
 * a JsonObject. The LinkDeserializer allows for both cases to be process without causing Gson to crash.
 */
public class Deserializer {
    public static Gson buildGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Link[].class, new LinkDeserializer());
        return gsonBuilder.create();
    }

    private static class LinkDeserializer implements JsonDeserializer<Link[]>{
        @Override
        public Link[] deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement instanceof JsonArray) {
                return new Gson().fromJson(jsonElement, Link[].class);
            }
            Link link = jsonDeserializationContext.deserialize(jsonElement, Link.class);
            return new Link[]{link};
        }
    }

}