package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeleteGroupDeviceUseCase;



@UseCase
@RequiredArgsConstructor
public class DeleteGroupDeviceUseCase implements IDeleteGroupDeviceUseCase {

    private final IGroupDeviceRepository groupDeviceRepository;

    @Override
    public void execute(Long id) {

        var groupDevice = groupDeviceRepository.findById(id);

        if (groupDevice == null) {
            var exception = new ExceptionDetail(GenericMessages.RESOURCE_NOT_EXIST);

            exception.addDetail("id", "El GroupDevice no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        groupDeviceRepository.delete(groupDevice);
    }
}
