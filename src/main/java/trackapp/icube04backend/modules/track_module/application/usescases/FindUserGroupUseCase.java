package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserGroupRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindUserGroupUseCase;

import java.util.List;


@UseCase
@RequiredArgsConstructor
public class FindUserGroupUseCase implements IFindUserGroupUseCase {

    private final IUserGroupRepository userGroupRepository;
    private final ISessionService sessionService;

    @Override
    public UserGroup findById(Long id) {
        var currentUserGroup = userGroupRepository.findById(id);

        if (currentUserGroup == null) {
            throw new BadRequestExceptionService("No existe el UserGroup con id: " + id);
        }

        return currentUserGroup;
    }

    @Override
    public List<UserGroup> findAllByCompanyIdAndByUserId(Long userId) {
        var companyId = sessionService.getCompanyId();

        var userGroups = userGroupRepository.findAllByCompanyIdAndByUserId(companyId, userId);
        return userGroups != null ? userGroups : List.of();
    }
}
