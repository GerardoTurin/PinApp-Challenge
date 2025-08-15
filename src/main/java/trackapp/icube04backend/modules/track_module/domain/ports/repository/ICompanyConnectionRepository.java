package trackapp.icube04backend.modules.track_module.domain.ports.repository;

import trackapp.icube04backend.modules.track_module.domain.models.CompanyConnection;

import java.util.List;

public interface ICompanyConnectionRepository {

    void save(CompanyConnection companyConnection);

    List<CompanyConnection> findAll();

    CompanyConnection findById(Long id);

    List<CompanyConnection> findAllByCompanyId(Long companyId);

    CompanyConnection findByCompanyId(Long companyId);

    List<CompanyConnection> findAllByUserId(Long userId);

    void delete(CompanyConnection companyConnection);
}
