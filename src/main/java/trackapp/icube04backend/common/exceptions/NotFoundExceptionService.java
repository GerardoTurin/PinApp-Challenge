package trackapp.icube04backend.common.exceptions;

public class NotFoundExceptionService extends RuntimeException {
    public NotFoundExceptionService(String message) {
        super(message);
    }
}
