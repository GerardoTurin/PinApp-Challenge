package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomEntidadUpdateRequest;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IEntidadRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IUpdateEntidadUseCase;

@Service
@RequiredArgsConstructor
public class UpdateEntidadUseCase implements IUpdateEntidadUseCase {

    private final IEntidadRepository entidadRepository;
    private final ISessionService sessionService;
    private final IConfigDetailRepository configDetailRepository;
    private final ICompanyRepository companyRepository;

    @Override
    public Entidad update(CustomEntidadUpdateRequest request) {
        var old = entidadRepository.findById(request.id());

        var status = configDetailRepository.findById(request.statusId());
        var entidadtype = configDetailRepository.findById(request.entidadTypeId());
        var identificaciontype = configDetailRepository.findById(request.identificationTypeId());

        old.setIdentification(request.identification());
        old.setName(request.name());
        old.setLastname(request.lastname());
        old.setAddress(request.address());
        old.setPhone(request.phone());
        old.setEmail(request.email());
        old.setIdentificationType(identificaciontype);
        old.setEntidadType(entidadtype);
        old.setStatus(status);

        return entidadRepository.save(old);

    }
}
