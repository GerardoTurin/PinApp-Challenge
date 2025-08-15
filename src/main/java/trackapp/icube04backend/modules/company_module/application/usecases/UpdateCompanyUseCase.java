package trackapp.icube04backend.modules.company_module.application.usecases;


import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.IUpdateCompanyUseCase;

@UseCase
@RequiredArgsConstructor
public class UpdateCompanyUseCase implements IUpdateCompanyUseCase {

    private final ICompanyRepository companyRepository;

    @Override
    public Company update(Company company, byte[] img) {
        return companyRepository.save(company);
    }
}
