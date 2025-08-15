package trackapp.icube04backend.modules.order_module.application.usecases;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomLocationCreateRequest;
import trackapp.icube04backend.infrastructure.utils.ISessionService;
import trackapp.icube04backend.modules.company_module.domain.ports.repositories.ICompanyRepository;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.order_module.domain.models.Location;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.ILocationRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.ICreateLocationUseCase;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CreateLocationUseCase implements ICreateLocationUseCase {

    private final ILocationRepository locationRepository;
    private final ISessionService sessionService;
    private final IConfigDetailRepository configDetailRepository;
    private final ICompanyRepository companyRepository;

    @Override
    public Location create(CustomLocationCreateRequest request) {
        var coords = configDetailRepository.findById(request.gpsCoordinatesTypeId());
        var category = configDetailRepository.findById(request.categoryId());
        var status = configDetailRepository.findById(request.statusId());
        var company = companyRepository.findById(sessionService.getCompanyId());


        var locationtypes = new ArrayList<ConfigDetail>();

        request.locationTypes().forEach( lt -> {
            locationtypes.add(configDetailRepository.findById(lt));
        });


        return locationRepository.save(Location.builder()
                        .name(request.name())
                        .address(request.address())
                        .gpsCoordinates(request.gpsCoordinates())
                        .coordinatesType(coords)
                        .category(category)
                        .status(status)
                        .company(company)
                        .locationTypes(locationtypes)
                .build());
    }
}
