package trackapp.icube04backend.infrastructure.adapters.order_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.BookingJPARepository;
import trackapp.icube04backend.infrastructure.db.model.BookingEntity;
import trackapp.icube04backend.modules.order_module.domain.models.Booking;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IBookingRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookingRepository implements IBookingRepository {

    private final BookingJPARepository jpaRepository;

    @Override
    public Booking save(Booking booking) {
        return jpaRepository.save(BookingEntity.convertFromDomain(booking)).convertToDomain();
    }

    @Override
    public Booking findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Booking> getAllByCompanyId(Long companyid) {
        return jpaRepository.getAllByCompanyId(companyid).stream().map(BookingEntity::convertToDomain).toList();
    }
}
