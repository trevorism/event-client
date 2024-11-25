package com.trevorism.event.model;

public class EventTopic {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static EventTopic from(String name){
        EventTopic topic = new EventTopic();
        topic.setName(name);
        return topic;
    }
}
