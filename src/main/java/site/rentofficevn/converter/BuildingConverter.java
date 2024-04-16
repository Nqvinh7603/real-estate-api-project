package site.rentofficevn.converter;

import org.springframework.stereotype.Component;
import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.entity.BuildingEntity;

@Component
public class BuildingConverter {

	/*private DistrictRepository districtRepository = new DistrictRepositoryImpl();
	private RentAreaRepository rentAreaRepository = new RentAreaRepositoryImpl();
*/
	public BuildingSearchResponse convertFromEntitytoBuildingSearchResponse(BuildingEntity buildingEntity) {

		BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
		buildingSearchResponse.setName(buildingEntity.getName());
		buildingSearchResponse.setNumberOfBasement(buildingEntity.getNumberOfBasement());
		/*buildingSearchResponse.setManagerName(buildingEntity.getManagerName());
		buildingSearchResponse.setManagerPhone(buildingEntity.getManagerPhone());
		buildingSearchResponse.setFloorArea(buildingEntity.getFloorArea());
		buildingSearchResponse.setRentCost(buildingEntity.getRentPrice().toString());
		buildingSearchResponse.setServiceFee(buildingEntity.getServiceFee());
		buildingSearchResponse.setBrokerageFee(buildingEntity.getBrokerageFee());*/

		// Xử lý District
		/*DistrictEntity district = districtRepository.findById(buildingEntity.getDistrictId());
		buildingSearchResponse
				.setAddress(buildingEntity.getStreet() + " - " + buildingEntity.getWard() + " - " + district.getName());
*/
		// Xử lý rent area
		// Cach 1:Dùng Stream API
		/*List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
		String rentAreaString = rentAreaEntities.stream()
				.map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue())).collect(Collectors.joining(", "));
		buildingSearchResponse.setEmptyArea(rentAreaString);*/

		// Cach2: Dùng StringUtils
		/*List<RentAreaEntity> rentAreaEntities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
		String rentAreaString = StringUtils.join(
				rentAreaEntities.stream().map(rentAreaEntity -> String.valueOf(rentAreaEntity.getValue())).toArray(),
				", ");
		if (!rentAreaString.isEmpty()) {
			rentAreaString = StringUtils.removeEnd(rentAreaString, ", ");
		}
		buildingSearchResponse.setEmptyArea(rentAreaString);
*/
		return buildingSearchResponse;
	}
}
