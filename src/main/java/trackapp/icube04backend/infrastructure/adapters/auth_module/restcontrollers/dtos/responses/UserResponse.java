package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses;

import lombok.Builder;
import trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.responses.CompanyResponse;
import trackapp.icube04backend.modules.auth_module.domain.models.User;

import java.util.List;

@Builder
public record UserResponse(
        String username,
        String name,
        String email,
        Long userTypeCode,
        List<CompanyResponse> companies,
        List<RoleResponse> roles
) {

    public static UserResponse convertFromDomain(User user){
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .userTypeCode(user.getId())
                .companies(user.getCompanies().stream().map(CompanyResponse::convertFromDomain).toList())
                .roles(user.getRoles().stream().map(RoleResponse::convertFromDomain).toList())
                .build();
    }
}
