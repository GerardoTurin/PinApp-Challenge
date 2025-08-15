package trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.client_module.domain.models.Client;

public record ClientResponse(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        String dateOfBirth, // If you want to use LocalDate, change this to LocalDate
        Long userId,
        Long companyId
) {

    public static ClientResponse convertFromDomain(Client client) {
        return new ClientResponse(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getAge(),
                client.getDateOfBirth() != null ? client.getDateOfBirth().toString() : null, // Convertir LocalDate a String
                client.getUserId(),
                client.getCompanyId()
        );
    }
}
