package trackapp.icube04backend.modules.track_module.domain.ports.repository;

import trackapp.icube04backend.infrastructure.db.model.UserCompaniesEntity;
import trackapp.icube04backend.modules.track_module.domain.models.UsersCompanies;

import java.util.List;

public interface IUserCompaniesRepository {

    UsersCompanies save(UsersCompanies userCompanies);

    UsersCompanies findById(Long id);

    List<UsersCompanies> findAllByCompanyId(Long companyId);

    List<UsersCompanies> findAllByUserId(Long userId);
}
