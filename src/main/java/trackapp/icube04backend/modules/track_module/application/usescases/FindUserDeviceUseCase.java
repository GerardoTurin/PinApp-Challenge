package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.track_module.domain.models.UserDevice;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IUserDeviceRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindUserDeviceUseCase;

import java.util.List;


@UseCase
@RequiredArgsConstructor
public class FindUserDeviceUseCase implements IFindUserDeviceUseCase {

    private final IUserDeviceRepository userDeviceRepository;
    private final ISessionService sessionService;

    @Override
    public UserDevice findById(Long id) {

        var currentUserDevice = userDeviceRepository.findById(id);

        if (currentUserDevice == null) {
            throw new BadRequestExceptionService("No existe el UserDevice con id: " + id);
        }

        return currentUserDevice;
    }

    @Override
    public List<UserDevice> findAllByCompanyIdAndByUserId(Long userId) {
        var companyId = sessionService.getCompanyId();

        var userDevices = userDeviceRepository.findAllByCompanyIdAndByUserId(companyId, userId);
        return userDevices != null ? userDevices : List.of();
    }
}
