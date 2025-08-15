package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomOrderUpdateRequest;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.order_module.domain.models.Order;
import trackapp.icube04backend.modules.order_module.domain.models.OrdersLocations;

import trackapp.icube04backend.modules.order_module.domain.ports.repositories.*;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IUpdateOrderUseCase;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UpdateOrderUseCase implements IUpdateOrderUseCase {

    private final IOrderRepository orderRepository;
    private final IEntidadRepository entidadRepository;
    private final IBookingRepository bookingRepository;
    private final IConfigDetailRepository configDetailRepository;
    private final IOrdersStatusesRepository ordersStatusesRepository;
    private final ILocationRepository locationRepository;
    private final IOrdersLocationsRepository orderLocationsRepository;

    @Override
    public Order update(CustomOrderUpdateRequest request) {
        var old = orderRepository.findById(request.id());

        var ent = entidadRepository.findById(request.entityId());
        var book = bookingRepository.findById(request.bookingId());
        var ot = configDetailRepository.findById(request.operationTypeId());
        var st = configDetailRepository.findById(request.statusId());


        old.setOrderDate(request.date());
        old.setEntity(ent);
        old.setBooking(book);
        old.setOperationType(ot);
        old.setStatus(st);
        old.setUpdatedAt(LocalDate.now());

        var saved = orderRepository.save(old);

        // Elimino ordersLocations viejos
        var oldOrderLocations = orderLocationsRepository.findByOrderId(request.id());
        oldOrderLocations.forEach(ol -> {
            orderLocationsRepository.delete(ol.getId());
        });

        // Agrego ordersLocations nuevos
        request.ordersLocationsRequests().forEach(ol -> {
            var l = locationRepository.findByid(ol.locationId());
            var s = configDetailRepository.findById(ol.statusConfigDetailId());
            var lt = configDetailRepository.findById(ol.locationTypeId());

            var nol = OrdersLocations.builder()
                    .order(saved)
                    .location(l)
                    .status(s)
                    .locationType(lt)
                    .position(ol.position())
                    .build();

            orderLocationsRepository.save(nol);
        });


        return saved;
    }
}
