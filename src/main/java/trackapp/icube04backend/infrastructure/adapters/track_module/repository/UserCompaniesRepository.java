package trackapp.icube04backend.infrastructure.adapters.track_module.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.UserCompaniesJPARepository;
import trackapp.icube04backend.infrastructure.db.model.UserCompaniesEntity;
import trackapp.icube04backend.modules.track_module.domain.models.UsersCompanies;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserCompaniesRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserCompaniesRepository implements IUserCompaniesRepository {

    private final UserCompaniesJPARepository jpaRepository;

    @Override
    public UsersCompanies save(UsersCompanies userCompanies) {
        return null;
    }

    @Override
    public UsersCompanies findById(Long id) {
        return jpaRepository.findById(id)
                .map(UserCompaniesEntity::convertToDomain)
                .orElse(null);
    }

    @Override
    public List<UsersCompanies> findAllByCompanyId(Long companyId) {
        return List.of();
    }

    @Override
    public List<UsersCompanies> findAllByUserId(Long userId) {
        return jpaRepository.findAllByUserId(userId).stream()
                .map(UserCompaniesEntity::convertToDomain)
                .toList();
    }
}
