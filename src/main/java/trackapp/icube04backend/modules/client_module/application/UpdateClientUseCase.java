package trackapp.icube04backend.modules.client_module.application;


import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.modules.client_module.domain.models.Client;
import trackapp.icube04backend.modules.client_module.domain.ports.repository.IClientRepository;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.IUpdateClientUseCase;


@UseCase
@RequiredArgsConstructor
public class UpdateClientUseCase implements IUpdateClientUseCase {
    private final IClientRepository clientRepository;


    @Override
    public Client execute(Client client) {
        var currentClient = clientRepository.findById(client.getId());

        if (currentClient == null) {
            throw new BadRequestExceptionService("El cliente con ese Id no existe");
        }

        currentClient.setFirstName(client.getFirstName());
        currentClient.setLastName(client.getLastName());
        currentClient.setAge(client.getAge());
        currentClient.setDateOfBirth(client.getDateOfBirth());

        return clientRepository.save(currentClient);
    }
}
