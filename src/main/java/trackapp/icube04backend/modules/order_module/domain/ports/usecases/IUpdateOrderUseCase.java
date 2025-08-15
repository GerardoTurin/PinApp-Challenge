package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomOrderUpdateRequest;
import trackapp.icube04backend.modules.order_module.domain.models.Order;

public interface IUpdateOrderUseCase {
    Order update(CustomOrderUpdateRequest request);
}
