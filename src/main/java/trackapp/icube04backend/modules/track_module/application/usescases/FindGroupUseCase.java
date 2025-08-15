package trackapp.icube04backend.modules.track_module.application.usescases;


import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.track_module.domain.models.Group;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindGroupUseCase;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindGroupUseCase implements IFindGroupUseCase {

    private final IGroupRepository groupRepository;
    private final ISessionService sessionService;

    @Override
    public Group findById(Long id) {
        var currentGroup = groupRepository.findById(id);

        if (currentGroup == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "No existe el grupo con id: " + id);
            throw new BadRequestExceptionService(exception.getJson());
        }

        return currentGroup;
    }

    @Override
    public List<Group> findAllByCompanyId() {
        var companyId = sessionService.getCompanyId();


        var groups = groupRepository.findAllByCompanyId(companyId);
        return groups != null ? groups : List.of();
    }

    @Override
    public List<Group> findAllByCompanyIdAndByUserId(Long userId) {
        var companyId = sessionService.getCompanyId();

        var groups = groupRepository.findAllByCompanyIdAndByUserId(companyId, userId);

        return groups != null ? groups : List.of();
    }
}
