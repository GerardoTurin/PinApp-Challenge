package trackapp.icube04backend.modules.client_module.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.client_module.domain.models.Client;
import trackapp.icube04backend.modules.client_module.domain.ports.repository.IClientRepository;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.ICreateClientUseCase;

@Service
@RequiredArgsConstructor
public class CreateClientUseCase implements ICreateClientUseCase {
    private final IClientRepository clientRepository;
    private final ISessionService sessionService;


    @Override
    public Client execute(Client client) {
        var companyId = sessionService.getCompanyId();

        if (client.getFirstName() == null || client.getFirstName().trim().isEmpty()) {
            throw new BadRequestExceptionService("El nombre del Cliente no puede estar vacío");
        }

        if (client.getLastName() == null || client.getLastName().trim().isEmpty()) {
            throw new BadRequestExceptionService("El apellido del Cliente no puede estar vacío");
        }

        if (client.getAge() == null || client.getAge() < 0) {
            throw new BadRequestExceptionService("La edad del Cliente no puede ser vacia o negativa");
        }

        /*if (client.getDateOfBirth() == null) {
            throw new BadRequestExceptionService("La fecha de nacimiento del Cliente no puede estar vacía");
        }*/

        if (client.getUserId() == null) {
            throw new BadRequestExceptionService("El ID del usuario es requerido");
        }


        client.setCompanyId(companyId); // AsignO el ID de la compañía al cliente.


        Client createdClient = clientRepository.save(client);

        if (createdClient == null) {
            throw new BadRequestExceptionService("Error al crear el cliente");
        }
        // Retornar el cliente creado
        return createdClient;
    }
}
