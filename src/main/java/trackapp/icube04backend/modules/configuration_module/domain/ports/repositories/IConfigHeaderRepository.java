package trackapp.icube04backend.modules.configuration_module.domain.ports.repositories;

import org.springframework.stereotype.Repository;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigHeader;

import java.util.List;

@Repository
public interface IConfigHeaderRepository {

    List<ConfigHeader> findByConfigTypeId(Long id);

    ConfigHeader findById(Long id);
}
