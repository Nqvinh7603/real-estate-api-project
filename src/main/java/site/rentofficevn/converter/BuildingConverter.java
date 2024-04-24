package site.rentofficevn.converter;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import site.rentofficevn.enums.DistrictsEnum;
import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.repository.entity.RentAreaEntity;


@Component
public class BuildingConverter {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	public BuildingSearchResponse convertFromEntitytoBuildingSearchResponse(BuildingEntity buildingEntity) {

		BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);

		//Xử lý District
		DistrictsEnum district = DistrictsEnum.valueOf(buildingEntity.getDistrictCode());
		buildingSearchResponse.setAddress(buildingEntity.getStreet() + " - " + buildingEntity.getWard() + " - " + district.getDistrictValue() );

		//Xử lý rent area -> By Stream API
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
		String rentAreaString = rentAreaEntities.stream()
				.map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue())).collect(Collectors.joining(", "));
		buildingSearchResponse.setEmptyArea(rentAreaString);

		/*Cach 2: Dùng StringUtils
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
		String rentAreaString = StringUtils.join(
				rentAreaEntities.stream().map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue())).toArray(),
				", ");
		if (!rentAreaString.isEmpty()) {
			rentAreaString = StringUtils.removeEnd(rentAreaString, ", ");
		}
		buildingSearchResponse.setEmptyArea(rentAreaString);*/

		return buildingSearchResponse;
	}
}
