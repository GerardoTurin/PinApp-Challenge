package trackapp.icube04backend.infrastructure.adapters.company_module.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.CompanyJPARepository;
import trackapp.icube04backend.infrastructure.db.model.CompanyEntity;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyRepository implements ICompanyRepository {

    private final CompanyJPARepository jpaRepository;

    @Transactional
    @Override
    public Company save(Company company) {
        var saved = jpaRepository.save(CompanyEntity.convertFromDomain(company));

        return saved.convertToDomain();
    }

    @Override
    public List<Company> findAll() {
        var companies = jpaRepository.findAll().stream().map(CompanyEntity::convertToDomain).toList();

        return companies;
    }

    @Override
    public Company findById(Long id) {
        var company = jpaRepository.findById(id);

        return company.get().convertToDomain();

    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
