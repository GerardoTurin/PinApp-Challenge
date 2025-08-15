package trackapp.icube04backend.infrastructure.adapters.configuration_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.ConfigHeaderJPARepository;
import trackapp.icube04backend.infrastructure.db.model.ConfigHeaderEntity;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigHeader;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigHeaderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigHeaderRepository implements IConfigHeaderRepository {

    private final ConfigHeaderJPARepository jpaRepository;

    @Override
    public List<ConfigHeader> findByConfigTypeId(Long id) {
        return jpaRepository.findByConfigTypeId(id).stream().map(ConfigHeaderEntity::convertToDomain).toList();
    }

    @Override
    public ConfigHeader findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }
}
