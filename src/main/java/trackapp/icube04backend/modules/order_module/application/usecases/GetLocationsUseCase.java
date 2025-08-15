package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.order_module.domain.models.Location;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.ILocationRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IGetLocationsUseCase;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GetLocationsUseCase implements IGetLocationsUseCase {

    private final ILocationRepository locationRepository;
    private final ISessionService sessionService;

    @Override
    public List<Location> getAllByCompanyId() {
        var companyId = sessionService.getCompanyId();

        return locationRepository.findAllByCompanyId(companyId);
    }
}
