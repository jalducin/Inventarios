package com.inventarios.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // Prefijo para suscripciones (broadcast)
    config.enableSimpleBroker("/topic");
    // Prefijo para mensajes entrantes
    config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // Punto de conexión para clientes SockJS
    registry.addEndpoint("/ws").withSockJS();
  }
}
