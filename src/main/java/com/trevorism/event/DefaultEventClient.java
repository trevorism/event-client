package com.trevorism.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trevorism.event.exception.EventChannelException;
import com.trevorism.http.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class DefaultEventClient<T> implements EventClient<T> {

    private static final String BASE_URL = "https://event.data.trevorism.com";

    private final HttpClient httpClient;
    private final Gson gson;


    public DefaultEventClient(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();

    }

    @Override
    public String sendEvent(String topic, T event) {
        try {
            String json = createEventJson(event);
            String result = httpClient.post(BASE_URL + "/event/" + topic, json);
            return gson.toJson(result);
        } catch (Exception e) {
            throw new EventChannelException("Error sending event to topic: " + topic);
        }
    }

    private String createEventJson(T event) {
        if (event instanceof String || event instanceof Number || event instanceof Boolean) {
            Map<String, Object> map = new HashMap<>();
            map.put("event", event);
            return gson.toJson(map);
        }

        return gson.toJson(event);
    }
}
