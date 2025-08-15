package trackapp.icube04backend.infrastructure.adapters.auth_module.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.UserJPARepository;
import trackapp.icube04backend.infrastructure.db.model.UserEntity;
import trackapp.icube04backend.modules.auth_module.domain.models.User;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserRepository implements IUserRepository {

    private final UserJPARepository jpaRepository;

    @Override
    public User findById(Long id) {
        var entity = jpaRepository.findById(id).orElse(null);

        if (entity != null) {
            return entity.convertToDomain();
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public User findByUsername(String username) {
        var entity = jpaRepository.findByUsername(username);

        if (entity != null) {
            return entity.convertToDomain();
        } else {
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return jpaRepository.findAll().stream().map(UserEntity::convertToDomain).toList();
    }

    @Override
    public User save(User user) {
        var entity = jpaRepository.save(UserEntity.convertFromDomain(user));

        return entity.convertToDomain();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        var user = jpaRepository.findByEmail(email);
        return user != null ? user.convertToDomain() : null;
    }
}
