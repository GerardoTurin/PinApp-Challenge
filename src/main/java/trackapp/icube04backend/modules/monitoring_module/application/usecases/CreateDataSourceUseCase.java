package trackapp.icube04backend.modules.monitoring_module.application.usecases;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.auth_module.domain.ports.repositories.IUserRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.monitoring_module.domain.models.DataSource;
import trackapp.icube04backend.modules.monitoring_module.domain.ports.repository.IDataSourceRepository;
import trackapp.icube04backend.modules.monitoring_module.domain.ports.usecases.ICreateDataSourceUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IEntidadRepository;

@Service
@RequiredArgsConstructor
public class CreateDataSourceUseCase implements ICreateDataSourceUseCase {

    public final IDataSourceRepository dataSourceRepository;
    public final IEntidadRepository entidadRepository;
    public final ICompanyRepository companyRepository;
    public final IUserRepository userRepository;
    public final ISessionService sessionService;


    @Override
    public DataSource execute(DataSource dataSource) {
        return dataSourceRepository.save(DataSource.builder()
                .urlConnection(dataSource.getUrlConnection())
                .userConnection(dataSource.getUserConnection())
                .passwordConnection(dataSource.getPasswordConnection())
                .driverConnection(dataSource.getDriverConnection())
                .endpointConnection(dataSource.getEndpointConnection())
                //.entityId(entidadRepository.findByIdAndCompany(dataSource.getEntityId().getId(), sessionService.getCompanyId()).orElseThrow(() -> new RuntimeException("Entidad no encontrada")))
                //.companyId(companyRepository.findById(sessionService.getCompanyId()).orElseThrow(() -> new RuntimeException("Compañía no encontrada")))
                .createdAt(dataSource.getCreatedAt())
                //.createdUser(userRepository.findById(sessionService.getUserId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado")))
                .build());
    }
}
