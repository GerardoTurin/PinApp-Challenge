package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateUserDeviceUseCase;




@Service
@RequiredArgsConstructor
public class CreateUserDeviceUseCase implements ICreateUserDeviceUseCase {

    private final IUserDeviceRepository userDeviceRepository;
    private final IConfigDetailRepository configDetailRepository;

    @Override
    public UserDevice execute(UserDevice userDevice) {

        //var configDetail = configDetailRepository.findByCode("c3ee58ff-bcb0-4c25-a281-41715036fd77");
        var configDetailId = configDetailRepository.findByCode("c3ee58ff-bcb0-4c25-a281-41715036fd77").getId();


        if (userDevice.getUser() == null || userDevice.getUser().getId() == null) {
            throw new BadRequestExceptionService("El usuario no puede estar vacío");
        }

        if (userDevice.getDevice() == null || userDevice.getDevice().getId() == null) {
            throw new BadRequestExceptionService("El dispositivo no puede estar vacío");
        }

        userDevice.setStatusConfigDetail(configDetailRepository.findById(configDetailId));

        if (userDevice.getStatusConfigDetail() == null || userDevice.getStatusConfigDetail().getId() == null) {
            throw new BadRequestExceptionService("El estado no puede estar vacío");
        }


        if (userDevice.getCreatedUser() == null || userDevice.getCreatedUser().getId() == null) {
            throw new BadRequestExceptionService("El usuario creador no puede estar vacío");
        }




        return userDeviceRepository.save(userDevice);
    }
}
