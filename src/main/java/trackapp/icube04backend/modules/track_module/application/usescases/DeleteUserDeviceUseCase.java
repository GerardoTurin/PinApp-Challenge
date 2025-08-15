package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeleteUserDeviceUseCase;





@UseCase
@RequiredArgsConstructor
public class DeleteUserDeviceUseCase implements IDeleteUserDeviceUseCase {

    private final IUserDeviceRepository userDeviceRepository;

    @Override
    public void execute(Long id) {
        var userDevice = userDeviceRepository.findById(id);

        if (userDevice == null) {
            var exception = new ExceptionDetail(GenericMessages.RESOURCE_NOT_EXIST);

            exception.addDetail("id", "El UserDevice no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        userDeviceRepository.delete(userDevice);
    }
}
