package com.trevorism.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trevorism.event.model.EventSubscription;
import com.trevorism.event.model.EventTopic;
import com.trevorism.https.SecureHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DefaultChannelClientTest {

    private SecureHttpClient secureHttpClient;
    private DefaultChannelClient client;
    private Gson gson;

    @BeforeEach
    void setUp() {
        secureHttpClient = mock(SecureHttpClient.class);
        client = new DefaultChannelClient(secureHttpClient);
        gson = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
    }

    @Test
    void listTopics() {
        String jsonResponse = gson.toJson(Arrays.asList("topic1", "topic2"));
        when(secureHttpClient.get(anyString())).thenReturn(jsonResponse);

        List<String> list = client.listTopics();
        assertNotNull(list);
        assertEquals(2, list.size());
        assertTrue(list.contains("topic1"));
        assertTrue(list.contains("topic2"));
    }

    @Test
    void createTopic() {
        EventTopic eventTopic = new EventTopic();
        eventTopic.setName("myTest1");
        String requestJson = gson.toJson(eventTopic);
        String responseJson = gson.toJson(eventTopic);
        when(secureHttpClient.post(anyString(), eq(requestJson))).thenReturn(responseJson);

        String result = client.createTopic("myTest1");
        assertEquals("myTest1", result);
    }

    @Test
    void deleteTopic() {
        EventTopic eventTopic = new EventTopic();
        eventTopic.setName("myTest1");
        String responseJson = gson.toJson(eventTopic);
        when(secureHttpClient.delete(anyString())).thenReturn(responseJson);

        String result = client.deleteTopic("myTest1");
        assertEquals("myTest1", result);
    }

    @Test
    void listSubscriptions() {
        EventSubscription subscription1 = new EventSubscription();
        subscription1.setTopic("myTest1");
        subscription1.setUrl("https://localhost:8080");
        subscription1.setName("unitTest1");
        EventSubscription subscription2 = new EventSubscription();
        subscription2.setName("unitTest2");
        subscription2.setTopic("myTest2");
        subscription2.setUrl("https://localhost:8081");
        String jsonResponse = gson.toJson(Arrays.asList(subscription1, subscription2));
        when(secureHttpClient.get(anyString())).thenReturn(jsonResponse);

        List<EventSubscription> list = client.listSubscriptions();
        assertNotNull(list);
        assertEquals(2, list.size());
        assertTrue(list.contains(subscription1));
        assertTrue(list.contains(subscription2));
    }

    @Test
    void createSubscription() {
        EventSubscription subscription = new EventSubscription();
        subscription.setTopic("myTest1");
        subscription.setUrl("https://localhost:8080");
        subscription.setName("unitTest1");
        String requestJson = gson.toJson(subscription);
        String responseJson = gson.toJson(subscription);
        when(secureHttpClient.post(anyString(), eq(requestJson))).thenReturn(responseJson);

        EventSubscription result = client.createSubscription(subscription);
        assertEquals(subscription, result);
    }

    @Test
    void getSubscription() {
        EventSubscription subscription = new EventSubscription();
        subscription.setTopic("myTest1");
        subscription.setUrl("https://localhost:8080");
        subscription.setName("unitTest1");
        String responseJson = gson.toJson(subscription);
        when(secureHttpClient.get(anyString())).thenReturn(responseJson);

        EventSubscription result = client.getSubscription("unitTest1");
        assertEquals(subscription, result);
    }

    @Test
    void deleteSubscription() {
        EventSubscription subscription = new EventSubscription();
        subscription.setTopic("myTest1");
        subscription.setUrl("https://localhost:8080");
        subscription.setName("unitTest1");
        String responseJson = gson.toJson(subscription);
        when(secureHttpClient.delete(anyString())).thenReturn(responseJson);

        EventSubscription result = client.deleteSubscription("unitTest1");
        assertEquals(subscription, result);
    }
}