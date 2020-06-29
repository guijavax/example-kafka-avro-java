package com.example.api.kafkaavroproducer.config;

import com.example.api.entitie.Cliente;
import com.example.api.kafkaavroproducer.serializer.AvroSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;
import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class Config {

    @Value("${kafka.bootstrap-servers}")
    private String servers;

    private Map<String, Object> producerProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put(BOOTSTRAP_SERVERS_CONFIG, servers);
        properties.put(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);
        return properties;
    }

    @Bean
    public KafkaTemplate<String, Cliente> producerKafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerProperties()));
    }
}
