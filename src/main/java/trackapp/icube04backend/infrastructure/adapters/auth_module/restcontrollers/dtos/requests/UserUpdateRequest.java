package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests;

import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

public record UserUpdateRequest(
        Long id,
        String username,
        String password,
        String name,
        String lastname,
        String email,
        boolean status,
        Long userTypeConfigDetailId,
        String imgUrl
) {

    public User convertToDomain(){
        return User.builder()
                .id(id)
                .username(username)
                .password(password)
                .name(name)
                .lastname(lastname)
                .email(email)
                .status(status)
                .userType(ConfigDetail.builder()
                        .id(userTypeConfigDetailId)
                        .build())
                .imgUrl(imgUrl)
                .build();
    }
}
