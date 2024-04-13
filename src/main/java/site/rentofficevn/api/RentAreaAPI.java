package site.rentofficevn.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.rentofficevn.model.dto.RentAreaDTO;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.repository.entity.RentAreaEntity;

import java.util.List;

@RestController
@RequestMapping("/api/rent-area")
public class RentAreaAPI {
    @Autowired
    private BuildingRepository buildingRepository;
    @GetMapping
    public List<RentAreaDTO> getRentArea(@RequestParam(value = "buildingId", required = false) Long buildingId) {
        BuildingEntity buildingEntity = buildingRepository.findById(buildingId);
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
        for(RentAreaEntity rentAreaEntity : rentAreaEntities) {
            System.out.println(rentAreaEntity.getValue());
        }
        return  null;
    }

}
