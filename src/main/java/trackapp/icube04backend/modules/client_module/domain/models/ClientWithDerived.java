package trackapp.icube04backend.modules.client_module.domain.models;


import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class ClientWithDerived {
    Long id;
    String firstName;
    String lastName;
    Integer age;
    LocalDate dateOfBirth;

    // Derivados (no en DB)
    LocalDate estimatedLifeExpectancyDate;
    Double remainingYears;
}
