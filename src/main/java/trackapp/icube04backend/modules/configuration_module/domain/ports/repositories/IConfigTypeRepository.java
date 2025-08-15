package trackapp.icube04backend.modules.configuration_module.domain.ports.repositories;

import org.springframework.stereotype.Repository;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigType;

import java.util.List;

@Repository
public interface IConfigTypeRepository {
    List<ConfigType> findAll();
}
