package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserGroupRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeleteUserGroupUseCase;




@UseCase
@RequiredArgsConstructor
public class DeleteUserGroupUseCase implements IDeleteUserGroupUseCase {
    private final IUserGroupRepository userGroupRepository;

    @Override
    public void execute(Long id) {
        var userGroup = userGroupRepository.findById(id);

        if (userGroup == null) {
            var exception = new ExceptionDetail(GenericMessages.RESOURCE_NOT_EXIST);

            exception.addDetail("id", "El grupo de usuario no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        userGroupRepository.delete(userGroup);
    }
}
