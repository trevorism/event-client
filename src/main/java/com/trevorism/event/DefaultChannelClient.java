package com.trevorism.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.trevorism.event.exception.EventChannelException;
import com.trevorism.event.model.EventSubscription;
import com.trevorism.event.model.EventTopic;
import com.trevorism.https.SecureHttpClient;

import java.util.List;

public class DefaultChannelClient implements ChannelClient {

    private static final String BASE_URL = "https://event.data.trevorism.com";

    private final SecureHttpClient secureHttpClient;
    private final Gson gson;

    public DefaultChannelClient(SecureHttpClient secureHttpClient) {
        this.secureHttpClient = secureHttpClient;
        this.gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
    }

    @Override
    public List<String> listTopics() {
        String json = secureHttpClient.get(BASE_URL + "/topic");
        return gson.fromJson(json, new TypeToken<List<String>>() {
        }.getType());
    }

    @Override
    public String createTopic(String topic) {
        try {
            EventTopic eventTopic = EventTopic.from(topic);
            String request = gson.toJson(eventTopic);
            String response = secureHttpClient.post(BASE_URL + "/topic", request);
            return gson.fromJson(response, EventTopic.class).getName();
        } catch (Exception e) {
            throw new EventChannelException("Unable to create topic, it likely already exists");
        }
    }

    @Override
    public String deleteTopic(String topic) {
        try {
            String response = secureHttpClient.delete(BASE_URL + "/topic/" + topic);
            return gson.fromJson(response, EventTopic.class).getName();
        } catch (Exception e) {
            throw new EventChannelException("Unable to delete topic name: " + topic);
        }
    }

    @Override
    public List<EventSubscription> listSubscriptions() {
        String json = secureHttpClient.get(BASE_URL + "/subscription");
        return gson.fromJson(json, new TypeToken<List<EventSubscription>>() {
        }.getType());
    }

    @Override
    public EventSubscription createSubscription(EventSubscription subscription) {
        try {
            String request = gson.toJson(subscription);
            String response = secureHttpClient.post(BASE_URL + "/subscription", request);
            return gson.fromJson(response, EventSubscription.class);
        } catch (Exception e) {
            throw new EventChannelException("Unable to create subscription. The topic may not exist or the URL may be invalid");
        }
    }

    @Override
    public EventSubscription getSubscription(String name) {
        try {
            String response = secureHttpClient.get(BASE_URL + "/subscription/" + name);
            return gson.fromJson(response, EventSubscription.class);
        } catch (Exception e) {
            throw new EventChannelException("Unable to find subscription name: " + name);
        }
    }

    @Override
    public EventSubscription deleteSubscription(String name) {
        try {
            String response = secureHttpClient.delete(BASE_URL + "/subscription/" + name);
            return gson.fromJson(response, EventSubscription.class);
        } catch (Exception e) {
            throw new EventChannelException("Unable to find subscription name: " + name);
        }
    }
}
