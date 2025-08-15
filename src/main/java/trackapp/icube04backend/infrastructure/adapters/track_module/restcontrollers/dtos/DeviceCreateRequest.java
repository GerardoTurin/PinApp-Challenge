package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.track_module.domain.models.Device;

public record DeviceCreateRequest(
        String name,
        String uniqueId,
        Long positionId,
        Long createdUserId
        //User createdUser
) {


    public Device convertToDomain() {
        return Device.builder()
                .name(this.name)
                .uniqueId(this.uniqueId)
                .positionId(this.positionId != null ? this.positionId : null)
                //.createdUser(this.createdUser)
                .createdUser(User.builder().id(this.createdUserId).build())
                .build();

    }
}
