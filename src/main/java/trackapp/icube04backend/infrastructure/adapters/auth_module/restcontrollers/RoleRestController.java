package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests.CustomRoleCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests.CustomRoleUpdateRequest;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses.CustomRoleResponse;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses.CustomRoleResponse2;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses.RoleResponse;

import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleRestController {

    private final ICreateRoleUseCase createRoleUseCase;
    private final IGetAllRolesUseCase getAllRolesUseCase;
    private final IGetRoleByIdUseCase getRoleByIdUseCase;
    private final IUpdateRoleUseCase updateRoleUseCase;
    private final IDeleteRoleByIdUseCase deleteRoleByIdUseCase;

    @PostMapping("/create")
    public RoleResponse create(@RequestBody CustomRoleCreateRequest request){
        var createdRole = createRoleUseCase.create(request);
        return RoleResponse.convertFromDomain(createdRole);
    }

    @PutMapping("")
    public RoleResponse update(@RequestBody CustomRoleUpdateRequest request){
        var role = updateRoleUseCase.update(request);

        return RoleResponse.convertFromDomain(role);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        deleteRoleByIdUseCase.delete(id);
    }

    @GetMapping("/all")
    public List<CustomRoleResponse> getAllByCompanyId(){
     return getAllRolesUseCase.getAllByCompanyId().stream().map(CustomRoleResponse::convertFromDomain).toList();
    }

    @GetMapping("/{id}")
    public CustomRoleResponse2 getById(@PathVariable Long id){
        return getRoleByIdUseCase.getRoleById(id);
    }



}
