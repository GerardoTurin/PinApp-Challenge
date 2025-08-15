package trackapp.icube04backend.modules.auth_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IUpdateUserUseCase;
import trackapp.icube04backend.modules.company_module.domain.models.FileCompany;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.IStorageCompanyRepository;

@UseCase
@RequiredArgsConstructor
public class UpdateUserUseCase implements IUpdateUserUseCase {

    private final IUserRepository userRepository;
    private final IStorageCompanyRepository storageCompanyRepository;
    private final PasswordEncoder passwordEncoder;

    /*@Override
    public User update(User user , byte[] img) {
        return userRepository.save(user);
    }*/


    @Override
    public User update(User user, byte[] img) {
        var old = userRepository.findById(user.getId());

        old.setUsername(user.getUsername());
        old.setPassword(passwordEncoder.encode(user.getPassword()));
        old.setName(user.getName());
        old.setLastname(user.getLastname());
        old.setEmail(user.getEmail());
        old.setStatus(user.isStatus());
        old.setUserType(user.getUserType());
        old.setImgUrl(user.getImgUrl());

        return userRepository.save(old);
    }



    private void setImg(User user, byte[] img) {
        if (img != null) {
            String nameFile = "user/" + user.getName().replace(" ", "") + ".jpg";
            var baseUrl = storageCompanyRepository.uploadFile(new FileCompany(nameFile, img), user.getId());
            user.setImgUrl(baseUrl);
            userRepository.save(user);
        }
    }



}
