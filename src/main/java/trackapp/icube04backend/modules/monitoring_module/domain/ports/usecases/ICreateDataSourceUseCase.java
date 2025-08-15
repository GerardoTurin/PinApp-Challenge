package trackapp.icube04backend.modules.monitoring_module.domain.ports.usecases;

import trackapp.icube04backend.modules.monitoring_module.domain.models.DataSource;

public interface ICreateDataSourceUseCase {
    DataSource execute(DataSource dataSource);
}
