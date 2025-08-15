package trackapp.icube04backend.modules.company_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.requests.CustomCompanyCreateRequest;
import trackapp.icube04backend.modules.company_module.domain.models.Company;

public interface ICreateCompanyUseCase {

    Company create(CustomCompanyCreateRequest request, byte[] img);
}
