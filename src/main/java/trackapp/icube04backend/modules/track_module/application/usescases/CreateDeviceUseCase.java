package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateDeviceUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateUserDeviceUseCase;


@Service
@RequiredArgsConstructor
public class CreateDeviceUseCase implements ICreateDeviceUseCase {
    private final IDeviceRepository deviceRepository;
    private final ISessionService sessionService;
    private final ICreateUserDeviceUseCase createUserDeviceUseCase; // Inyecta el use case de UserDevice


    @Override
    public Device execute(Device device) {
        var companyId = sessionService.getCompanyId();

        if (device.getName() == null || device.getName().trim().isEmpty()) {
            throw new BadRequestExceptionService("El nombre del dispositivo no puede estar vacío");
        }

        if (device.getCreatedUser() == null || device.getCreatedUser().getId() == null) {
            throw new BadRequestExceptionService("Debe asociar un usuario al dispositivo");
        }

        device.setCompanyId(companyId);
        device.setLastUpdated(null);



        //return deviceRepository.save(device);
        Device createdDevice = deviceRepository.save(device);


        // Se crea el registro en UserDevice para el usuario creador y el dispositivo creado
        UserDevice userDevice = UserDevice.builder()
                .user(createdDevice.getCreatedUser())
                .device(createdDevice)
                .createdUser(createdDevice.getCreatedUser())
                //().createdAt(LocalDateTime.now())
                .build();

        createUserDeviceUseCase.execute(userDevice);

        return createdDevice;
    }
}
