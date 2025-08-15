package trackapp.icube04backend.infrastructure.adapters.auth_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.RoleJPARepository;
import trackapp.icube04backend.infrastructure.db.model.RoleEntity;
import trackapp.icube04backend.modules.auth_module.domain.models.Role;
import trackapp.icube04backend.modules.catalog_module.domain.ports.repositories.IRoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleRepository implements IRoleRepository {

    private final RoleJPARepository jpaRepository;

    @Override
    public Role save(Role role) {
        return jpaRepository.save(RoleEntity.convertFromDomain(role)).convertToDomain();
    }

    @Override
    public List<Role> findAll() {
        return jpaRepository.findAll().stream().map(RoleEntity::convertToDomain).toList();
    }

    @Override
    public Role findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
