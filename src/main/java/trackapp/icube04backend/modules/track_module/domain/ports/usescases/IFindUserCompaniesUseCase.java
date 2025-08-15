package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.UsersCompanies;

import java.util.List;

public interface IFindUserCompaniesUseCase {

    UsersCompanies findById(Long id);

    List<UsersCompanies> findAllByCompanyId(Long companyId);

    List<UsersCompanies> findAllByUserId(Long userId);
}
