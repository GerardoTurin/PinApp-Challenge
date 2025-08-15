package trackapp.icube04backend.common.exceptions;

public class BadRequestExceptionService extends RuntimeException {
    public BadRequestExceptionService(String message) {
        super(message);
    }
}
