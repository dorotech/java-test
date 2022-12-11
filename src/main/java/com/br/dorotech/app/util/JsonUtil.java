package com.br.dorotech.app.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    private static final ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }


    public static String writeValueAsString(Object value) {
        try {
            String json = OBJECT_MAPPER.writeValueAsString(value);
            json = (json.equals("\"\"")) ? "{}" : json;

            return json;
        } catch (JsonProcessingException e) {
            LOG.error("Erro ao gerar json : " + value, e);
        }

        return "";
    }

    public static <T> T readValue(String json, Class<T> valueType) {
        try {
            if (JsonNode.class.isAssignableFrom(valueType)) {
                return (T) (OBJECT_MAPPER.readTree(json));
            } else {
                return OBJECT_MAPPER.readValue(json, valueType);
            }
        } catch (Exception e) {
            LOG.error("Erro ao gerar objeto a partir do JSON : " + json, e);
        }

        return null;
    }

}
