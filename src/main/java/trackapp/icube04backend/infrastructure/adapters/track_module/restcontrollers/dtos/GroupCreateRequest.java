package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.track_module.domain.models.Group;

public record GroupCreateRequest(
        String name,
        String code,
        Long createdUserId
) {

    public Group convertToDomain() {
        return Group.builder()
                .name(this.name)
                .code(this.code)
                .createdUser(User.builder().id(this.createdUserId).build())
                .build();
    }
}
