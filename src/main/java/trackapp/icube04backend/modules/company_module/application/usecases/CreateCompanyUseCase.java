package trackapp.icube04backend.modules.company_module.application.usecases;


import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.requests.CustomCompanyCreateRequest;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.company_module.domain.models.FileCompany;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.IStorageCompanyRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.ICreateCompanyUseCase;

import java.util.ArrayList;

@UseCase
@RequiredArgsConstructor
public class CreateCompanyUseCase implements ICreateCompanyUseCase {

    private final ICompanyRepository companyRepository;
    private final IStorageCompanyRepository storageCompanyRepository;
    private final IUserRepository userRepository;


    @Override
    public Company create(CustomCompanyCreateRequest request, byte[] img) {
        var company = Company.builder()
                .name(request.name())
                .address(request.address())
                .description(request.description())
                .usuarios(new ArrayList<User>())
                .build();

        var user = userRepository.findById(request.userId());
        company.getUsuarios().add(user);

        return setImg(company, img);
    }

    private Company setImg(Company company, byte[] img) {
        if (img != null) {
            String nameFile = "company/" + company.getName().replace(" ", "") + ".jpg";
            var baseUrl = storageCompanyRepository.uploadFile(new FileCompany(nameFile, img), company.getId());
            company.setImgUrl(baseUrl);

        }
        return companyRepository.save(company);
    }
}
