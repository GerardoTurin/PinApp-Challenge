package trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.client_module.domain.models.ClientStats;

public record ClientStatsResponse(
        Long totalClients,
        Double averageAge,
        Double stdDevAge
) {

    public static ClientStatsResponse convertFromDomain(ClientStats s) {
        return new ClientStatsResponse(
                s.getTotalClients(),
                s.getAverageAge(),
                s.getStdDevAge()
        );
    }
}
