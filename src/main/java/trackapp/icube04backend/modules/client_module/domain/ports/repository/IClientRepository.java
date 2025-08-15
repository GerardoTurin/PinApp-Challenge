package trackapp.icube04backend.modules.client_module.domain.ports.repository;

import trackapp.icube04backend.modules.client_module.domain.models.Client;
import trackapp.icube04backend.modules.client_module.domain.models.ClientStats;

import java.util.List;

public interface IClientRepository {
    Client save(Client client);

    Client findById(Long id);

    List<Client> findAll();

    List<Client> findAllByCompanyIdAndByUserId(Long companyId, Long userId);

    List<Client> findAllByUserId(Long userId);

    void delete(Client device);


    ClientStats getStatsAll();
}
