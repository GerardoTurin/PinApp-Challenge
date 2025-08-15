package trackapp.icube04backend.modules.track_module.application.usescases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.common.exceptions.ExceptionDetail;
import trackapp.icube04backend.common.exceptions.GenericMessages;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IPositionRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeletePositionUseCase;



@UseCase
@RequiredArgsConstructor
public class DeletePositionUseCase implements IDeletePositionUseCase {
    private final IPositionRepository positionRepository;

    @Override
    public void execute(Long id) {
        var position = positionRepository.findById(id);

        if (position == null) {
            var exception = new ExceptionDetail(GenericMessages.OPERATION_NOT_COMPLETED);

            exception.addDetail("id", "La posición no existe");
            throw new BadRequestExceptionService(exception.getJson());
        }

        positionRepository.delete(position);
    }
}
