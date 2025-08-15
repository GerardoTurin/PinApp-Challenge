package trackapp.icube04backend.modules.storage_module.application.usecases;

import lombok.RequiredArgsConstructor;
import trackapp.icube04backend.common.annotations.UseCase;
import trackapp.icube04backend.modules.company_module.domain.models.File;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.IUploadFileUseCase;
import trackapp.icube04backend.modules.storage_module.domain.ports.repositories.IStorageRepository;


@UseCase
@RequiredArgsConstructor
public class UploadFileUseCase implements IUploadFileUseCase {

    private final IStorageRepository repository;

    @Override
    public String execute(File file) {
        return repository.upload(file);
    }

    @Override
    public String execute(File file, Long companyId) {
        return repository.upload(file, companyId);
    }
}
