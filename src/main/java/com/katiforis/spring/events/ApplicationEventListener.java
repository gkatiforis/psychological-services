package com.katiforis.spring.events;

import com.katiforis.spring.Respones.SubmissionEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@Component
public class ApplicationEventListener {

    /**
     * The list of the objects of SseEmitter.
     * The key of the map stands for submissionId.
     * The value of the map is the corresponding SseEmitter object.
     */
    private static Map<Long, SseEmitter> sseEmitters = new Hashtable<Long, SseEmitter>();

    @EventListener
    public void submissionEventHandler(SubmissionEvent event) throws IOException {
        long submissionId = event.getSubmissionId();
        String message = event.getMessage();
        SseEmitter sseEmitter = sseEmitters.get(submissionId);

        if ( sseEmitter == null ) {
            //LOGGER.warn(String.format("CANNOT get the SseEmitter for submission #%d.", submissionId));
            return;
        }
        sseEmitter.send(message);
    }

    public void addSseEmitters(long submissionId, SseEmitter sseEmitter) {
        sseEmitters.put(submissionId, sseEmitter);
    }
}