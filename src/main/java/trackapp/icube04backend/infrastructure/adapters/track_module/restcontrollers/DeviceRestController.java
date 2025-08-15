package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.DeviceCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.DeviceResponse;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.DeviceUpdateRequest;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateDeviceUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeleteDeviceUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindDeviceUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IUpdateDeviceUseCase;

import java.util.List;

@RestController
@RequestMapping("/devices")
@RequiredArgsConstructor
@Tag(name = "Devices", description = "CRUD")
@Slf4j
public class DeviceRestController {

    private final IFindDeviceUseCase findDeviceUseCase;
    private final ICreateDeviceUseCase createDeviceUseCase;
    private final IUpdateDeviceUseCase updateDeviceUseCase;
    private final IDeleteDeviceUseCase deleteDeviceUseCase;



    @GetMapping("/user/{userId}")
    public List<DeviceResponse> getAllByUserId(@PathVariable Long userId) {
        return findDeviceUseCase.findAllByCompanyIdAndByUserId(userId)
                .stream()
                .map(DeviceResponse::convertFromDomain)
                .toList();
    }



    @GetMapping("/only-user/{userId}")
    public List<DeviceResponse> getAllByOnlyUserId(@PathVariable Long userId) {
        return findDeviceUseCase.findAllByUserId(userId)
                .stream()
                .map(DeviceResponse::convertFromDomain)
                .toList();
    }







    @GetMapping("/{id}")
    public DeviceResponse getDeviceById(@PathVariable Long id) {
        var device = findDeviceUseCase.findById(id);
        return DeviceResponse.convertFromDomain(device);
    }



    @PostMapping
    public DeviceResponse createDevice(@RequestBody DeviceCreateRequest deviceCreateRequest) {
        var createdDevice = createDeviceUseCase.execute(deviceCreateRequest.convertToDomain());
        return DeviceResponse.convertFromDomain(createdDevice);
    }




    @PutMapping
    public DeviceResponse updateDevice(@RequestBody DeviceUpdateRequest deviceUpdateRequest) {
        var updatedDevice = updateDeviceUseCase.execute(deviceUpdateRequest.convertToDomain());
        return DeviceResponse.convertFromDomain(updatedDevice);
    }


    @DeleteMapping("{id}")
    public void deleteDevice(@PathVariable Long id) {
        deleteDeviceUseCase.execute(id);
    }

}
