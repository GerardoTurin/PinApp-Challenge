package trackapp.icube04backend.modules.order_module.application.usecases;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import trackapp.icube04backend.infrastructure.adapters.order_module.restcontrollers.dtos.requests.CustomLocationUpdateRequest;
import trackapp.icube04backend.modules.configuration_module.domain.models.ConfigDetail;
import trackapp.icube04backend.modules.configuration_module.domain.ports.repositories.IConfigDetailRepository;
import trackapp.icube04backend.modules.order_module.domain.models.Location;
import trackapp.icube04backend.modules.order_module.domain.ports.repositories.ILocationRepository;
import trackapp.icube04backend.modules.order_module.domain.ports.usecases.IUpdateLocationUseCase;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class UpdateLocationUseCase implements IUpdateLocationUseCase {

    private final ILocationRepository locationRepository;
    private final IConfigDetailRepository configDetailRepository;

    @Override
    public Location update(CustomLocationUpdateRequest request) {
        var old = locationRepository.findByid(request.id());

        var coords = configDetailRepository.findById(request.gpsCoordinatesTypeId());
        var category = configDetailRepository.findById(request.categoryId());
        var status = configDetailRepository.findById(request.statusId());

        var locationtypes = new ArrayList<ConfigDetail>();

        request.locationTypes().forEach( lt -> {
            locationtypes.add(configDetailRepository.findById(lt));
        });

        old.setName(request.name());
        old.setAddress(request.address());
        old.setGpsCoordinates(request.gpsCoordinates());
        old.setCoordinatesType(coords);
        old.setCategory(category);
        old.setStatus(status);
        old.setLocationTypes(locationtypes);

        return locationRepository.save(old);
    }
}
