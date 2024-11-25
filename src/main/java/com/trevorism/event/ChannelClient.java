package com.trevorism.event;

import com.trevorism.event.model.EventSubscription;

import java.util.List;

public interface ChannelClient {

    List<String> listTopics();
    String createTopic(String topic);
    String deleteTopic(String topic);

    List<EventSubscription> listSubscriptions();
    EventSubscription createSubscription(EventSubscription subscription);
    EventSubscription getSubscription(String name);
    EventSubscription deleteSubscription(String name);
}
