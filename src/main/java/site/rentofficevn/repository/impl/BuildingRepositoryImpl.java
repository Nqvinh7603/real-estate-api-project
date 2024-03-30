package site.rentofficevn.repository.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.utils.MapUtils;
import site.rentofficevn.utils.StringUtils;

@Repository
public class BuildingRepositoryImpl	 extends JdbcRepositoryImpl<BuildingEntity> implements BuildingRepository {

	@Override
	public List<BuildingEntity> findBuilding(Map<String, Object> buildingSearch, List<String> buildingTypes) {
		String name = MapUtils.getObject(buildingSearch, "name", String.class);
		System.out.println(name);
		Long staffId = MapUtils.getObject(buildingSearch, "staffid", Long.class);
		System.out.println(staffId);
		Integer numberOfBasement = MapUtils.getObject(buildingSearch, "numberofbasement", Integer.class);
		System.out.println(numberOfBasement);
		//String query = "select * from building " + SystemConstant.WHERE_ONE_EQUAL_ONE;
		return null;
	}
}
