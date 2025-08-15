package trackapp.icube04backend.modules.track_module.domain.ports.repository;

import trackapp.icube04backend.modules.track_module.domain.models.Position;

import java.util.List;

public interface IPositionRepository {
    Position save(Position position);

    Position findById(Long id);

    List<Position> findAllByDeviceId(Long deviceId);

    List<Position> findAllCompanyIdAndDeviceId(Long companyId, Long deviceId);

    void delete(Position position);
}
