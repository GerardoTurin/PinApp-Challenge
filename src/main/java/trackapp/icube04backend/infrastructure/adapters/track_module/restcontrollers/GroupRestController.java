package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.GroupCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.GroupResponse;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.GroupUpdateRequest;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateGroupUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeleteGroupUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindGroupUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IUpdateGroupUseCase;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
@Tag(name = "Groups", description = "CRUD")
@Slf4j
public class GroupRestController {

    private final IFindGroupUseCase findGroupUseCase;
    private final ICreateGroupUseCase createGroupUseCase;
    private final IUpdateGroupUseCase updateGroupUseCase;
    private final IDeleteGroupUseCase deleteGroupUseCase;



    @GetMapping("/company")
    public List<GroupResponse> getAllByCompanyId() {
        return findGroupUseCase.findAllByCompanyId()
                .stream()
                .map(GroupResponse::convertFromDomain)
                .toList();
    }


    @GetMapping("/user/{userId}")
    public List<GroupResponse> getAllByCompanyIdAndByUserId(@PathVariable Long userId) {
        return findGroupUseCase.findAllByCompanyIdAndByUserId(userId)
                .stream()
                .map(GroupResponse::convertFromDomain)
                .toList();
    }


    @GetMapping("/{id}")
    public GroupResponse getGroupById(@PathVariable Long id) {
        var group = findGroupUseCase.findById(id);
        return GroupResponse.convertFromDomain(group);
    }





    @PostMapping
    public GroupResponse createGroup(@RequestBody GroupCreateRequest groupCreateRequest) {
        var createdGroup = createGroupUseCase.execute(groupCreateRequest.convertToDomain());
        return GroupResponse.convertFromDomain(createdGroup);
    }




    @PutMapping
    public GroupResponse updateGroup(@RequestBody GroupUpdateRequest groupUpdateRequest) {
        var updatedGroup = updateGroupUseCase.execute(groupUpdateRequest.convertToDomain());
        return GroupResponse.convertFromDomain(updatedGroup);
    }



    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable Long id) {
        deleteGroupUseCase.execute(id);
    }
}
