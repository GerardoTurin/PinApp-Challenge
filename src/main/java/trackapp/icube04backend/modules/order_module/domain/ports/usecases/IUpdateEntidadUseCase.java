package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomEntidadUpdateRequest;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;

public interface IUpdateEntidadUseCase {
    Entidad update(CustomEntidadUpdateRequest request);
}
