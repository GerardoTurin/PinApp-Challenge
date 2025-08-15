package trackapp.icube04backend.modules.configuration_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigHeaderRepository;
import trackapp.icube04backend.modules.configuration_module.domain.ports.usecases.IGetConfigDetailByCodeUseCase;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetConfigDetailByCodeUseCase implements IGetConfigDetailByCodeUseCase {

    private final IConfigDetailRepository configDetailRepository;
    private final IConfigHeaderRepository configHeaderRepository;
    private final ISessionService sessionService;


    @Override
    public ConfigDetail findByCode(String code) {

        var companyId = sessionService.getCompanyId();

        if(configDetailRepository.findByCode(code).isSystemParameter()){
            return configDetailRepository.findByCode(code);
        }else{
            if(configDetailRepository.findByCode(code).getCompany().getId().equals(sessionService.getCompanyId())){
                return configDetailRepository.findByCode(code);
            }else{
                return null;
            }
        }
    }

    @Override
    public List<ConfigDetail> findByHeaderCode(String code) {
        var companyId = sessionService.getCompanyId();

        List<ConfigDetail> configDetails = configDetailRepository.findByHeaderCode(code);

        if (companyId == 1) {
            return configDetails.stream()
                    .filter(ConfigDetail::isSystemParameter).toList();
        } else {
            return configDetails.stream()
                    .filter(configDetail -> configDetail.getCompany().getId().equals(companyId) || configDetail.isSystemParameter()).toList();
        }
    }


}
