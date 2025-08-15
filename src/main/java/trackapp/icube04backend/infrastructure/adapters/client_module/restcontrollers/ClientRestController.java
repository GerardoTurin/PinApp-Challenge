package trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.common.messaging.publishers.ClientEventPublisher;
import trackapp.icube04backend.infrastructure.adapters.client_module.restcontrollers.dtos.*;
import trackapp.icube04backend.modules.client_module.domain.ports.usecases.*;


import java.util.List;

@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@Tag(name = "Clients", description = "CRUD")
@Slf4j
public class ClientRestController {

    private final ICreateClientUseCase createClientUseCase;
    private final IFindClientUseCase findClientUseCase;
    private final IUpdateClientUseCase updateClientUseCase;
    private final IDeleteClientUseCase deleteClientUseCase;

    private final IGetClientStatsUseCase getClientStatsUseCase;
    private final IFindClientsWithDerivedUseCase listClientsWithDerivedUseCase;

    private final ClientEventPublisher clientEventPublisher;



    @PreAuthorize("hasAuthority('SOMAS_SUPER_ADMIN')")
    @PostMapping
    public ClientResponse createClient(@RequestBody ClientCreateRequest clientCreateRequest) {
        var client = createClientUseCase.execute(clientCreateRequest.convertToDomain());

        // <<< nuevo: publicar evento
        clientEventPublisher.publishClientCreated(client);

        return ClientResponse.convertFromDomain(client);
    }



    @PutMapping("/update/{id}")
    public ClientResponse updateClient(@PathVariable Long id, @RequestBody ClientUpdateRequest clientUpdateRequest) {
        var client = clientUpdateRequest.convertToDomain(id);
        var updatedClient = updateClientUseCase.execute(client);
        return ClientResponse.convertFromDomain(updatedClient);
    }






    @GetMapping("/{id}")
    public ClientResponse getClientById(@PathVariable Long id) {
        var client = findClientUseCase.findById(id);
        return ClientResponse.convertFromDomain(client);
    }



    @GetMapping("/user-company/{userId}")
    public List<ClientResponse> getAllByCompanyIdAndByUserId(@PathVariable Long userId) {
        return findClientUseCase.findAllByCompanyIdAndByUserId(userId)
                .stream()
                .map(ClientResponse::convertFromDomain)
                .toList();
    }




    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable Long id) {
        deleteClientUseCase.execute(id);
    }



    @GetMapping("/stats")
    public ClientStatsResponse getClientStats() {
        var stats = getClientStatsUseCase.execute();
        return ClientStatsResponse.convertFromDomain(stats);
    }



    @GetMapping("/derived")
    public List<ClientWithDerivedResponse> listWithDerived() {
        return listClientsWithDerivedUseCase.execute()
                .stream()
                .map(ClientWithDerivedResponse::convertFromDomain)
                .toList();
    }



}

