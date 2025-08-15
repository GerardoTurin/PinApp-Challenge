package trackapp.icube04backend.modules.client_module.application;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.client_module.domain.models.ClientStats;
import trackapp.icube04backend.modules.client_module.domain.ports.repository.IClientRepository;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.IGetClientStatsUseCase;

@Service
@RequiredArgsConstructor
public class GetClientStatsUseCase implements IGetClientStatsUseCase {
    private final IClientRepository clientRepository;


    @Override
    @Transactional
    public ClientStats execute() {
        return clientRepository.getStatsAll();
    }
}
