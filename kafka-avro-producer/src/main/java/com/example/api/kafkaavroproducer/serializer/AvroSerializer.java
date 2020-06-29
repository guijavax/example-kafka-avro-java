package com.example.api.kafkaavroproducer.serializer;

import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class AvroSerializer<T extends SpecificRecordBase> implements Serializer<T> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, T data)  {
        try {
            byte[] res = null;
            if(data != null ) {
                ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                BinaryEncoder encoder = EncoderFactory.get().binaryEncoder(byteOut, null);

                DatumWriter<GenericRecord> record = new GenericDatumWriter<>(data.getSchema());
                record.write(data, encoder);

                encoder.flush();
                byteOut.close();

                res = byteOut.toByteArray();
            }
            return res;
        } catch (IOException e) {
            throw new SerializationException("A mensagem:'" + data + "'nao pode ser serializada para o topico:'" + topic + "'");
        }
    }

    @Override
    public void close() {}
}
