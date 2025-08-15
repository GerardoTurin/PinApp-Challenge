package trackapp.icube04backend.modules.configuration_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailUpdateRequest;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigHeader;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigHeaderRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IUpdateConfigDetailUseCase;


@Service
@RequiredArgsConstructor
public class UpdateConfigDetailUseCase implements IUpdateConfigDetailUseCase {

    private final IConfigDetailRepository configDetailRepository;
    private final IConfigHeaderRepository configHeaderRepository;
    private final ICompanyRepository companyRepository;
    private final ISessionService sessionService;


    @Override
    public ResponseEntity<?> update(CustomConfigDetailUpdateRequest request) {
        var old = configDetailRepository.findById(request.id());
        var ch = request.configHeaderId() != null ? configHeaderRepository.findById(request.configHeaderId()) : null;
        var parent = request.parentId() != null ? configDetailRepository.findById(request.parentId()) : null;
        var company = companyRepository.findById(sessionService.getCompanyId());

        if(validateConfigHeader(ch)){
            var nuevo = ConfigDetail.builder()
                    .id(request.id())
                    .name(request.name())
                    .company(company)
                    .configHeader(ch != null ? ch : null)
                    .position(request.position() != null ? request.position() : null)
                    .parent(parent != null ? parent : null)
                    .isSystemParameter(request.systemParameter())
                    .attribute(request.attribute() != null ? request.attribute() : null)
                    .build();

            return ResponseEntity.ok( configDetailRepository.save(nuevo));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: ConfigHeader systemParameter true");
        }
    }

    private boolean validateConfigHeader(ConfigHeader ch) {
        return !ch.isSystemParameter();
    }
}
