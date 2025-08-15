package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.ILocationRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IDeleteLocationUseCase;

@Service
@RequiredArgsConstructor
public class DeleteLocationUseCase implements IDeleteLocationUseCase {

    private final ILocationRepository locationRepository;

    @Override
    public void delete(Long id) {
    locationRepository.deleteById(id);
    }
}
