package trackapp.icube04backend.modules.company_module.domain.ports.repositories;

import org.springframework.stereotype.Repository;
import trackapp.icube04backend.modules.company_module.domain.models.Company;

import java.util.List;

@Repository
public interface ICompanyRepository {

    Company save(Company company);

    List<Company> findAll();

    Company findById(Long id);

    void deleteById(Long id);
}
