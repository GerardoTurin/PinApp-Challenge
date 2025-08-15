package trackapp.icube04backend.modules.track_module.application.usescases;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.models.Position;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IPositionRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreatePositionUseCase;

@Service
@RequiredArgsConstructor
public class CreatePositionUseCase implements ICreatePositionUseCase {
    private final IPositionRepository positionRepository;
    private final IDeviceRepository deviceRepository;


    @Override
    public Position execute(Position position) {

        if (position.getDevice().getId() == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("deviceId", "El ID del dispositivo no puede ser nulo");
            throw new BadRequestExceptionService(exception.getJson());
        }

        validateDevice(position);

        return positionRepository.save(position);
    }



    private void validateDevice(Position position) {

        Device device = deviceRepository.findById(position.getDevice().getId());
        if (device == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("Id", "Id del dispositivo no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        position.setDevice(device);
    }


}
