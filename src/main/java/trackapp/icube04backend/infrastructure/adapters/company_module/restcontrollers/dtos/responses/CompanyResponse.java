package trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.responses;

import lombok.Builder;
import trackapp.icube04backend.modules.company_module.domain.models.Company;

@Builder
public record CompanyResponse(
        Long id,
        String name
) {

    public static CompanyResponse convertFromDomain(Company company){
        return CompanyResponse.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
