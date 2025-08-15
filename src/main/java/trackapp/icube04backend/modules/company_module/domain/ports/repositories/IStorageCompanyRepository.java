package trackapp.icube04backend.modules.company_module.domain.ports.repositories;

import trackapp.icube04backend.modules.company_module.domain.models.FileCompany;

public interface IStorageCompanyRepository {
    String uploadFile(FileCompany file);

    String uploadFile(FileCompany file, Long companyId);
}
