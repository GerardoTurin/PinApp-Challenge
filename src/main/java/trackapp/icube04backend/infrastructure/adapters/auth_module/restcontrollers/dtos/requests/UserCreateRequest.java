package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests;


import trackapp.icube04backend.modules.auth_module.domain.models.Role;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import java.util.ArrayList;
import java.util.List;

public record UserCreateRequest(
        String username,
        String password,
        String name,
        String lastname,
        String email,
        boolean status,
        Long userTypeConfigDetailId,
        Long roleId
) {

    public User convertToDomain(){
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .lastname(lastname)
                .email(email)
                .status(status)
                .userType(ConfigDetail.builder()
                        .id(userTypeConfigDetailId)
                        .build())
                .companies(new ArrayList<>())
                .roles(putRole())
                .build();
    }

    private List<Role> putRole() {
        var lista = new ArrayList<Role>();
        lista.add(Role.builder()
                .id(roleId)
                .company(Company.builder().build())
                .build());

        return lista;
    }
}
