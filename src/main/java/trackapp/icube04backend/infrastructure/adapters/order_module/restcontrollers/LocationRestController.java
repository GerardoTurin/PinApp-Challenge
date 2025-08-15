package trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomLocationCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomLocationUpdateRequest;
import trackapp.icube04backend.modules.order_module.domain.models.Location;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateLocationUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IDeleteLocationUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IGetLocationsUseCase;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IUpdateLocationUseCase;


import java.util.List;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
@Slf4j
public class LocationRestController {

    private final ICreateLocationUseCase createLocationUseCaseUseCase;
    private final IUpdateLocationUseCase updateLocationUseCase;
    private final IDeleteLocationUseCase deleteLocationUseCase;
    private final IGetLocationsUseCase getLocationsUseCase;


    @PostMapping("/create")
    public Location create(@RequestBody CustomLocationCreateRequest request){
        return createLocationUseCaseUseCase.create(request);
    }
    @PutMapping("/update")
    public Location update(@RequestBody CustomLocationUpdateRequest request){
        return updateLocationUseCase.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        deleteLocationUseCase.delete(id);
    }

    @GetMapping("/all")
    public List<Location> getAllByCompanyId(){
        return getLocationsUseCase.getAllByCompanyId();
    }
}
