package trackapp.icube04backend.common.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestExceptionService.class)
    public ResponseEntity<Object> handleBadRequest(BadRequestExceptionService ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    // ⬇️ NUEVO: 404 Not Found en texto plano
    @ExceptionHandler(NotFoundExceptionService.class)
    public ResponseEntity<Object> handleNotFound(NotFoundExceptionService ex) {
        // Podés usar ex.getMessage() o GenericMessages.RESOURCE_NOT_EXIST
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UnauthorizedExceptionService.class)
    public ResponseEntity<ExceptionDetail> handleUnauthorized(UnauthorizedExceptionService ex) {
        ExceptionDetail detail = new ExceptionDetail(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(detail);
    }

    @ExceptionHandler(InternalServerErrorExceptionService.class)
    public ResponseEntity<ExceptionDetail> handleInternalServerError(InternalServerErrorExceptionService ex) {
        ExceptionDetail detail = new ExceptionDetail(GenericMessages.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(detail);
    }

    @ExceptionHandler(UnauthorizedDetailExceptionService.class)
    public ResponseEntity<ExceptionDetail> handleUnauthorizedDetail(UnauthorizedDetailExceptionService ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getDetail());
    }
}
