package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos;

import trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.responses.CompanyResponse;
import trackapp.icube04backend.modules.track_module.domain.models.UsersCompanies;

public record UsersCompaniesResponse(
        Long id,
        Long userId,
        CompanyResponse company,
        String createdAt
) {
    public static UsersCompaniesResponse convertFromDomain(UsersCompanies usersCompanies) {
        return new UsersCompaniesResponse(
                usersCompanies.getId(),
                usersCompanies.getUser() != null ? usersCompanies.getUser().getId() : null,
                usersCompanies.getCompany() != null
                        ? CompanyResponse.convertFromDomain(usersCompanies.getCompany())
                        : null,
                usersCompanies.getCreatedAt() != null
                        ? usersCompanies.getCreatedAt().toString()
                        : null
        );
    }
}
