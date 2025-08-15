package trackapp.icube04backend.modules.monitoring_module.domain.ports.repository;

import trackapp.icube04backend.infrastructure.adapters.monitoring_module.restcontroller.dtos.HoursOption;
import trackapp.icube04backend.modules.monitoring_module.domain.models.DataGPSExternal;

import java.util.List;

public interface IDataGPSExternalRepository {

    List<DataGPSExternal> findGPSDataByOrder (Long orderId, HoursOption time);
}
