package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.Group;

public interface IUpdateGroupUseCase {
    Group execute(Group group);
}
