package trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.client_module.domain.models.Client;

import java.time.LocalDate;

public record ClientCreateRequest(
        String firstName,
        String lastName,
        Integer age,
        LocalDate dateOfBirth,
        Long userId
) {

    public Client convertToDomain() {
        return Client.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .age(this.age)
                .dateOfBirth(this.dateOfBirth)
                .userId(this.userId)
                .build();
    }
}
