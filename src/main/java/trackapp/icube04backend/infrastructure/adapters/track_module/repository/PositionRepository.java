package trackapp.icube04backend.infrastructure.adapters.track_module.repository;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.PositionJPARepository;
import trackapp.icube04backend.infrastructure.db.model.PositionEntity;
import trackapp.icube04backend.modules.track_module.domain.models.Position;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IPositionRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PositionRepository implements IPositionRepository {

    private final PositionJPARepository jpaRepository;


    @Transactional
    @Override
    public Position save(Position position) {
        PositionEntity positionEntity = PositionEntity.convertFromDomain(position);
        return jpaRepository.save(positionEntity).convertToDomain();
    }

    @Override
    public Position findById(Long id) {

        return jpaRepository.findById(id)
                .map(PositionEntity::convertToDomain)
                .orElse(null);

    }

    @Override
    public List<Position> findAllByDeviceId(Long deviceId) {

        return jpaRepository.findAllByDeviceId(deviceId).stream()
                .map(PositionEntity::convertToDomain)
                .toList();
    }

    @Override
    public List<Position> findAllCompanyIdAndDeviceId(Long companyId, Long deviceId) {
        return List.of();
    }

    @Transactional
    @Override
    public void delete(Position position) {
        jpaRepository.delete(PositionEntity.convertFromDomain(position));
    }
}
