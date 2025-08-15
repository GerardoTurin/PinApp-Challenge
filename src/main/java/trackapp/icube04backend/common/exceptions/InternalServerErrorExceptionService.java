package trackapp.icube04backend.common.exceptions;

public class InternalServerErrorExceptionService extends RuntimeException {
    public InternalServerErrorExceptionService(String message) {
        super(message);
    }
}
