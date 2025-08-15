package trackapp.icube04backend.modules.auth_module.domain.ports.repositories;

import trackapp.icube04backend.modules.auth_module.domain.models.User;

import java.util.List;

public interface IUserRepository {

    User findById(Long id);

    User findByUsername(String username);

    List<User> findAll();

    User save(User user);

    void deleteById(Long id);

    User findByEmail(String email);

}
