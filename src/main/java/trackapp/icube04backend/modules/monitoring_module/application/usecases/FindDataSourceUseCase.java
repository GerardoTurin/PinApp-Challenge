package trackapp.icube04backend.modules.monitoring_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.monitoring_module.domain.models.DataSource;
import trackapp.icube04backend.modules.monitoring_module.domain.ports.repository.IDataSourceRepository;
import trackapp.icube04backend.modules.monitoring_module.domain.ports.usecases.IFindDataSourceUseCase;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class FindDataSourceUseCase implements IFindDataSourceUseCase {
    private final IDataSourceRepository dataSourceRepository;
    private final ISessionService sessionService;


    @Override
    public List<DataSource> getByEntity(Long entityId) {
        return dataSourceRepository.findByEntity_IdAndCompany_Id(entityId, sessionService.getCompanyId());
    }

    @Override
    public Optional<DataSource> getByIdAndCompany(Long id) {
        return dataSourceRepository.findByIdAndCompany(id, sessionService.getCompanyId());
    }

    @Override
    public List<DataSource> getAllByCompany() {
        return dataSourceRepository.findByCompany(sessionService.getCompanyId());
    }
}
