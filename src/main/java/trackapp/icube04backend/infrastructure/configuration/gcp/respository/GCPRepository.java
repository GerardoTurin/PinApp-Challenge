package trackapp.icube04backend.infrastructure.configuration.gcp.respository;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;


@RequiredArgsConstructor
@Service
public class GCPRepository implements IGCPRepository {

    @Value("${spring.cloud.gcp.project-id}")
    private String projectId;

    @Value("${spring.gcp.bucket.users}")
    private String bucketName;

    @Override
    public String upload(String objectName, byte[] content) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/gcp/credential.json");
        assert inputStream != null;

        Storage storage = StorageOptions.newBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build()
                .getService();
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        Storage.BlobTargetOption precondition;
        if (storage.get(bucketName, objectName) == null) {

            precondition = Storage.BlobTargetOption.doesNotExist();
        } else {
            precondition =
                    Storage.BlobTargetOption.generationMatch(
                            storage.get(bucketName, objectName).getGeneration());
        }
        storage.create(blobInfo, content, precondition);

        return objectName;
    }

    @Override
    public byte[] download(String objectName) throws IOException {

        InputStream inputStream = getClass().getResourceAsStream("/gcp/credential.json");
        assert inputStream != null;

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId)
                .setCredentials(GoogleCredentials.fromStream(inputStream))
                .build().getService();
        return storage.readAllBytes(bucketName, objectName);
    }

}