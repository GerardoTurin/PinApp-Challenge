package trackapp.icube04backend.infrastructure.adapters.order_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import trackapp.icube04backend.infrastructure.db.jpa.EntidadJPARepository;
import trackapp.icube04backend.infrastructure.db.model.EntidadEntity;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.IEntidadRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EntidadRepository implements IEntidadRepository {

    private final EntidadJPARepository jpaRepository;

    @Override
    public List<Entidad> findByCompanyIdAndEntidadType(Long typeId, Long companyId) {
        return jpaRepository.findByCompanyIdAndEntidadType(typeId, companyId).stream().map(EntidadEntity::convertToDomain).toList();
    }

    @Override
    public Entidad save(Entidad entidad) {
        return jpaRepository.save(EntidadEntity.convertFromDomain(entidad)).convertToDomain();
    }

    @Override
    public Entidad findById(Long id) {
        return jpaRepository.findById(id).get().convertToDomain();
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
