package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IUpdateDeviceUseCase;

import java.time.LocalDateTime;


@UseCase
@RequiredArgsConstructor
public class UpdateDeviceUseCase implements IUpdateDeviceUseCase {
    private final IDeviceRepository deviceRepository;


    @Override
    public Device execute(Device device) {
       var currentDevice = deviceRepository.findById(device.getId());

       if (currentDevice == null) {
            throw new BadRequestExceptionService("El dispositivo con ese Id no existe");
        }

       currentDevice.setName(device.getName());
       currentDevice.setUniqueId(device.getUniqueId());
       currentDevice.setPositionId(device.getPositionId());
       currentDevice.setLastUpdated(LocalDateTime.now());


        return deviceRepository.save(currentDevice);

    }
}
