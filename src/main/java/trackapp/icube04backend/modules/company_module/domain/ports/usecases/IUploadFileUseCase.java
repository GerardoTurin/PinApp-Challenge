package trackapp.icube04backend.modules.company_module.domain.ports.usecases;

import trackapp.icube04backend.modules.company_module.domain.models.File;

public interface IUploadFileUseCase {
    String execute (File file);

    String execute (File file, Long companyId);
}
