package trackapp.icube04backend.infrastructure.adapters.auth_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.ModulesRolesPermissionsJPARepository;
import trackapp.icube04backend.infrastructure.db.model.ModulesRolesPermissionsEntity;
import trackapp.icube04backend.modules.auth_module.domain.models.ModulesRolesPermissions;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IModulesRolesPermissionsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModulesRolesPermissionsRepository implements IModulesRolesPermissionsRepository {

    private final ModulesRolesPermissionsJPARepository jpaRepository;

    @Override
    public void save(ModulesRolesPermissions mrp) {
        jpaRepository.save(ModulesRolesPermissionsEntity.convertFromDomain(mrp));

    }

    @Override
    public List<ModulesRolesPermissions> findByRoleId(Long id) {
        return jpaRepository.findByRoleId(id).stream().map(ModulesRolesPermissionsEntity::convertToDomain).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        jpaRepository.deleteByRoleId(roleId);
    }
}
