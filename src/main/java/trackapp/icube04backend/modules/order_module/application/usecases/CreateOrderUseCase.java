package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomOrderCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomOrdersLocationsRequest;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.order_module.domain.models.Order;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersLocations;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersStatuses;

import trackapp.icube04backend.modules.order_module.domain.ports.repositories.*;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateOrderStatusesUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateOrderUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase implements ICreateOrderUseCase {

    private final IOrderRepository orderRepository;
    private final IEntidadRepository entidadRepository;
    private final IBookingRepository bookingRepository;
    private final IConfigDetailRepository configDetailRepository;
    private final ICreateOrderStatusesUseCase createOrderStatusesUseCase;
    private final IOrdersLocationsRepository orderLocationsRepository;
    private final ILocationRepository locationRepository;

    @Override
    public Order create(CustomOrderCreateRequest request) {

        var saved = orderRepository.save(createOrder(request));
        createOrderStatusesUseCase.create(createOrderStatuses(saved, request.statusId()));
        createOrderLocations(saved, request.orderslocations());

        return saved;
    }


    private Order createOrder(CustomOrderCreateRequest request) {
        var ent = entidadRepository.findById(request.entityId());
        var book = bookingRepository.findById(request.bookingId());
        var ot = configDetailRepository.findById(request.operationTypeId());
        var st = configDetailRepository.findById(request.statusId());

         return Order.builder()
                .orderDate(request.date())
                .entity(ent)
                .booking(book)
                 .active(true)
                .operationType(ot)
                .status(st)
                .build();
    }

    private OrdersStatuses createOrderStatuses(Order order, Long statusId) {
        var st = configDetailRepository.findById(statusId);

        return OrdersStatuses.builder()
                .order(order)
                .status(st)
                .build();
    }

    private void createOrderLocations(Order order, List<CustomOrdersLocationsRequest> request) {
        request.forEach( r -> {
            var locationdb = locationRepository.findByid(r.locationId());
            var statusdb = configDetailRepository.findById(r.statusConfigDetailId());
            var locationtype = configDetailRepository.findById(r.locationTypeId());

            orderLocationsRepository.save(OrdersLocations.builder()
                            .order(order)
                            .location(locationdb)
                            .status(statusdb)
                            .position(r.position())
                            .locationType(locationtype)
                    .build());
        });
    }
}
