package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserGroupRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateUserGroupUseCase;




@Service
@RequiredArgsConstructor
public class CreateUserGroupUseCase implements ICreateUserGroupUseCase {

    private final IUserGroupRepository userGroupRepository;

    @Override
    public UserGroup execute(UserGroup userGroup) {

        if (userGroup.getUser() == null || userGroup.getUser().getId() == null) {
            throw new BadRequestExceptionService("El usuario no puede estar vacío");
        }

        if (userGroup.getGroup() == null || userGroup.getGroup().getId() == null) {
            throw new BadRequestExceptionService("El grupo no puede estar vacío");
        }

        return userGroupRepository.save(userGroup);
    }
}
