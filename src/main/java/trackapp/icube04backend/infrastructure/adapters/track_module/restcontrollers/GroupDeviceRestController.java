package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.GroupDeviceCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.GroupDeviceResponse;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.GroupDeviceUpdateRequest;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateGroupDeviceUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeleteGroupDeviceUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindGroupDeviceUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IUpdateGroupDeviceUseCase;

import java.util.List;

@RestController
@RequestMapping("/group-devices")
@RequiredArgsConstructor
@Tag(name = "Group_Devices", description = "CRUD")
@Slf4j
public class GroupDeviceRestController {

    private final IFindGroupDeviceUseCase findGroupDeviceUseCase;
    private final ICreateGroupDeviceUseCase createGroupDeviceUseCase;
    private final IUpdateGroupDeviceUseCase updateGroupDeviceUseCase;
    private final IDeleteGroupDeviceUseCase deleteGroupDeviceUseCase;



    @GetMapping("/user/{userId}")
    public List<GroupDeviceResponse> getAllByCompanyIdAndByUserId(@PathVariable Long userId) {
        return findGroupDeviceUseCase.findAllByCompanyIdAndByUserId(userId)
                .stream()
                .map(GroupDeviceResponse::convertFromDomain)
                .toList();
    }




    @GetMapping("/{id}")
    public GroupDeviceResponse getGroupDeviceById(@PathVariable Long id) {
        var groupDevice = findGroupDeviceUseCase.findById(id);
        return GroupDeviceResponse.convertFromDomain(groupDevice);
    }




    @PostMapping
    public GroupDeviceResponse createGroupDevice(@RequestBody GroupDeviceCreateRequest groupDeviceCreateRequest) {
        var createdGroupDevice = createGroupDeviceUseCase.execute(groupDeviceCreateRequest.convertToDomain());
        return GroupDeviceResponse.convertFromDomain(createdGroupDevice);
    }




    @PutMapping
    public GroupDeviceResponse updateGroupDevice(@RequestBody GroupDeviceUpdateRequest groupDeviceUpdateRequest) {
        var updatedGroupDevice = updateGroupDeviceUseCase.execute(groupDeviceUpdateRequest.convertToDomain());
        return GroupDeviceResponse.convertFromDomain(updatedGroupDevice);
    }



    @DeleteMapping("{id}")
    public void deleteGroupDevice(@PathVariable Long id) {
        deleteGroupDeviceUseCase.execute(id);
    }
}
