package trackapp.icube04backend.infrastructure.adapters.track_module.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.CompanyConnectionJPARepository;
import trackapp.icube04backend.infrastructure.db.model.CompanyConnectionEntity;
import trackapp.icube04backend.modules.track_module.domain.models.CompanyConnection;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.ICompanyConnectionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CompanyConnectionRepository implements ICompanyConnectionRepository {
    private final CompanyConnectionJPARepository jpaRepository;

    @Override
    public void save(CompanyConnection companyConnection) {
        // Convierte a Entity y guarda
        CompanyConnectionEntity entity = new CompanyConnectionEntity().convertFromDomain(companyConnection);
        jpaRepository.save(entity);
    }

    @Override
    public List<CompanyConnection> findAll() {
        return jpaRepository.findAll().stream()
                .map(CompanyConnectionEntity::convertToDomain)
                .toList();
    }

    @Override
    public CompanyConnection findById(Long id) {
        return jpaRepository.findById(id)
                .map(CompanyConnectionEntity::convertToDomain)
                .orElse(null);
    }

    @Override
    public List<CompanyConnection> findAllByCompanyId(Long companyId) {
        return jpaRepository.findAllByCompanyId().stream()
                .map(CompanyConnectionEntity::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public CompanyConnection findByCompanyId(Long companyId) {
        return null;
    }

    @Override
    public List<CompanyConnection> findAllByUserId(Long userId) {
        return jpaRepository.findAllByUserId(userId).stream()
                .map(CompanyConnectionEntity::convertToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(CompanyConnection companyConnection) {
        CompanyConnectionEntity entity = new CompanyConnectionEntity().convertFromDomain(companyConnection);
        jpaRepository.delete(entity);
    }
}
