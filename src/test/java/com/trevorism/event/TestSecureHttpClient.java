package com.trevorism.event;

import com.trevorism.http.HeadersHttpResponse;
import com.trevorism.http.HttpClient;
import com.trevorism.https.SecureHttpClient;
import com.trevorism.https.token.ObtainTokenStrategy;

import java.util.Map;

public class TestSecureHttpClient implements SecureHttpClient {
    @Override
    public ObtainTokenStrategy getObtainTokenStrategy() {
        return null;
    }

    @Override
    public HttpClient getHttpClient() {
        return null;
    }

    @Override
    public String get(String s) {
        return "[\"topic1\",\"topic2\"]";
    }

    @Override
    public HeadersHttpResponse get(String s, Map<String, String> map) {
        return null;
    }

    @Override
    public String post(String s, String s1) {
        return s1;
    }

    @Override
    public HeadersHttpResponse post(String s, String s1, Map<String, String> map) {
        return null;
    }

    @Override
    public String put(String s, String s1) {
        return "";
    }

    @Override
    public HeadersHttpResponse put(String s, String s1, Map<String, String> map) {
        return null;
    }

    @Override
    public String patch(String s, String s1) {
        return "";
    }

    @Override
    public HeadersHttpResponse patch(String s, String s1, Map<String, String> map) {
        return null;
    }

    @Override
    public String delete(String s) {
        return "";
    }

    @Override
    public HeadersHttpResponse delete(String s, Map<String, String> map) {
        return null;
    }
}
