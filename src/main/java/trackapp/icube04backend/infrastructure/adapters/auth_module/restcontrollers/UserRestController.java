package trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests.UserCreateRequest;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.requests.UserUpdateRequest;
import trackapp.icube04backend.infrastructure.adapters.auth_module.restcontrollers.dtos.responses.UserResponse;
import trackapp.icube04backend.infrastructure.utils.ConvertToByte;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.ICreateUserUseCase;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IDeleteUserUseCase;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IFindAllUsersUseCase;
import trackapp.icube04backend.modules.auth_module.domain.ports.usecases.IUpdateUserUseCase;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserRestController {

    private final IFindAllUsersUseCase findAllUsersUseCase;
    private final ICreateUserUseCase createUserUseCase;
    private final IUpdateUserUseCase updateUserUseCase;
    private final IDeleteUserUseCase deleteUserUseCase;

    @GetMapping("/all")
    public List<UserResponse> getAllByCompanyId() {
        var users = findAllUsersUseCase.findAllByCompanyId();

        return users.stream().map(UserResponse::convertFromDomain).toList();
    }

    @PostMapping("/create")
    public UserResponse create(@RequestPart(value = "user") @Valid UserCreateRequest request, @RequestPart(value ="img")MultipartFile img){
        var user = createUserUseCase.create(request.convertToDomain(), (!img.isEmpty() ? ConvertToByte.execute(img) : null));

        return UserResponse.convertFromDomain(user);
    }

    @PutMapping("/update")
    public UserResponse update(@RequestPart(value = "user") @Valid UserUpdateRequest request, @RequestPart(value ="img")MultipartFile img){
        var user = updateUserUseCase.update(request.convertToDomain(), (!img.isEmpty() ? ConvertToByte.execute(img) : null));

        return UserResponse.convertFromDomain(user);
    }


    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        deleteUserUseCase.delete(id);
    }
}
