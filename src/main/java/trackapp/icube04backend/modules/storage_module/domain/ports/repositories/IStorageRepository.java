package trackapp.icube04backend.modules.storage_module.domain.ports.repositories;

import trackapp.icube04backend.modules.company_module.domain.models.File;

public interface IStorageRepository {
    byte[] download(String fileName);

    String upload(File file);

    String upload(File file, Long companyId);
}
