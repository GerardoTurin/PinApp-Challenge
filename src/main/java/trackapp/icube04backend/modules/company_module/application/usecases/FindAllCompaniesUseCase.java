package trackapp.icube04backend.modules.company_module.application.usecases;


import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.IFindAllCompaniesUseCase;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllCompaniesUseCase implements IFindAllCompaniesUseCase {

    private final ICompanyRepository companyRepository;
    private final ISessionService sessionService;

    @Override
    public List<Company> findAll() {
        var companies = companyRepository.findAll();

        return companies;
    }

}
