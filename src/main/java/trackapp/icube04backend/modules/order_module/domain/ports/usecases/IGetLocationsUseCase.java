package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.modules.order_module.domain.models.Location;

import java.util.List;

public interface IGetLocationsUseCase {
    List<Location> getAllByCompanyId();
}
