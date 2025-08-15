package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserGroupRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IUpdateUserGroupUseCase;




@UseCase
@RequiredArgsConstructor
public class UpdateUserGroupUseCase implements IUpdateUserGroupUseCase {
    private final IUserGroupRepository userGroupRepository;

    @Override
    public UserGroup execute(UserGroup userGroup) {

        var currentUserGroup = userGroupRepository.findById(userGroup.getId());

        if (currentUserGroup == null) {
            throw new BadRequestExceptionService("El UserGroup con ese Id no existe");
        }

        currentUserGroup.setUser(userGroup.getUser());
        currentUserGroup.setGroup(userGroup.getGroup());

        return userGroupRepository.save(currentUserGroup);
    }
}
