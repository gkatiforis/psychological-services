package com.katiforis.spring.configuration;


import com.katiforis.spring.events.StompConnectEvent;
import com.katiforis.spring.events.StompConnectEvent;
import com.katiforis.spring.events.StompDisconnectEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketHandlersConfig {
    @Bean
    public StompConnectEvent webSocketConnectHandler() {
        return new StompConnectEvent();
    }

    @Bean
    public StompDisconnectEvent webSocketDisconnectHandler() {
        return new StompDisconnectEvent();
    }
}
