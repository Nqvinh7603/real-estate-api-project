package site.rentofficevn.repository.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.utils.CheckInputSearchUtils;
import site.rentofficevn.utils.MapUtils;

@Repository
public class BuildingRepositoryImpl	 extends JdbcRepositoryImpl<BuildingEntity> implements BuildingRepository {

	@Override
	public List<BuildingEntity> findBuilding(Map<String, Object> buildingSearch, List<String> buildingTypes) {
		StringBuilder finalQuery = new StringBuilder();
		StringBuilder joinQuery = new StringBuilder();
		StringBuilder whereQuery = new StringBuilder();
		finalQuery.append(
						"SELECT b.id, b.name, b.street, b.ward, b.districtid, b.managername, b.managerphone, b.floorarea, b.rentprice, b.rentpriceDescription, b.servicefee, b.brokeragefee")
				.append("\nFrom building b").append(buildSqlJoin(buildingSearch, buildingTypes, joinQuery)).append(SystemConstant.WHERE_ONE_EQUAL_ONE);

		return null;
	}
	private StringBuilder buildSqlJoin(Map<String, Object> buildingSearch, List<String> buildingTypes, StringBuilder joinQuery
	){
		Long staffId = MapUtils.getObject(buildingSearch, "staffId", Long.class);
		String districtCode = MapUtils.getObject(buildingSearch, "districtCode", String.class);
		if(!CheckInputSearchUtils.numIsNullLong(staffId)){
			joinQuery.append(" INNER JOIN assignmentbuilding as ab ON ab.buildingid = b.id INNER JOIN user as u ON ab.staffid = u.id ");
		}
		if(!CheckInputSearchUtils.strIsNullOrEmpty(districtCode)){
			joinQuery.append(" INNER JOIN district d ON d.id = b.districtid");
		}
		if(buildingTypes != null && !buildingTypes.isEmpty()){
			joinQuery.append(
					" INNER JOIN buildingrenttype as br ON br.buildingid = b.id INNER JOIN renttype as r ON br.renttypeid = r.id ");
		}
		return joinQuery;
	}
	private StringBuilder buildSqlWhereClause(Map<String,Object> buildingSearch, List<String> buildingTypes, StringBuilder whereQuery){
		String name = MapUtils.getObject(buildingSearch, "name", String.class);
		if(!CheckInputSearchUtils.strIsNullOrEmpty(name)){
			whereQuery.append(" AND b.name LIKE '%").append(name).append("%'");
		}

		String street = MapUtils.getObject(buildingSearch, "street", String.class);
		if(!CheckInputSearchUtils.strIsNullOrEmpty(street)){
			whereQuery.append(" AND b.street LIKE '%").append(street).append("%'");
		}

		String ward = MapUtils.getObject(buildingSearch, "ward", String.class);
		if (!CheckInputSearchUtils.strIsNullOrEmpty(ward)) {
			whereQuery.append(" AND b.ward LIKE '%").append(ward).append("%'");
		}

		String districtCode = MapUtils.getObject(buildingSearch, "districtcode", String.class);
		if(!CheckInputSearchUtils.strIsNullOrEmpty(districtCode)){
			whereQuery.append(" AND d.code LIKE '%").append(districtCode).append("%'");
		}

		Integer floorArea = MapUtils.getObject(buildingSearch, "floorarea", Integer.class);
		if(!CheckInputSearchUtils.numIsNullInt(floorArea)){
			whereQuery.append(" AND b.floorarea = ").append(floorArea);
		}

		Integer numberOfBasement = MapUtils.getObject(buildingSearch, "numberofbasement", Integer.class);
		if(!CheckInputSearchUtils.numIsNullInt(numberOfBasement)){
			whereQuery.append(" AND b.numberofbasement = ").append(numberOfBasement);
		}

		String direction = MapUtils.getObject(buildingSearch, "direction", String.class);
		if(!CheckInputSearchUtils.strIsNullOrEmpty(direction)){
			whereQuery.append(" AND b.direction LIKE '%").append(direction).append("%'");
		}

		String level = MapUtils.getObject(buildingSearch, "level", String.class);
		if(!CheckInputSearchUtils.strIsNullOrEmpty(level)){
			whereQuery.append(" AND b.level LIKE '%").append(level).append("%'");
		}

		String managerName = MapUtils.getObject(buildingSearch, "managename", String.class);
		if (!CheckInputSearchUtils.strIsNullOrEmpty(managerName)) {
			whereQuery.append(" AND b.managername LIKE '%").append(managerName).append("%'");
		}

		String managerPhone = MapUtils.getObject(buildingSearch, "managerphone", String.class);
		if (!CheckInputSearchUtils.strIsNullOrEmpty(managerPhone)) {
			whereQuery.append(" AND b.managerphone LIKE '%").append(managerPhone).append("%'");
		}


		return whereQuery;
	}
}
