package trackapp.icube04backend.infrastructure.configuration.gcp.respository;

import java.io.IOException;

public interface IGCPRepository {
    String upload(String fileName, byte[] content) throws IOException;
    byte[] download(String objectName) throws IOException;
}
