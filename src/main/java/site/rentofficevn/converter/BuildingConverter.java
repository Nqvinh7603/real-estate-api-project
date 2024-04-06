package site.rentofficevn.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import site.rentofficevn.model.dto.BuildingDTO;
import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.DistrictRepository;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.repository.entity.DistrictEntity;
import site.rentofficevn.repository.entity.RentAreaEntity;
import site.rentofficevn.repository.impl.DistrictRepositoryImpl;
import site.rentofficevn.repository.impl.RentAreaRepositoryImpl;

@Component
public class BuildingConverter {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	DistrictRepository districtRepository;
	@Autowired
	RentAreaRepository rentAreaRepository;

	//Áp dụng cách thuần convert
	public BuildingSearchResponse convertFromEntitytoBuildingSearchResponse(BuildingEntity buildingEntity) {

		BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
		buildingSearchResponse.setName(buildingEntity.getName());
		buildingSearchResponse.setManagerName(buildingEntity.getManagerName());
		buildingSearchResponse.setManagerPhone(buildingEntity.getManagerPhone());
		buildingSearchResponse.setFloorArea(buildingEntity.getFloorArea());
		buildingSearchResponse.setRentCost(buildingEntity.getRentPrice().toString());
		buildingSearchResponse.setServiceFee(buildingEntity.getServiceFee());
		buildingSearchResponse.setBrokerageFee(buildingEntity.getBrokerageFee());

		// Xử lý District
		DistrictEntity district = districtRepository.findById(buildingEntity.getDistrictId());
		buildingSearchResponse
				.setAddress(buildingEntity.getStreet() + " - " + buildingEntity.getWard() + " - " + district.getName());

		// Xử lý rent area
		// Cach 1:Dùng Stream API
		List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
		String rentAreaString = rentAreaEntities.stream()
				.map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue())).collect(Collectors.joining(", "));
		buildingSearchResponse.setEmptyArea(rentAreaString);

		return buildingSearchResponse;

		// Cach 2: Dùng StringUtils
		/*List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
		String rentAreaString = StringUtils.join(
				rentAreaEntities.stream().map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue())).toArray(),
				", ");
		if (!rentAreaString.isEmpty()) {
			rentAreaString = StringUtils.removeEnd(rentAreaString, ", ");
		}
		buildingSearchResponse.setEmptyArea(rentAreaString);
		*/
	}

	//Áp dụng cách sử dụng ModelMapper để convert
	/*public BuildingSearchResponse convertFromEntitytoBuildingSearchResponse(BuildingEntity buildingEntity) {

		BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
		return buildingSearchResponse;
	}*/

	public BuildingDTO convertFromEntitytoDTO(BuildingEntity buildingEntity) {

		 BuildingDTO result = modelMapper.map(buildingEntity, BuildingDTO.class);
		return result;
	}

}
