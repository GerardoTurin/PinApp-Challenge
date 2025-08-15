package trackapp.icube04backend.modules.configuration_module.domain.ports.usecases;

import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigHeader;

import java.util.List;

public interface IGetConfigHeadersUseCase {
    List<ConfigHeader> getAllByConfigTypeId(Long id);
}
