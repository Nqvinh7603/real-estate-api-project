package site.rentofficevn.converter;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.DistrictRepository;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.repository.entity.DistrictEntity;
import site.rentofficevn.repository.entity.RentAreaEntity;


@Component
public class BuildingConverter {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private DistrictRepository districtRepository ;
	@Autowired
	private RentAreaRepository rentAreaRepository;

	//Áp dụng cách sử dụng ModelMapper để convert
	public BuildingSearchResponse convertFromEntitytoBuildingSearchResponse(BuildingEntity buildingEntity) {

		BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);

		//Xử lý District
		DistrictEntity districtEntity = districtRepository.findById(buildingEntity.getDistrictId());
		buildingSearchResponse.setAddress(buildingEntity.getStreet() + " - " + buildingEntity.getWard() + " - " + districtEntity.getName());

		//Xử lý rent area -> By Stream API
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
		String rentAreaString = rentAreaEntities.stream()
				.map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue())).collect(Collectors.joining(", "));
		buildingSearchResponse.setEmptyArea(rentAreaString);

		// Cach 2: Dùng StringUtils
		/*List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
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
