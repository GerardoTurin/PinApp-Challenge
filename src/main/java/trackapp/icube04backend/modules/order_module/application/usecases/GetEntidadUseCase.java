package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IEntidadRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IGetEntidadUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetEntidadUseCase implements IGetEntidadUseCase {

    private final IEntidadRepository entidadRepository;
    private final ISessionService sessionService;

    @Override
    public List<Entidad> getByCompanyIdAndEntidadType(Long typeId) {
        var companyId = sessionService.getCompanyId();

        return entidadRepository.findByCompanyIdAndEntidadType(typeId, companyId);
    }
}
