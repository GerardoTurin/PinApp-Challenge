package trackapp.icube04backend.infrastructure.adapters.configuration_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.ConfigTypeJPARepository;
import trackapp.icube04backend.infrastructure.db.model.ConfigTypeEntity;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigType;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigTypeRepository implements IConfigTypeRepository {

    private final ConfigTypeJPARepository jpaRepository;

    @Override
    public List<ConfigType> findAll() {
        return jpaRepository.findAll().stream()
                .map(ConfigTypeEntity::convertToDomain)
                .toList();
    }

}
