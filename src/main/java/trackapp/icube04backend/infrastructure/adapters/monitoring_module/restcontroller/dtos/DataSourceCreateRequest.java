package trackapp.icube04backend.infrastructure.adapters.monitoring_module.restcontroller.dtos;

import trackapp.icube04backend.modules.monitoring_module.domain.models.DataSource;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;

public record DataSourceCreateRequest(
        String urlConnection,
        String userConnection,
        String passwordConnection,
        String driverConnection,
        String endpointConnection,
        Long entityId
) {
    public DataSource convertToDomain() {
        return DataSource.builder()
                .urlConnection(this.urlConnection)
                .userConnection(this.userConnection)
                .passwordConnection(this.passwordConnection)
                .driverConnection(this.driverConnection)
                .endpointConnection(this.endpointConnection)
                .entityId(Entidad.builder().id(this.entityId).build())
                .build();
    }

}
