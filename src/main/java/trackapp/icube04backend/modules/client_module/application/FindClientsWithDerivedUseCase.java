package trackapp.icube04backend.modules.client_module.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.client_module.domain.models.ClientWithDerived;
import trackapp.icube04backend.modules.client_module.domain.ports.repository.IClientRepository;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.IFindClientsWithDerivedUseCase;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FindClientsWithDerivedUseCase implements IFindClientsWithDerivedUseCase {

    private final IClientRepository clientRepository;
    private final LifeExpectancyPolicy policy;


    @Override
    public List<ClientWithDerived> execute() {
        final int expectancy = policy.expectancyYears();

        return clientRepository.findAll().stream().map(c -> {
            LocalDate dob = c.getDateOfBirth();
            LocalDate estimated = null;
            Double remaining = null;

            if (dob != null) {
                estimated = dob.plusYears(expectancy);
                // años restantes (solo años completos); luego redondeamos a 2 decimales
                double years = Period.between(LocalDate.now(), estimated).getYears();
                remaining = round2(years);

            } else if (c.getAge() != null) {
                int left = Math.max(0, expectancy - c.getAge());
                estimated = LocalDate.now().plusYears(left);
                remaining = round2((double) left);

            }

            return ClientWithDerived.builder()
                    .id(c.getId())
                    .firstName(c.getFirstName())
                    .lastName(c.getLastName())
                    .age(c.getAge())
                    .dateOfBirth(c.getDateOfBirth())
                    .estimatedLifeExpectancyDate(estimated)
                    .remainingYears(remaining)
                    .build();
        }).toList();
    }


    private static Double round2(Double v) {
        return v == null ? null : Math.round(v * 100.0) / 100.0;
    }
}
