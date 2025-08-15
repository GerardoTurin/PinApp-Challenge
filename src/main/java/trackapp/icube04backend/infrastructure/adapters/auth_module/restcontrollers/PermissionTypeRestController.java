package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import trackapp.icube04backend.modules.auth_module.domain.models.PermissionType;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IFindAllPermissionTypesUseCase;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
@Slf4j
public class PermissionTypeRestController {

    private final IFindAllPermissionTypesUseCase findAllPermissionTypesUseCase;

    @GetMapping("/all")
    public List<PermissionType> getAll() {
        var permissions = findAllPermissionTypesUseCase.findAll();

        return permissions;
    }

    @GetMapping("/{roleId}")
    public List<PermissionType> findByRoleId(@PathVariable Long roleId){
        var permissions = findAllPermissionTypesUseCase.findByRoleId(roleId);

        return permissions;
    }

}