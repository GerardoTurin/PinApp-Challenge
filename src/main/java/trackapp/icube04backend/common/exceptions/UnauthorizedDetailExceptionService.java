package trackapp.icube04backend.common.exceptions;

public class UnauthorizedDetailExceptionService extends RuntimeException {
    private final ExceptionDetail detail;

    public UnauthorizedDetailExceptionService(ExceptionDetail detail) {
        super(detail.getMessage());
        this.detail = detail;
    }

    public ExceptionDetail getDetail() {
        return detail;
    }
}
