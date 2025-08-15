package trackapp.icube04backend.modules.monitoring_module.domain.ports.repository;

import trackapp.icube04backend.modules.monitoring_module.domain.models.DataSource;

import java.util.List;
import java.util.Optional;

public interface IDataSourceRepository {
    DataSource save(DataSource dataSource);

    Optional<DataSource> findById(Long id);

    List<DataSource> findByEntity_IdAndCompany_Id(Long entityId, Long companyId);

    Optional<DataSource> findByIdAndCompany(Long id, Long companyId);

    List<DataSource> findByCompany(Long companyId);
}
