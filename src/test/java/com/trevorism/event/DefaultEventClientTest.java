package com.trevorism.event;

import com.trevorism.https.SecureHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DefaultEventClientTest {

    private SecureHttpClient secureHttpClient;
    private DefaultEventClient<String> client;

    @BeforeEach
    void setUp() {
        secureHttpClient = mock(SecureHttpClient.class);
        client = new DefaultEventClient<>(secureHttpClient);
    }

    @Test
    void sendEvent() {
        String topic = "myTest1";
        String event = "test event 2";
        String expectedResponse = "Event sent: " + event;

        when(secureHttpClient.post(anyString(), anyString())).thenReturn(expectedResponse);

        String result = client.sendEvent(topic, event);
        assertTrue(result.contains(event));
    }
}