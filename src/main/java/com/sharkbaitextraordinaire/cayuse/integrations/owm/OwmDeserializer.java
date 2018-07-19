package com.sharkbaitextraordinaire.cayuse.integrations.owm;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.sharkbaitextraordinaire.cayuse.integrations.owm.OpenweathermapResponse;

import java.io.IOException;

public class OwmDeserializer extends StdDeserializer<OpenweathermapResponse> {

    public OwmDeserializer() {
        this(null);
    }

    public OwmDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public OpenweathermapResponse deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        String cityName = root.get("name").asText();
        Double temperature = root.get("main").get("temp").asDouble();

        return new OpenweathermapResponse(cityName, temperature);
    }
}
