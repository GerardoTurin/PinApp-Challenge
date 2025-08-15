package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.modules.track_module.domain.models.Group;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IUpdateGroupUseCase;




@UseCase
@RequiredArgsConstructor
public class UpdateGroupUseCase implements IUpdateGroupUseCase {
    private final IGroupRepository groupRepository;

    @Override
    public Group execute(Group group) {
        var currentGroup = groupRepository.findById(group.getId());

        if (currentGroup == null) {
            throw new BadRequestExceptionService("El grupo con ese Id no existe");
        }


        currentGroup.setName(group.getName());
        currentGroup.setCode(group.getCode());

        return groupRepository.save(currentGroup);
    }
}
