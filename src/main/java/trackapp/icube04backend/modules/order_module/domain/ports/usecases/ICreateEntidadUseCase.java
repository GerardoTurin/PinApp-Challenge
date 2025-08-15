package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomEntidadCreateRequest;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;

public interface ICreateEntidadUseCase {
    Entidad create(CustomEntidadCreateRequest request);
}
