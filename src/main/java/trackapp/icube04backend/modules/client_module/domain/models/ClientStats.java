package trackapp.icube04backend.modules.client_module.domain.models;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClientStats {
    Long totalClients;
    Double averageAge;
    Double stdDevAge;
}
