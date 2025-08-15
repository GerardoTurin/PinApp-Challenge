package trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.requests.CompanyUpdateRequest;
import trackapp.icube04backend.infrastructure.adapters.company_module.restcontrollers.dtos.requests.CustomCompanyCreateRequest;
import trackapp.icube04backend.infrastructure.utils.ConvertToByte;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.ICreateCompanyUseCase;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.IDeleteCompanyUseCase;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.IFindAllCompaniesUseCase;
import trackapp.icube04backend.modules.company_module.domain.ports.usecases.IUpdateCompanyUseCase;


import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
@Slf4j
public class CompanyRestController {

    private final IFindAllCompaniesUseCase findAllCompaniesUseCase;
    private final ICreateCompanyUseCase createCompanyUseCase;
    private final IUpdateCompanyUseCase updateCompanyUseCase;
    private final IDeleteCompanyUseCase deleteCompanyUseCase;


    @GetMapping("/all")
    public List<Company> getAllByCompanyId() {
        var companies = findAllCompaniesUseCase.findAll();
        return companies;
    }

    @PostMapping("/create")
    public Company create(@RequestPart(value = "company") @Valid CustomCompanyCreateRequest request, @RequestPart(value = "img") MultipartFile img) {
        var created = createCompanyUseCase.create(request, (!img.isEmpty() ? ConvertToByte.execute(img) : null));
        return created;
    }

    @PutMapping("/update")
    public Company update(@RequestPart(value = "company") @Valid CompanyUpdateRequest request, @RequestPart(value = "img") MultipartFile img){
        var updated = updateCompanyUseCase.update(request.convertToDomain(), (!img.isEmpty() ? ConvertToByte.execute(img) : null));
        return updated;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        deleteCompanyUseCase.deleteById(id);
    }



}
