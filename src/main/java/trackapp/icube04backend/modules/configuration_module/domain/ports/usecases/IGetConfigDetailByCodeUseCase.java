package trackapp.icube04backend.modules.configuration_module.domain.ports.usecases;

import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;

import java.util.List;

public interface IGetConfigDetailByCodeUseCase {

    ConfigDetail findByCode(String code);

    List<ConfigDetail> findByHeaderCode(String code);
}
