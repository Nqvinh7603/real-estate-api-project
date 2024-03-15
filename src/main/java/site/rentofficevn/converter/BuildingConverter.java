package site.rentofficevn.converter;

import java.util.List;

import site.rentofficevn.model.response.BuildingSearchResponse;
import site.rentofficevn.repository.DistrictRepository;
import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.repository.entity.DistrictEntity;
import site.rentofficevn.repository.entity.RentAreaEntity;
import site.rentofficevn.repository.impl.DistrictRepositoryImpl;
import site.rentofficevn.repository.impl.RentAreaRepositoryImpl;
public class BuildingConverter {
	
	private DistrictRepository districtRepository = new DistrictRepositoryImpl();
	private RentAreaRepository rentAreaRepository = new RentAreaRepositoryImpl();
	
	public BuildingSearchResponse convertFromEntitytoBuildingSearchResponse(BuildingEntity buildingEntity) {
		BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
		buildingSearchResponse.setName(buildingEntity.getName());
		buildingSearchResponse.setManagerName(buildingEntity.getManagerName());
		buildingSearchResponse.setManagerPhone(buildingEntity.getManagerPhone());
		buildingSearchResponse.setFloorArea(buildingEntity.getFloorArea());
		buildingSearchResponse.setRentCost(buildingEntity.getRentPrice().toString());
		buildingSearchResponse.setServiceFee(buildingEntity.getServiceFee());
		buildingSearchResponse.setBrokerageFee(buildingEntity.getBrokerageFee());
		
		//set Address
		DistrictEntity district = districtRepository.findById(buildingEntity.getDistrictId());
		buildingSearchResponse.setAddress(buildingEntity.getStreet() + " - " + buildingEntity.getWard() + " - " + district.getName());
		
		//set EmptyArea
		List<RentAreaEntity> rentAreaEnities = rentAreaRepository.findByBuildingId(buildingEntity.getId());
	      StringBuilder rentArea = new StringBuilder();
	      for (RentAreaEntity rentAreaEnity : rentAreaEnities) {
	    	  
	    	  rentArea.append(String.valueOf(rentAreaEnity.getValue()));
	    	    rentArea.append(", ");
	      }
	      if (rentArea.length() > 0) {
	    	    rentArea.setLength(rentArea.length() - 2); 
	    	}
	      buildingSearchResponse.setEmptyArea(rentArea.toString());
		return buildingSearchResponse;
	}
}
