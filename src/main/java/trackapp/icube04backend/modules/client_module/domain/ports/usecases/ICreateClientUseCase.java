package trackapp.icube04backend.modules.client_module.domain.ports.usecases;

import trackapp.icube04backend.modules.client_module.domain.models.Client;

public interface ICreateClientUseCase {
    Client execute(Client client);
}
