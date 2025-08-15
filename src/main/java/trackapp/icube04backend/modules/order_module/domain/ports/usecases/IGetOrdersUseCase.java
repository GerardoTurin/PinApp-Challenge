package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.responses.CustomOrderOrderLocationsOrderStatusesResponse;

import java.time.LocalDate;
import java.util.List;

public interface IGetOrdersUseCase {
    List<CustomOrderOrderLocationsOrderStatusesResponse> getAllByDateCompanyAndActive(LocalDate date);
}
