package com.example.api.kafkaavroproducer.controller;

import com.example.api.entitie.Cliente;
import com.example.api.kafkaavroproducer.entitie.ClienteEntitie;
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
    public void post(@RequestBody ClienteEntitie clienteEntitie) {
        Cliente cliente = new Cliente(clienteEntitie.getNome(), clienteEntitie.getCpf(), clienteEntitie.getDataNasc());
        this.template.send(topic, cliente);
        LOGGER.info("ok");
    }
}