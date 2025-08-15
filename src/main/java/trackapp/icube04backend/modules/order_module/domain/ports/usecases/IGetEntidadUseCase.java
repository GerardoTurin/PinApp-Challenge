package trackapp.icube04backend.modules.order_module.domain.ports.usecases;

import trackapp.icube04backend.modules.order_module.domain.models.Entidad;

import java.util.List;

public interface IGetEntidadUseCase {
    List<Entidad> getByCompanyIdAndEntidadType(Long typeId);
}
