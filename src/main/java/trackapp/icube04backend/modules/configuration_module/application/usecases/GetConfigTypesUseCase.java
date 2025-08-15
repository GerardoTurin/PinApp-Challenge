package trackapp.icube04backend.modules.configuration_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigType;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigTypeRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IGetConfigTypesUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConfigTypesUseCase implements IGetConfigTypesUseCase {

    private static final String SUPER_ADMIN_CODE = "b9fde102-c5bc-4dbc-a4a9-65c6eab2f6aa";

    private final IConfigTypeRepository configTypeRepository;
    private final ISessionService sessionService;
    private final IConfigDetailRepository configDetailRepository;

    @Override
    public List<ConfigType> getAll() {
        var userType = sessionService.getUserTypeId() != null ? configDetailRepository.findById(sessionService.getUserTypeId()) : null;

        if(userType.getCode().equals(SUPER_ADMIN_CODE)){
            return configTypeRepository.findAll();
        }else{
            return configTypeRepository.findAll().stream().filter(configType -> configType.isCatalog()).toList();
        }
    }
}
