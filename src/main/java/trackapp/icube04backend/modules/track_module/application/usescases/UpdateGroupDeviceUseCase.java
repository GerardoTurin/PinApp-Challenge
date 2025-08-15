package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IUpdateGroupDeviceUseCase;



@UseCase
@RequiredArgsConstructor
public class UpdateGroupDeviceUseCase implements IUpdateGroupDeviceUseCase {

    private final IGroupDeviceRepository groupDeviceRepository;

    @Override
    public GroupDevice execute(GroupDevice groupDevice) {
        var currentGroupDevice = groupDeviceRepository.findById(groupDevice.getId());

        if (currentGroupDevice == null) {
            throw new BadRequestExceptionService("El GroupDevice con ese Id no existe");
        }

        currentGroupDevice.setGroup(groupDevice.getGroup());
        currentGroupDevice.setDevice(groupDevice.getDevice());

        return groupDeviceRepository.save(currentGroupDevice);
    }
}
