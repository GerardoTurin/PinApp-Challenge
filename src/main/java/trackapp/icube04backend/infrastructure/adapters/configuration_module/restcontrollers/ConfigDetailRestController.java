package trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.configuration_module.restcontrollers.dtos.requests.CustomConfigDetailUpdateRequest;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.ICreateConfigDetailUseCase;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IDeleteConfigDetailUseCase;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IGetConfigDetailByCodeUseCase;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IUpdateConfigDetailUseCase;

import java.util.List;

@RestController
@RequestMapping("/configdetails")
@RequiredArgsConstructor
@Slf4j
public class ConfigDetailRestController {

    private final IGetConfigDetailByCodeUseCase getConfigDetailByCodeUseCase;
    private final ICreateConfigDetailUseCase createConfigDetailUseCase;
    private final IDeleteConfigDetailUseCase deleteConfigDetailUseCase;
    private final IUpdateConfigDetailUseCase updateConfigDetailUseCase;

    @Operation(summary = "Get configDetails by his ConfigHeader CODE")
    @ApiResponse(responseCode = "200", description = "Respuesta exitosa")
    @Parameters({@Parameter(name = "code", description = "ConfigHeader Code", required = true)})
    @GetMapping("/header/{code}")
    public List<ConfigDetail> getByHeaderCode(@PathVariable String code){
        return getConfigDetailByCodeUseCase.findByHeaderCode(code);
    }

    @GetMapping("/detail/{code}")
    public ConfigDetail getByCode(@PathVariable String code){
        return getConfigDetailByCodeUseCase.findByCode(code);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CustomConfigDetailCreateRequest request){
        return createConfigDetailUseCase.create(request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        deleteConfigDetailUseCase.delete(id);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CustomConfigDetailUpdateRequest request){
        return updateConfigDetailUseCase.update(request);
    }
}
