package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeleteDeviceUseCase;




@UseCase
@RequiredArgsConstructor
public class DeleteDeviceUseCase implements IDeleteDeviceUseCase {

    private final IDeviceRepository deviceRepository;

    @Override
    public void execute(Long id) {
        var device = deviceRepository.findById(id);

        if (device == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "El dispositivo no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        deviceRepository.delete(device);
    }
}
