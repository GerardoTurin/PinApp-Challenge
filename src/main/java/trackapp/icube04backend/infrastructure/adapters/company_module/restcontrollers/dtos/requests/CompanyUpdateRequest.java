package trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.requests;

import trackapp.icube04backend.modules.company_module.domain.models.Company;

public record CompanyUpdateRequest(
        Long id,
        String name,
        String address,
        String description,
        String imgUrl
) {

    public Company convertToDomain(){
        return Company.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .description(this.description)
                .imgUrl(this.imgUrl)
                .build();
    }

}


