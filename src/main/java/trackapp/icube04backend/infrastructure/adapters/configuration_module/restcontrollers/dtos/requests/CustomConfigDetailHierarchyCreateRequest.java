package trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests;

import com.fasterxml.jackson.databind.JsonNode;

public record CustomConfigDetailHierarchyCreateRequest(
        Long configDetailId,
        Long parentConfigDetailId,
        Long sonConfigDetailId,
        JsonNode attribute
) {
}
