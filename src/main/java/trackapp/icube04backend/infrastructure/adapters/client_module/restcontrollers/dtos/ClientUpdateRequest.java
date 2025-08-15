package trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.client_module.domain.models.Client;

import java.time.LocalDate;

public record ClientUpdateRequest(
        String firstName,
        String lastName,
        Integer age,
        LocalDate dateOfBirth
) {

    public Client convertToDomain(Long id) {
        return Client.builder()
                .id(id)
                .firstName(this.firstName)
                .lastName(this.lastName)
                .age(this.age != null ? this.age : null)
                .dateOfBirth(this.dateOfBirth != null ? this.dateOfBirth : null)
                .build();
    }
}
