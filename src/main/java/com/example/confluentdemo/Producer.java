package com.example.confluentdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CommonsLog(topic = "Producer Logger")
public class Producer {

  @Value("${topic.name:users}")
  private String TOPIC;

  private final KafkaTemplate<String, User> kafkaTemplate;

  void sendMessage(User user) {
    log.info(String.format("Before Produced user -> %s", user));
    this.kafkaTemplate.send(this.TOPIC, user.getName(), user);
    log.info(String.format("Produced user -> %s", user));
  }
}
