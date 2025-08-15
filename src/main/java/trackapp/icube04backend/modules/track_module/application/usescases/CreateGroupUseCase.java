package trackapp.icube04backend.modules.track_module.application.usescases;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.common.exceptions.BadRequestExceptionService;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.company_module.domain.models.Company;
import trackapp.icube04backend.modules.track_module.domain.models.Group;
import trackapp.icube04backend.modules.track_module.domain.models.UserGroup;
import trackapp.icube04backend.modules.track_module.domain.ports.repository.IGroupRepository;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateGroupUseCase;
import trackapp.icube04backend.modules.track_module.domain.ports.usescases.ICreateUserGroupUseCase;

@Service
@RequiredArgsConstructor
public class CreateGroupUseCase implements ICreateGroupUseCase {

    private final IGroupRepository groupRepository;
    private final ISessionService sessionService;
    private final ICreateUserGroupUseCase createUserGroupUseCase; // Inyecta el use case de UserGroup

    @Override
    public Group execute(Group group) {
        var companyId = sessionService.getCompanyId();

        if (group.getName() == null || group.getName().trim().isEmpty()) {
            throw new BadRequestExceptionService("El nombre del grupo no puede estar vacío");
        }

        if (group.getCreatedUser() == null || group.getCreatedUser().getId() == null) {
            throw new BadRequestExceptionService("Debe asociar un usuario al grupo");
        }

        // Se crea la instancia de Company usando el id obtenido
        Company company = new Company();
        company.setId(companyId);

        group.setCompany(company);

        //return groupRepository.save(group);

        Group createdGroup = groupRepository.save(group);

        // Se crea el registro en UserGroup para el usuario creador y el grupo creado
        UserGroup userGroup = UserGroup.builder()
                .user(createdGroup.getCreatedUser())
                .group(createdGroup)
                .createdUser(createdGroup.getCreatedUser())
                //().createdAt(LocalDateTime.now())
                .build();
        createUserGroupUseCase.execute(userGroup);

        return createdGroup;
    }
}
