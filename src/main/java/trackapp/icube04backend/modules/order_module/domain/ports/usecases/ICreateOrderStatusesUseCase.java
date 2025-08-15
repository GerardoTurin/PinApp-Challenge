package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.modules.order_module.domain.models.OrdersStatuses;

public interface ICreateOrderStatusesUseCase {
    void create(OrdersStatuses orderstatus);
}
