package trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers.dtos;

import trackapp.icube04backend.modules.client_module.domain.models.ClientWithDerived;

import java.time.LocalDate;

public record ClientWithDerivedResponse(
        Long id,
        String firstName,
        String lastName,
        Integer age,
        LocalDate dateOfBirth,
        LocalDate estimatedLifeExpectancyDate,
        Double remainingYears
) {

    public static ClientWithDerivedResponse convertFromDomain(ClientWithDerived c) {
        return new ClientWithDerivedResponse(
                c.getId(),
                c.getFirstName(),
                c.getLastName(),
                c.getAge(),
                c.getDateOfBirth(),
                c.getEstimatedLifeExpectancyDate(),
                c.getRemainingYears()
        );
    }

}
