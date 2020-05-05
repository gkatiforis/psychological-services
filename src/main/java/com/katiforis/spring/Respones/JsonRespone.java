package com.katiforis.spring.Respones;

import java.util.Map;

public class JsonRespone {
    private Object user;
    private boolean validated;
    private Map<String, String> errorMessages;

    public JsonRespone(boolean validated) {
        this.validated = validated;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}