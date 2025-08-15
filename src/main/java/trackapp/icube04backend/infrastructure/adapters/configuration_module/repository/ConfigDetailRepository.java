package trackapp.icube04backend.infrastructure.adapters.configuration_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.db.jpa.ConfigDetailJPARepository;
import trackapp.icube04backend.infrastructure.db.model.ConfigDetailEntity;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ConfigDetailRepository implements IConfigDetailRepository {

    private final ConfigDetailJPARepository jpaRepository;

    @Override
    public ConfigDetail findByCode(String code) {
        return jpaRepository.findByCode(code).convertToDomain();
    }

    @Override
    public List<ConfigDetail> findByHeaderCode(String code) {
        return jpaRepository.findByHeaderCode(code).stream().map(ConfigDetailEntity::convertToDomain).toList();
    }

    @Override
    public ConfigDetail save(ConfigDetail configDetail) {
        return  jpaRepository.save(ConfigDetailEntity.convertFromDomain(configDetail)).convertToDomain();
    }

    @Override
    public ConfigDetail findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
