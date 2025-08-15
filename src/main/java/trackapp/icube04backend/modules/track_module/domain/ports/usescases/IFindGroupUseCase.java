package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.Group;

import java.util.List;

public interface IFindGroupUseCase {

    Group findById(Long id);

    List<Group> findAllByCompanyId();

    List<Group> findAllByCompanyIdAndByUserId(Long userId);
}
