package trackapp.icube04backend.modules.company_module.domain.ports.usecases;


import trackapp.icube04backend.modules.company_module.domain.models.Company;

import java.util.List;

public interface IFindAllCompaniesUseCase {
    List<Company> findAll();
}
