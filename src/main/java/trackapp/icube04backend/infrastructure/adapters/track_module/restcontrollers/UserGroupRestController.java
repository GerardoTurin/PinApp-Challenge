package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.UserGroupCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.UserGroupResponse;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.UserGroupUpdateRequest;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateUserGroupUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IDeleteUserGroupUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindUserGroupUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IUpdateUserGroupUseCase;

import java.util.List;

@RestController
@RequestMapping("/user-group")
@RequiredArgsConstructor
@Tag(name = "UserGroup", description = "CRUD")
@Slf4j
public class UserGroupRestController {

    private final IFindUserGroupUseCase findUserGroupUseCase;
    private final ICreateUserGroupUseCase createUserGroupUseCase;
    private final IUpdateUserGroupUseCase updateUserGroupUseCase;
    private final IDeleteUserGroupUseCase deleteUserGroupUseCase;



    @GetMapping("/user/{userId}")
    public List<UserGroupResponse> getAllByCompanyIdAndByUserId(@PathVariable Long userId) {
        return findUserGroupUseCase.findAllByCompanyIdAndByUserId(userId)
                .stream()
                .map(UserGroupResponse::convertFromDomain)
                .toList();
    }


    @GetMapping("/{id}")
    public UserGroupResponse getUserGroupById(@PathVariable Long id) {
        var userGroup = findUserGroupUseCase.findById(id);
        return UserGroupResponse.convertFromDomain(userGroup);
    }



    @PostMapping
    public UserGroupResponse createUserGroup(@RequestBody UserGroupCreateRequest userGroupCreateRequest) {
        var createdUserGroup = createUserGroupUseCase.execute(userGroupCreateRequest.convertToDomain());
        return UserGroupResponse.convertFromDomain(createdUserGroup);
    }



    @PutMapping
    public UserGroupResponse updateUserGroup(@RequestBody UserGroupUpdateRequest userGroupUpdateRequest) {
        var updatedUserGroup = updateUserGroupUseCase.execute(userGroupUpdateRequest.convertToDomain());
        return UserGroupResponse.convertFromDomain(updatedUserGroup);
    }


    @DeleteMapping("{id}")
    public void deleteUserGroup(@PathVariable Long id) {
        deleteUserGroupUseCase.execute(id);
    }

}
