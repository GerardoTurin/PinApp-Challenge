package trackapp.icube04backend.modules.order_module.domain.ports.repositories;

import trackapp.icube04backend.modules.order_module.domain.models.Booking;

import java.util.List;

public interface IBookingRepository {
    Booking save(Booking build);

    Booking findById(Long id);

    void deleteById(Long id);

    List<Booking> getAllByCompanyId(Long companyid);
}
