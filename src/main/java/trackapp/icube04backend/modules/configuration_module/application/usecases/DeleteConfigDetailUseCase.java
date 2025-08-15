package trackapp.icube04backend.modules.configuration_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IDeleteConfigDetailUseCase;


@Service
@RequiredArgsConstructor
public class DeleteConfigDetailUseCase implements IDeleteConfigDetailUseCase {

    private final IConfigDetailRepository configDetailRepository;

    @Override
    public void delete(Long id) {
        var cd = configDetailRepository.findById(id);

        if(!cd.isSystemParameter()){
            configDetailRepository.deleteById(id);
        }
    }
}
