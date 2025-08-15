package trackapp.icube04backend.modules.configuration_module.domain.ports.repositories;

import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import java.util.List;

public interface IConfigDetailRepository {

    ConfigDetail findByCode(String code);

    List<ConfigDetail> findByHeaderCode(String code);

    ConfigDetail save(ConfigDetail build);

    ConfigDetail findById(Long id);

    void deleteById(Long id);
}
