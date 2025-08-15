package trackapp.icube04backend.infrastructure.adapters.order_module.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.LocationJPARepository;
import trackapp.icube04backend.infrastructure.db.model.LocationEntity;
import trackapp.icube04backend.modules.order_module.domain.models.Location;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.ILocationRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LocationRepository implements ILocationRepository{

    private final LocationJPARepository jpaRepository;

    @Override
    public Location save(Location location) {
        return jpaRepository.save(LocationEntity.convertFromDomain(location)).convertToDomain();
    }

    @Override
    public Location findByid(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Location> findAllByCompanyId(Long companyId) {
        return jpaRepository.findAllByCompanyId(companyId).stream().map(LocationEntity::convertToDomain).toList();
    }
}
