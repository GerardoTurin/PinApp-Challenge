package trackapp.icube04backend.infrastructure.adapters.storage_module.repository;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.configuration.gcp.respository.IGCPRepository;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.company_module.domain.models.File;
import trackapp.icube04backend.modules.storage_module.domain.ports.repositories.IStorageRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageRepository implements IStorageRepository {
    private final IGCPRepository repository;
    private final ISessionService sessionService;

    private static final List<String> ROOTS = Arrays.asList("companies", "users");

    @Value("${tenant-id}")
    private String tenantId;

    @Override
    public byte[] download(String fileName) {
        validateAccess(fileName);

        try {
            return repository.download(fileName);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String upload(File file) {
        file.setContentName(buildRoute(file.getContentName()));
        try {
            return repository.upload(
                    file.getContentName(),
                    file.getContent());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    @Override
    public String upload(File file, Long companyId) {
        file.setContentName("tenants/" + tenantId + "/companies/" + companyId + "/" + file.getContentName());
        try {
            return repository.upload(
                    file.getContentName(),
                    file.getContent());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }

        return null;

    }

    private void validateAccess(String fileName) {
        var split = fileName.split("/");

        if (!tenantId.equals(split[1])) {
        }

        if (!ROOTS.contains(split[2])) {
        }

        if ("companies".equals(split[2]) && !"company".equals(split[4]) && !sessionService.getUsersCompanies().contains(Integer.parseInt(split[3]))) {
        }
    }

    private String buildRoute(String contentName) {
        var split = contentName.split("/");

        if ("users".equals(split[0])) {
            return "tenants/" + sessionService.getTenantId() + "/" + contentName;
        }
        return sessionService.getUrlBase() + contentName;
    }


}
