package trackapp.icube04backend.modules.track_module.domain.ports.usescases;

import trackapp.icube04backend.modules.track_module.domain.models.Position;

public interface IUpdatePositionUseCase {
    Position execute(Position position);
}
