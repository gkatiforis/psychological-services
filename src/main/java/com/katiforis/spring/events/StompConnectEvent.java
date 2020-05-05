package com.katiforis.spring.events;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;

public class StompConnectEvent implements ApplicationListener<SessionConnectEvent> {

    private final Log logger = LogFactory.getLog(StompConnectEvent.class);

    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String username = event.getUser().getName();
        System.out.println("++++++++ " + username +" StompConnectEvent::onApplicationEvent()    sha.getSessionId(): "+sha.getSessionId()+" sha.toNativeHeaderMap():"+sha.toNativeHeaderMap());
    }
}