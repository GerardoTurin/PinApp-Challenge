package trackapp.icube04backend.infrastructure.adapters.client_module.repository;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.ClientJPARepository;
import trackapp.icube04backend.infrastructure.db.jpa.projections.ClientAgeAggregatesProjection;
import trackapp.icube04backend.infrastructure.db.model.ClientEntity;
import trackapp.icube04backend.modules.client_module.domain.models.Client;
import trackapp.icube04backend.modules.client_module.domain.models.ClientStats;
import trackapp.icube04backend.modules.client_module.domain.ports.repository.IClientRepository;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class ClientRepository implements IClientRepository {

    private final ClientJPARepository jpaRepository;

    @Transactional
    @Override
    public Client save(Client client) {

        ClientEntity clientEntity = ClientEntity.convertFromDomain(client);
        return jpaRepository.save(clientEntity).convertToDomain();


    }

    @Override
    public Client findById(Long id) {
        return jpaRepository.findById(id)
                .map(ClientEntity::convertToDomain)
                .orElse(null);
    }

    @Override
    public List<Client> findAll() {
        return jpaRepository.findAll().stream()
                .map(ClientEntity::convertToDomain)
                .toList();
    }

    @Override
    public List<Client> findAllByCompanyIdAndByUserId(Long companyId, Long userId) {
        return jpaRepository.findAllByCompanyIdAndByUserId(companyId, userId).stream()
                .map(ClientEntity::convertToDomain)
                .toList();
    }

    @Override
    public List<Client> findAllByUserId(Long userId) {
        return List.of();
    }

    @Override
    public void delete(Client device) {
        jpaRepository.delete(ClientEntity.convertFromDomain(device));
    }

    @Override
    public ClientStats getStatsAll() {
        ClientAgeAggregatesProjection p = jpaRepository.computeAgeAggregatesAll();
        long total = p != null && p.getTotal() != null ? p.getTotal() : 0L;
        double avg  = p != null && p.getAverageAge() != null ? p.getAverageAge() : 0.0;
        double std  = p != null && p.getStdDevAge() != null ? p.getStdDevAge() : 0.0;

        return ClientStats.builder()
                .totalClients(total)
                .averageAge(avg)
                .stdDevAge(std)
                .build();
    }
}
