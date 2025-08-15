package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.track_module.domain.models.Group;

public record GroupResponse(
        Long id,
        String name,
        String code,
        Long createdUserId,
        Long companyId,
        String createdAt
) {

    public static GroupResponse convertFromDomain(Group group) {
        return new GroupResponse(
                group.getId(),
                group.getName(),
                group.getCode(),
                group.getCreatedUser() != null ? group.getCreatedUser().getId() : null,
                group.getCompany() != null ? group.getCompany().getId() : null,
                group.getCreatedAt().toString()
        );
    }
}
