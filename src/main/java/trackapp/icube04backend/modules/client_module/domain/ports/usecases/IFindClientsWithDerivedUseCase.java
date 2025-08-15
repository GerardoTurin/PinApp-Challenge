package trackapp.icube04backend.modules.client_module.domain.ports.usecases;

import trackapp.icube04backend.modules.client_module.domain.models.ClientWithDerived;

import java.util.List;

public interface IFindClientsWithDerivedUseCase {
    List<ClientWithDerived> execute();
}
