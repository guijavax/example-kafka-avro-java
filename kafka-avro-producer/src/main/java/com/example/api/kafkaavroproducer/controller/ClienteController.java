package com.example.api.kafkaavroproducer.controller;

import com.example.api.entitie.Cliente;
import com.example.api.kafkaavroproducer.entitie.ClienteEntitie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.awt.font.LayoutPath;

@RestController
@RequestMapping(value="/cliente")
public class ClienteController {

    @Autowired
    private KafkaTemplate<String, Cliente> template;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteController.class);

    @Value(value = "${kafka.topic}")
    private String topic;

    @PostMapping("/post")
    public void post(@RequestBody ClienteEntitie clienteEntitie) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Cliente cl = mapper.readValue(new Gson().toJson(clienteEntitie), Cliente.class);

        this.template.send(topic, cl);
        LOGGER.info("ok");
    }
}