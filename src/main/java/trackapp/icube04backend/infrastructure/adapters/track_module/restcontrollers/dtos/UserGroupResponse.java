package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;

public record UserGroupResponse(
        Long id,
        Long userId,
        Long groupId,
        Long createdUserId,
        String createdAt
) {

    public static UserGroupResponse convertFromDomain(UserGroup userGroup) {
        return new UserGroupResponse(
                userGroup.getId(),
                userGroup.getUser() != null ? userGroup.getUser().getId() : null,
                userGroup.getGroup() != null ? userGroup.getGroup().getId() : null,
                userGroup.getCreatedUser() != null ? userGroup.getCreatedUser().getId() : null,
                userGroup.getCreatedAt().toString()
        );
    }
}
