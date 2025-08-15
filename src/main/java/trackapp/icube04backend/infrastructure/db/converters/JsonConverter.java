package trackapp.icube04backend.infrastructure.db.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JsonConverter implements AttributeConverter<List<Map<String, String>>, String> {
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public String convertToDatabaseColumn(List<Map<String, String>> attribute) {
        if (attribute == null) return null;
        try {
            return mapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public List<Map<String, String>> convertToEntityAttribute(String dbData) {
        if (dbData == null) return Collections.emptyList();
        try {
            return mapper.readValue(dbData, new TypeReference<List<Map<String, String>>>() {});
        } catch (IOException e) {
            return Collections.emptyList();
        }
    }
}
