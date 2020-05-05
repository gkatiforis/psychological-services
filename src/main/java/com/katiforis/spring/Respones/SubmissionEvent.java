package com.katiforis.spring.Respones;

import org.springframework.context.ApplicationEvent;

public class SubmissionEvent extends ApplicationEvent {
    public SubmissionEvent(Object source, long submissionId, String message) {
        super(source);
        this.submissionId = submissionId;
        this.message = message;
    }

    public long getSubmissionId() {
        return submissionId;
    }

    public String getMessage() {
        return message;
    }

    private final long submissionId;

    private final String message;
}