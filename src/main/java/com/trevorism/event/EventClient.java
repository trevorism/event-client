package com.trevorism.event;

public interface EventClient<T> {

    String sendEvent(String topic, T event);

}
