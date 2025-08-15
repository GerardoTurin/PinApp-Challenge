package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomEntidadCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomEntidadUpdateRequest;
import trackapp.icube04backend.modules.order_module.domain.models.Entidad;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateEntidadUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IDeleteEntidadUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IGetEntidadUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IUpdateEntidadUseCase;

import java.util.List;

@RestController
@RequestMapping("/entidades")
@RequiredArgsConstructor
@Slf4j
public class EntidadRestController {

    private final IGetEntidadUseCase getEntidadUseCase;
    private final ICreateEntidadUseCase createEntidadUseCase;
    private final IUpdateEntidadUseCase updateEntidadUseCase;
    private final IDeleteEntidadUseCase deleteEntidadUseCase;

    @GetMapping("/{typeId}")
    public List<Entidad> getAllByCompanyIdAndEntidadType(@PathVariable Long typeId){
        return getEntidadUseCase.getByCompanyIdAndEntidadType(typeId);
    }

    @PostMapping("/create")
    public Entidad create(@RequestBody CustomEntidadCreateRequest request){
        return createEntidadUseCase.create(request);
    }

    @PutMapping("/update")
    public Entidad update(@RequestBody CustomEntidadUpdateRequest request){
        return updateEntidadUseCase.update(request);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        deleteEntidadUseCase.delete(id);
    }
}
