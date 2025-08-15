package trackapp.icube04backend.modules.order_module.domain.ports.repositories;

import trackapp.icube04backend.modules.order_module.domain.models.Entidad;

import java.util.List;

public interface IEntidadRepository {
    List<Entidad> findByCompanyIdAndEntidadType(Long typeId, Long companyId);

    Entidad save(Entidad entidad);

    Entidad findById(Long id);

    void delete(Long id);
}
