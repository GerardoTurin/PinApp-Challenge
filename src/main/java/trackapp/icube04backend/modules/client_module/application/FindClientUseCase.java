package trackapp.icube04backend.modules.client_module.application;


import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.client_module.domain.models.Client;
import trackapp.icube04backend.modules.client_module.domain.ports.repository.IClientRepository;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.IFindClientUseCase;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindClientUseCase implements IFindClientUseCase {

    private final IClientRepository clientRepository;
    private final ISessionService sessionService;


    @Override
    public Client findById(Long id) {
        var currrentClient = clientRepository.findById(id);

        if (currrentClient == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "No existe el dispositivo con id: " + id);
            throw new BadRequestExceptionService(exception.getJson());
        }

        return currrentClient;
    }


    @Override
    public List<Client> findAllByCompanyIdAndByUserId(Long userId) {
        var companyId = sessionService.getCompanyId();

        if (companyId == null || userId == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "No se puede obtener el id de la compañia o del usuario para obtener los clientes");
            throw new BadRequestExceptionService(exception.getJson());
        }

        var clients = clientRepository.findAllByCompanyIdAndByUserId(companyId, userId);
        return clients != null ? clients : List.of();
    }

    @Override
    public List<Client> findAllByUserId(Long userId) {
        if (userId == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "No se puede obtener el id del usuario para retornar sus clientes");
            throw new BadRequestExceptionService(exception.getJson());
        }

        var clients = clientRepository.findAllByUserId(userId);
        return clients != null ? clients : List.of();
    }

}
