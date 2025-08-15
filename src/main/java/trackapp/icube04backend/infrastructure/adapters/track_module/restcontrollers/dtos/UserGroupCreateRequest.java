package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.track_module.domain.models.Group;
import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;

public record UserGroupCreateRequest(
        Long userId,
        Long groupId,
        Long createdUserId
) {

    public UserGroup convertToDomain() {
        return UserGroup.builder()
                .user(User.builder().id(this.userId).build())
                .group(Group.builder().id(this.groupId).build())
                .createdUser(User.builder().id(this.createdUserId).build())
                .build();
    }
}
