package trackapp.icube04backend.modules.auth_module.application.usecases;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.ICreateUserUseCase;
import trackapp.icube04backend.modules.company_module.domain.models.FileCompany;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.IStorageCompanyRepository;

@UseCase
@RequiredArgsConstructor
public class CreateUserUseCase implements ICreateUserUseCase {

    private final IUserRepository userRepository;
    private final ISessionService sessionService;
    private final ICompanyRepository companyRepository;
    private final IStorageCompanyRepository storageCompanyRepository;
    private final PasswordEncoder passwordEncoder;


   /* @Override
    public User create(User user , byte[] img) {
        var userCompany = companyRepository.findById(sessionService.getCompanyId());
        user.getCompanies().add(userCompany);

        var savedUser = userRepository.save(user);

        return savedUser;
    }*/


    @Transactional
    @Override
    public User create(User user , byte[] img) {
        var userCompany = companyRepository.findById(sessionService.getCompanyId());
        user.getCompanies().add(userCompany);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return  setImg(user, img);

    }



    private User setImg(User user, byte[] img) {
        if (img != null) {
            String nameFile = "user/" + user.getName().replace(" ", "") + ".jpg";
            var baseUrl = storageCompanyRepository.uploadFile(new FileCompany(nameFile, img), user.getId());
            user.setImgUrl(baseUrl);
        }
        return userRepository.save(user);
    }

}
