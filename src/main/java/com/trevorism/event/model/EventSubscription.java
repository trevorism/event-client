package com.trevorism.event.model;

import java.util.Objects;

public final class EventSubscription {

    private String name;
    private final String type = "PULL_UNWRAPPED";
    private String topic;
    private String url;
    private int ackDeadlineSeconds = 60;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAckDeadlineSeconds() {
        return ackDeadlineSeconds;
    }

    public void setAckDeadlineSeconds(int ackDeadlineSeconds) {
        this.ackDeadlineSeconds = ackDeadlineSeconds;
    }

    @Override
    public String toString() {
        return "EventSubscription{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", topic='" + topic + '\'' +
                ", url='" + url + '\'' +
                ", ackDeadlineSeconds=" + ackDeadlineSeconds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        EventSubscription that = (EventSubscription) o;
        return ackDeadlineSeconds == that.ackDeadlineSeconds && Objects.equals(name, that.name) && type.equals(that.type) && Objects.equals(topic, that.topic) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + type.hashCode();
        result = 31 * result + Objects.hashCode(topic);
        result = 31 * result + Objects.hashCode(url);
        result = 31 * result + ackDeadlineSeconds;
        return result;
    }
}
