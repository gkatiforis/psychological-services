package com.katiforis.spring.events;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

public class StompDisconnectEvent implements ApplicationListener<SessionDisconnectEvent> {

    private final Log logger = LogFactory.getLog(StompDisconnectEvent.class);

    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("++++++++ Disconnect  StompConnectEvent::onApplicationEvent()    sha.getSessionId(): "+
                sha.getSessionId()+" sha.toNativeHeaderMap():"+sha.toNativeHeaderMap());
    }
}