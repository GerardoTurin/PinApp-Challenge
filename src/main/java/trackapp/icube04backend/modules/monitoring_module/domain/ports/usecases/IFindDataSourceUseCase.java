package trackapp.icube04backend.modules.monitoring_module.domain.ports.usecases;

import trackapp.icube04backend.modules.monitoring_module.domain.models.DataSource;

import java.util.List;
import java.util.Optional;

public interface IFindDataSourceUseCase {

    List<DataSource> getByEntity (Long entityId);

    Optional<DataSource> getByIdAndCompany (Long id);

    List<DataSource> getAllByCompany ();
}
