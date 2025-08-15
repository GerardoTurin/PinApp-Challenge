package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.models.Group;
import trackapp.icube04backend.modules.track_module.domain.models.GroupDevice;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateGroupDeviceUseCase;




@Service
@RequiredArgsConstructor
public class CreateGroupDeviceUseCase implements ICreateGroupDeviceUseCase {

    private final IGroupDeviceRepository groupDeviceRepository;
    private final IGroupRepository groupRepository;
    private final IDeviceRepository deviceRepository;

    @Override
    public GroupDevice execute(GroupDevice groupDevice) {

        if (groupDevice.getDevice().getId() == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("deviceId", "El ID del dispositivo no puede ser nulo");
            throw new BadRequestExceptionService(exception.getJson());
        }

        if (groupDevice.getGroup().getId() == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("groupId", "El ID del grupo no puede ser nulo");
            throw new BadRequestExceptionService(exception.getJson());
        }

        if (groupDevice.getCreatedUser().getId() == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("createdUserId", "El ID del usuario creador no puede ser nulo");
            throw new BadRequestExceptionService(exception.getJson());
        }

        validateGroup(groupDevice);
        validateDevice(groupDevice);

        return groupDeviceRepository.save(groupDevice);
    }



    private void validateDevice(GroupDevice groupDevice) {

        Device device = deviceRepository.findById(groupDevice.getDevice().getId());
        if (device == null) {
            var exception = new ExceptionDetail(GenericMessages.RESOURCE_NOT_EXIST);

            exception.addDetail("Id", "Id del dispositivo no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        groupDevice.setDevice(device);
    }


    private void validateGroup(GroupDevice groupDevice) {

        Group group = groupRepository.findById(groupDevice.getGroup().getId());
        if (group == null) {
            var exception = new ExceptionDetail(GenericMessages.RESOURCE_NOT_EXIST);

            exception.addDetail("Id", "Id del grupo no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        groupDevice.setGroup(group);
    }
}
