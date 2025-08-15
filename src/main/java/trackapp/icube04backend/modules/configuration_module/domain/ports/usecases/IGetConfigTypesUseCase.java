package trackapp.icube04backend.modules.configuration_module.domain.ports.usecases;

import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigType;

import java.util.List;

public interface IGetConfigTypesUseCase {
    List<ConfigType> getAll();
}
