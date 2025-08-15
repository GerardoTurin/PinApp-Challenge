package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomLocationCreateRequest;
import trackapp.icube04backend.modules.order_module.domain.models.Location;

public interface ICreateLocationUseCase {
    Location create(CustomLocationCreateRequest request);
}
