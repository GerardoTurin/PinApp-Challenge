package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IEntidadRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IDeleteEntidadUseCase;

@Service
@RequiredArgsConstructor
public class DeleteEntidadUseCase implements IDeleteEntidadUseCase {

    private final IEntidadRepository entidadRepository;

    @Override
    public void delete(Long id) {
        entidadRepository.delete(id);
    }
}
