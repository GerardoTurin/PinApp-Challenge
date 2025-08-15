package trackapp.icube04backend.modules.order_module.domain.ports.repositories;

import trackapp.icube04backend.modules.order_module.domain.models.Location;

import java.util.List;

public interface ILocationRepository {
    Location save(Location location);

    Location findByid(Long id);

    void deleteById(Long id);

    List<Location> findAllByCompanyId(Long companyId);
}
