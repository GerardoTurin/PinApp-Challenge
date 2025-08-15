package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.responses.CustomOrderOrderLocationsOrderStatusesResponse;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IOrderRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IOrdersLocationsRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IOrdersStatusesRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IGetOrdersUseCase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOrdersUseCase implements IGetOrdersUseCase {

    private final IOrderRepository orderRepository;
    private final ISessionService sessionService;
    private final IOrdersLocationsRepository ordersLocationsRepository;
    private final IOrdersStatusesRepository ordersStatusesRepository;

    @Override
    public List<CustomOrderOrderLocationsOrderStatusesResponse> getAllByDateCompanyAndActive(LocalDate date) {
        var companyId = sessionService.getCompanyId();
        var res = new ArrayList<CustomOrderOrderLocationsOrderStatusesResponse>();

        var orders = orderRepository.findAllByDateAndCompanyIdAndActive(date, companyId);

        orders.forEach(o -> {
            var orderloctaions = ordersLocationsRepository.findByOrderId(o.getId());
            var orderStatuses = ordersStatusesRepository.findByOrderId(o.getId());

            var custom = CustomOrderOrderLocationsOrderStatusesResponse.builder()
                    .id(o.getId())
                    .orderDate(o.getOrderDate())
                    .entity(o.getEntity())
                    .booking(o.getBooking())
                    .active(o.isActive())
                    .operationType(o.getOperationType())
                    .status(o.getStatus())
                    .ordersLocations(orderloctaions)
                    .ordersStatuses(orderStatuses)
                    .build();

            res.add(custom);
        });

        return res;

    }
}
