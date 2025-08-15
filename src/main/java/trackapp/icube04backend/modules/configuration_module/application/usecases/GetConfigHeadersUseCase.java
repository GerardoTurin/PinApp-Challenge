package trackapp.icube04backend.modules.configuration_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigHeader;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigHeaderRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IGetConfigHeadersUseCase;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GetConfigHeadersUseCase implements IGetConfigHeadersUseCase {

    private final IConfigHeaderRepository configHeaderRepository;

    @Override
    public List<ConfigHeader> getAllByConfigTypeId(Long id) {
        return configHeaderRepository.findByConfigTypeId(id);
    }
}
