package trackapp.icube04backend.modules.client_module.domain.ports.usecases;

import trackapp.icube04backend.modules.client_module.domain.models.ClientStats;

public interface IGetClientStatsUseCase {
    ClientStats execute();
}
