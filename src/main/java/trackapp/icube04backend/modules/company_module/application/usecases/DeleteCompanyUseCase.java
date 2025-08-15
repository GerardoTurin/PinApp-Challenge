package trackapp.icube04backend.modules.company_module.application.usecases;


import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.IDeleteCompanyUseCase;

@UseCase
@RequiredArgsConstructor
public class DeleteCompanyUseCase implements IDeleteCompanyUseCase {

    private final ICompanyRepository companyRepository;

    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
}
