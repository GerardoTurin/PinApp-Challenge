package trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.requests;

import trackapp.icube04backend.modules.company_module.domain.models.Company;

public record CompanyCreateRequest(
        String name,
        String address,
        String description,
        String imgUrl
) {


    public Company convertToDomain(){
        return Company.builder()
                .name(this.name)
                .address(this.address)
                .description(this.description)
                .imgUrl(this.imgUrl)
                .build();
    }
}
