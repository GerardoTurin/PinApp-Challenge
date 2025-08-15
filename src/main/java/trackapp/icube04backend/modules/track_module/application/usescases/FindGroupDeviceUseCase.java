package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindGroupDeviceUseCase;

import java.util.List;


@UseCase
@RequiredArgsConstructor
public class FindGroupDeviceUseCase implements IFindGroupDeviceUseCase {

    private final IGroupDeviceRepository groupDeviceRepository;
    private final ISessionService sessionService;

    @Override
    public GroupDevice findById(Long id) {
        var currentGroupDevice = groupDeviceRepository.findById(id);

        if (currentGroupDevice == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "No existe el GroupDevice con id: " + id);
            throw new BadRequestExceptionService(exception.getJson());
        }

        return currentGroupDevice;
    }

    @Override
    public List<GroupDevice> findAllByCompanyIdAndByUserId(Long userId) {
        var companyId = sessionService.getCompanyId();

        var groupDevices = groupDeviceRepository.findAllByCompanyIdAndByUserId(companyId, userId);
        return groupDevices != null ? groupDevices : List.of();
    }
}
