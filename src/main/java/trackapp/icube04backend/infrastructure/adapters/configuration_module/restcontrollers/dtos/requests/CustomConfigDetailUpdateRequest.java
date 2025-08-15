package trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests;

import com.fasterxml.jackson.databind.JsonNode;

public record CustomConfigDetailUpdateRequest(
        Long id,
        String name,
        Long configHeaderId,
        Long position,
        Long parentId,
        boolean systemParameter,
        JsonNode attribute
) {
}
