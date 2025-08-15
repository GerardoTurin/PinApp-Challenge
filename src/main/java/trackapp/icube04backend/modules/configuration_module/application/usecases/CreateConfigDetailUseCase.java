package trackapp.icube04backend.modules.configuration_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.company_module.repository.CompanyRepository;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.repository.ConfigHeaderRepository;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailCreateRequest;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigHeader;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.ICreateConfigDetailUseCase;


@Service
@RequiredArgsConstructor
public class CreateConfigDetailUseCase implements ICreateConfigDetailUseCase {

    private final IConfigDetailRepository configDetailRepository;
    private final CompanyRepository companyRepository;
    private final ConfigHeaderRepository configHeaderRepository;
    private final ISessionService sessionService;

    @Override
    public ResponseEntity<?> create(CustomConfigDetailCreateRequest request) {
        var company = companyRepository.findById(sessionService.getCompanyId());
        var parent = request.parentId() != null ? configDetailRepository.findById(request.parentId()) : null;
        var ch = configHeaderRepository.findById(request.headerId());

        if(validateConfigHeader(ch)){
            var tmp = configDetailRepository.save(ConfigDetail.builder()
                    .name(request.name())
                    .company(company)
                    .configHeader(ch)
                    .position(request.position() != null ? request.position() : null)
                    .parent(parent != null ? parent : null)
                    .isSystemParameter(request.systemParameter())
                    .attribute(request.attribute() != null ? request.attribute() : null)
                    .build());

            return ResponseEntity.ok(tmp);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("¡Error! No se puede ingresar el registro en el catálogo.");
        }


    }

    private boolean validateConfigHeader(ConfigHeader ch) {
        return !ch.isSystemParameter();
    }

}
