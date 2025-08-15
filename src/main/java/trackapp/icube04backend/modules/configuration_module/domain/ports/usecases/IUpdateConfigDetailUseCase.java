package trackapp.icube04backend.modules.configuration_module.domain.ports.usecases;

import org.springframework.http.ResponseEntity;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailUpdateRequest;

public interface IUpdateConfigDetailUseCase {

    ResponseEntity<?> update(CustomConfigDetailUpdateRequest configDetail);
}
