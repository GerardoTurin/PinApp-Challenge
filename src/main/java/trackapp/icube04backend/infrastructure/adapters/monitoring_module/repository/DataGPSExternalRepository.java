package trackapp.icube04backend.infrastructure.adapters.monitoring_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.adapters.monitoring_module.restcontroller.dtos.HoursOption;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.monitoring_module.domain.models.DataGPSExternal;
import trackapp.icube04backend.modules.monitoring_module.domain.ports.repository.IDataGPSExternalRepository;
import trackapp.icube04backend.modules.monitoring_module.domain.ports.repository.IDataSourceRepository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class DataGPSExternalRepository implements IDataGPSExternalRepository {
    private final IDataSourceRepository dataSourceRepository;
    //private final IOrderRepository orderRepository;
    //private final IAsignationRepository asignationRepository;
    ///private final IEquipoRepository equipoRepository;
    private final ISessionService sessionService;


    @Override
    public List<DataGPSExternal> findGPSDataByOrder(Long orderId, HoursOption time) {
        return List.of();
    }
}
