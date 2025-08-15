package trackapp.icube04backend.infrastructure.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ConvertToByte {

    public static byte[] execute(MultipartFile file){
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}