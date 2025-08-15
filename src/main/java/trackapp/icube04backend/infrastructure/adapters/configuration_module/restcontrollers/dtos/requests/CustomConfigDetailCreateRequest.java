package trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests;

import com.fasterxml.jackson.databind.JsonNode;
import io.swagger.v3.core.util.Json;

public record CustomConfigDetailCreateRequest(
        String name,
        Long headerId,
        Long parentId,
        JsonNode attribute,
        boolean systemParameter,
        Long position
) {
}
