package trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import trackapp.icube04backend.infrastructure.adapters.track_module.restcontrollers.dtos.UsersCompaniesResponse;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.IFindUserCompaniesUseCase;

import java.util.List;

@RestController
@RequestMapping("/users-companies")
@RequiredArgsConstructor
@Tag(name = "UsersCompanies", description = "CRUD")
@Slf4j
public class UsersCompaniesRestController {

    private final IFindUserCompaniesUseCase findUserCompaniesUseCase;



    @GetMapping("/user/{userId}")
    public List<UsersCompaniesResponse> getAllByUserId(@PathVariable Long userId) {
        return findUserCompaniesUseCase.findAllByUserId(userId)
                .stream()
                .map(UsersCompaniesResponse::convertFromDomain)
                .toList();
    }



}
