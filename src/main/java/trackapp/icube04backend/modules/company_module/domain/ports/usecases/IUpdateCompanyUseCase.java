package trackapp.icube04backend.modules.company_module.domain.ports.usecases;

import trackapp.icube04backend.modules.company_module.domain.models.Company;

public interface IUpdateCompanyUseCase {
    Company update(Company company, byte[] img);
}
