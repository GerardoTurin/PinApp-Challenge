package trackapp.icube04backend.infrastructure.adapters.company_module.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.modules.company_module.domain.models.File;
import trackapp.icube04backend.modules.company_module.domain.models.FileCompany;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.IStorageCompanyRepository;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.IUploadFileUseCase;

@Service
@RequiredArgsConstructor
public class StorageCompanyRepository implements IStorageCompanyRepository {

    private final IUploadFileUseCase uploadFileUseCase;

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;
    @Value("${spring.gcp.bucket.users}")
    private String bucketName;
    @Value("/storage?projectId=${spring.cloud.gcp.project-id}&bucketName=${spring.gcp.bucket.users}&objectName=")
    private String baseUrl;

    @Override
    public String uploadFile(FileCompany file) {
        return uploadFileUseCase.execute(new File(file.getFileName(), file.getFile()));

    }

    @Override
    public String uploadFile(FileCompany file, Long companyId) {
        return uploadFileUseCase.execute(new File(file.getFileName(), file.getFile()), companyId);
    }
}
