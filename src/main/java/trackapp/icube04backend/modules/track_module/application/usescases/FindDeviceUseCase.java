package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.track_module.domain.models.Device;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindDeviceUseCase;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindDeviceUseCase implements IFindDeviceUseCase {

    private final IDeviceRepository deviceRepository;
    private final ISessionService sessionService;

    @Override
    public Device findById(Long id) {
        var currentDevice = deviceRepository.findById(id);

        if (currentDevice == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "No existe el dispositivo con id: " + id);
            throw new BadRequestExceptionService(exception.getJson());
        }

        return currentDevice;
    }

    @Override
    public List<Device> findAllByCompanyId(Long companyId) {
        return List.of();
    }



    @Override
    public List<Device> findAllByCompanyIdAndByUserId(Long userId) {

        var companyId = sessionService.getCompanyId();

        if (companyId == null || userId == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "No se puede obtener el id de la compañia o del usuario");
            throw new BadRequestExceptionService(exception.getJson());
        }

        var devices = deviceRepository.findAllByCompanyIdAndByUserId(companyId, userId);
        return devices != null ? devices : List.of();
    }

    @Override
    public List<Device> findAllByUserId(Long userId) {

        if (userId == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "No se puede obtener el id del usuario");
            throw new BadRequestExceptionService(exception.getJson());
        }

        var devices = deviceRepository.findAllByUserId(userId);
        return devices != null ? devices : List.of();

    }
}
