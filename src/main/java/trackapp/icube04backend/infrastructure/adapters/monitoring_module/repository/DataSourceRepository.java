package trackapp.icube04backend.infrastructure.adapters.monitoring_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.DataSourceJPARepository;
import trackapp.icube04backend.infrastructure.db.model.DataSourceEntity;
import trackapp.icube04backend.modules.monitoring_module.domain.models.DataSource;
import trackapp.icube04backend.modules.monitoring_module.domain.ports.repository.IDataSourceRepository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class DataSourceRepository implements IDataSourceRepository {

    private final DataSourceJPARepository dataSourceJPARepository;

    @Override
    public DataSource save(DataSource dataSource) {

        return dataSourceJPARepository.save(DataSourceEntity.convertFromDomain(dataSource)).convertToDomain();
    }

    @Override
    public Optional<DataSource> findById(Long id) {
        Optional<DataSourceEntity> lstResult = dataSourceJPARepository.findById(id);

        if (lstResult.isEmpty()) {
            return Optional.empty();
        }

        return lstResult.map(DataSourceEntity::convertToDomain);
    }

    @Override
    public List<DataSource> findByEntity_IdAndCompany_Id(Long entityId, Long companyId) {
        List<DataSourceEntity> result = dataSourceJPARepository.findByEntity_IdAndCompany_Id (entityId, companyId);
        //return result.stream.map(DataSourceEntity::convertToDomain).to;
        return result.stream().map(DataSourceEntity::convertToDomain).toList();
    }

    @Override
    public Optional<DataSource> findByIdAndCompany(Long id, Long companyId) {
        Optional<DataSourceEntity> result = dataSourceJPARepository.findByIdAndCompany_Id(id, companyId);

        if (result.isEmpty())  {
            return Optional.empty();
        }
        return result.map(DataSourceEntity::convertToDomain);
    }

    @Override
    public List<DataSource> findByCompany(Long companyId) {
        List<DataSourceEntity> lstResult = dataSourceJPARepository.findByCompany_Id(companyId);
        return lstResult.stream().map( DataSourceEntity::convertToDomain ).toList();
    }
}
