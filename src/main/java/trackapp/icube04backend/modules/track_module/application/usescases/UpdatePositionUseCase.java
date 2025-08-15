package trackapp.icube04backend.modules.track_module.application.usescases;


import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.modules.track_module.domain.models.Position;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IPositionRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IUpdatePositionUseCase;

@UseCase
@RequiredArgsConstructor
public class UpdatePositionUseCase implements IUpdatePositionUseCase {
    private final IPositionRepository positionRepository;


    @Override
    public Position execute(Position position) {
        var currentPosition = positionRepository.findById(position.getId());

        if (currentPosition == null) {
            throw new BadRequestExceptionService("La posición con ese Id no existe");
        }

        currentPosition.setLatitude(position.getLatitude());
        currentPosition.setLongitude(position.getLongitude());
        currentPosition.setNearbyPoint(position.getNearbyPoint());
        currentPosition.setAltitude(position.getAltitude());
        currentPosition.setSpeed(position.getSpeed());
        currentPosition.setAddress(position.getAddress());
        currentPosition.setAttributes(position.getAttributes());

        currentPosition.setDeviceTime(position.getDeviceTime());
        //currentPosition.setDeviceTime(LocalDateTime.now());

        currentPosition.setFixTime(position.getFixTime());
        //currentPosition.setFixTime(LocalDateTime.now());

        return positionRepository.save(currentPosition);
    }
}
