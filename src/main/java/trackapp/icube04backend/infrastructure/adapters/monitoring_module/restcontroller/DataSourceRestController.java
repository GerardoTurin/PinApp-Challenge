package trackapp.icube04backend.infrastructure.adapters.monitoring_module.restcontroller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.monitoring_module.restcontroller.dtos.DataSourceCreateRequest;
import trackapp.icube04backend.modules.monitoring_module.domain.ports.usecases.ICreateDataSourceUseCase;
import trackapp.icube04backend.modules.monitoring_module.domain.ports.usecases.IFindDataSourceUseCase;

@RestController
@RequestMapping("/datasource")
@RequiredArgsConstructor
@Slf4j
public class DataSourceRestController {

    private final ICreateDataSourceUseCase createDataSourceUseCase;
    private final IFindDataSourceUseCase findDataSourceUseCase;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DataSourceCreateRequest request) {
        try {
            //
            var entity = createDataSourceUseCase.execute(request.convertToDomain());
            return ResponseEntity.ok(entity);
        } catch (Exception expd) {
            //
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al Crear");
        }
    }

    @GetMapping("/entity/{entityId}")
    public ResponseEntity<?> findByEntity(@PathVariable Long entityId) {
        try {
            //
            var entities = findDataSourceUseCase.getByEntity(entityId);
            return ResponseEntity.ok(entities);

        } catch (Exception ecxt) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al responder");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdCompany(@PathVariable Long id) {
        try {
            var entity = findDataSourceUseCase.getByIdAndCompany(id);
            return ResponseEntity.ok(entity);
        } catch (Exception ewer) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al recuperar");
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll () {
        try {
            var entities = findDataSourceUseCase.getAllByCompany();
            return ResponseEntity.ok(entities);
        } catch (Exception eqq) {
            //throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al recuperar");
        }
    }
}
