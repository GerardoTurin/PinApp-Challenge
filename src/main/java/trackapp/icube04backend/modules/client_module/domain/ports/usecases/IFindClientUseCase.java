package trackapp.icube04backend.modules.client_module.domain.ports.usecases;

import trackapp.icube04backend.modules.client_module.domain.models.Client;

import java.util.List;

public interface IFindClientUseCase {

    Client findById(Long id);

    List<Client> findAllByCompanyIdAndByUserId(Long userId);

    List<Client> findAllByUserId(Long userId);
}
