package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.track_module.domain.models.Group;

public record GroupUpdateRequest(
        Long id,
        String name,
        String code
) {

    public Group convertToDomain() {
        return Group.builder()
                .id(this.id)
                .name(this.name)
                .code(this.code)
                .build();
    }
}
