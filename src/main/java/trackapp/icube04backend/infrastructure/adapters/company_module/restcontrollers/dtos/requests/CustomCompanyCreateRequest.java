package trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.requests;

public record CustomCompanyCreateRequest(
        String name,
        String address,
        String description,
        String imgUrl,
        Long userId
) {
}
