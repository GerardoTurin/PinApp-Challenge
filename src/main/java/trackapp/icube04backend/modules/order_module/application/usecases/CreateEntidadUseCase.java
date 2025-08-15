package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomEntidadCreateRequest;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IEntidadRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateEntidadUseCase;


@Service
@RequiredArgsConstructor
public class CreateEntidadUseCase implements ICreateEntidadUseCase {

    private final IEntidadRepository entidadRepository;
    private final ISessionService sessionService;
    private final IConfigDetailRepository configDetailRepository;
    private final ICompanyRepository companyRepository;

    @Override
    public Entidad create(CustomEntidadCreateRequest request) {
        var status = configDetailRepository.findById(request.statusId());
        var entidadtype = configDetailRepository.findById(request.entidadTypeId());
        var identificaciontype = configDetailRepository.findById(request.identificationTypeId());
        var company = companyRepository.findById(sessionService.getCompanyId());

        return entidadRepository.save(Entidad.builder()
                        .identification(request.identification())
                        .name(request.name())
                        .lastname(request.lastname())
                        .address(request.address())
                        .phone(request.phone())
                        .email(request.email())
                        .identificationType(identificaciontype)
                        .entidadType(entidadtype)
                        .status(status)
                        .company(company)
                .build());
    }
}
