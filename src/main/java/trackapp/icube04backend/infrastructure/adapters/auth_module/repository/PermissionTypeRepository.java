package trackapp.icube04backend.infrastructure.adapters.auth_module.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.PermissionTypeJPARepository;
import trackapp.icube04backend.infrastructure.db.model.PermissionTypeEntity;
import trackapp.icube04backend.modules.auth_module.domain.models.PermissionType;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IPermissionTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionTypeRepository implements IPermissionTypeRepository {

    private final PermissionTypeJPARepository jpaRepository;

    @Override
    public List<PermissionType> findAll() {
        var entities = jpaRepository.findAll();

        return entities.stream().map(PermissionTypeEntity::convertToDomain).toList();
    }

    @Override
    public PermissionType findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }
}
