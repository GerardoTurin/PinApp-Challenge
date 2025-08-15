package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeleteGroupUseCase;




@UseCase
@RequiredArgsConstructor
public class DeleteGroupUseCase implements IDeleteGroupUseCase {

    private final IGroupRepository groupRepository;

    @Override
    public void execute(Long id) {
        var group = groupRepository.findById(id);

        if (group == null) {
            var exception = new ExceptionDetail(GenericMessages.RESOURCE_NOT_EXIST);

            exception.addDetail("id", "El grupo no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        groupRepository.delete(group);
    }
}
