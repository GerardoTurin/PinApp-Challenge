package trackapp.icube04backend.modules.client_module.application;


import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.modules.client_module.domain.ports.repository.IClientRepository;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.IDeleteClientUseCase;

@UseCase
@RequiredArgsConstructor
public class DeleteClientUseCase implements IDeleteClientUseCase {

    private final IClientRepository clientRepository;


    @Override
    public void execute(Long id) {
        var client = clientRepository.findById(id);

        if (client == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "El Cliente no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        clientRepository.delete(client);
    }
}
