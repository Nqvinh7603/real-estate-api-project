package site.rentofficevn.repository.custom.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;
import site.rentofficevn.constant.SystemConstant;
import site.rentofficevn.repository.custom.BuildingRepositoryCustom;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.utils.CheckInputSearchUtils;
import site.rentofficevn.utils.MapUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BuildingRepositoryImpl	  implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuildingEntity> findBuilding(Map<String, Object> buildingSearch, List<String> buildingTypes) {
        //b.id, b.name, b.street, b.ward, b.districtid, b.managername, b.managerphone, b.floorarea, b.rentprice, b.rentpriceDescription, b.servicefee, b.brokeragefee, b.numberofbasement
        StringBuilder finalQuery = new StringBuilder("SELECT * from building b\n");
        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder(SystemConstant.WHERE_ONE_EQUAL_ONE);
        finalQuery.append(buildSqlJoin(buildingSearch, buildingTypes, joinQuery))
                .append(buildSqlWhereClause(buildingSearch, buildingTypes, whereQuery));
        if (buildingTypes != null && !buildingTypes.isEmpty()) {
            finalQuery.append(" GROUP BY b.id, br.id");
        } else {
            finalQuery.append(" GROUP BY b.id");
        }
        return entityManager.createNativeQuery(finalQuery.toString(), BuildingEntity.class).getResultList();
        
    }
    private StringBuilder buildSqlJoin(Map<String, Object> buildingSearch, List<String> buildingTypes, StringBuilder joinQuery
    ){
        Long staffId = MapUtils.getObject(buildingSearch, "staffid", Long.class);
        String districtCode = MapUtils.getObject(buildingSearch, "districtcode", String.class);
        Integer rentAreaFrom = MapUtils.getObject(buildingSearch, "rentareafrom", Integer.class);
        Integer rentAreaTo = MapUtils.getObject(buildingSearch, "rentareato", Integer.class);

        if(!CheckInputSearchUtils.isNullLong(staffId)){
            joinQuery.append(" INNER JOIN assignmentbuilding as ab ON ab.buildingid = b.id INNER JOIN user as u ON ab.staffid = u.id");
        }

        if(!CheckInputSearchUtils.isEmptyOrNullString(districtCode)){
            joinQuery.append(" INNER JOIN district d ON d.id = b.districtid");
        }
        if(buildingTypes != null && !buildingTypes.isEmpty()){
            joinQuery.append(
                    " INNER JOIN buildingrenttype as br ON br.buildingid = b.id INNER JOIN renttype as r ON br.renttypeid = r.id");
        }

        if(!CheckInputSearchUtils.isNullInteger(rentAreaFrom) || !CheckInputSearchUtils.isNullInteger(rentAreaTo)){
            joinQuery.append(" INNER JOIN rentarea as ra ON ra.buildingid = b.id");
        }
        return joinQuery;
    }
    private StringBuilder buildSqlWhereClause(Map<String,Object> buildingSearch, List<String> buildingTypes, StringBuilder whereQuery){
        String name = MapUtils.getObject(buildingSearch, "name", String.class);
        if(!CheckInputSearchUtils.isEmptyOrNullString(name)){
            whereQuery.append(" AND b.name LIKE '%").append(name).append("%'");
        }

        String street = MapUtils.getObject(buildingSearch, "street", String.class);
        if(!CheckInputSearchUtils.isEmptyOrNullString(street)){
            whereQuery.append(" AND b.street LIKE '%").append(street).append("%'");
        }

        String ward = MapUtils.getObject(buildingSearch, "ward", String.class);
        if (!CheckInputSearchUtils.isEmptyOrNullString(ward)) {
            whereQuery.append(" AND b.ward LIKE '%").append(ward).append("%'");
        }

        String districtCode = MapUtils.getObject(buildingSearch, "districtcode", String.class);
        if(!CheckInputSearchUtils.isEmptyOrNullString(districtCode)){
            whereQuery.append(" AND d.code LIKE '%").append(districtCode).append("%'");
        }

        Integer floorArea = MapUtils.getObject(buildingSearch, "floorarea", Integer.class);
        if(!CheckInputSearchUtils.isNullInteger(floorArea)){
            whereQuery.append(" AND b.floorarea = ").append(floorArea);
        }

        Integer numberOfBasement = MapUtils.getObject(buildingSearch, "numberofbasement", Integer.class);
        if(!CheckInputSearchUtils.isNullInteger(numberOfBasement)){
            whereQuery.append(" AND b.numberofbasement = ").append(numberOfBasement);
        }

        String direction = MapUtils.getObject(buildingSearch, "direction", String.class);
        if(!CheckInputSearchUtils.isEmptyOrNullString(direction)){
            whereQuery.append(" AND b.direction LIKE '%").append(direction).append("%'");
        }

        String level = MapUtils.getObject(buildingSearch, "level", String.class);
        if(!CheckInputSearchUtils.isEmptyOrNullString(level)){
            whereQuery.append(" AND b.level LIKE '%").append(level).append("%'");
        }

        String managerName = MapUtils.getObject(buildingSearch, "managename", String.class);
        if (!CheckInputSearchUtils.isEmptyOrNullString(managerName)) {
            whereQuery.append(" AND b.managername LIKE '%").append(managerName).append("%'");
        }

        String managerPhone = MapUtils.getObject(buildingSearch, "managerphone", String.class);
        if (!CheckInputSearchUtils.isEmptyOrNullString(managerPhone)) {
            whereQuery.append(" AND b.managerphone LIKE '%").append(managerPhone).append("%'");
        }

        Integer rentPriceFrom = MapUtils.getObject(buildingSearch, "rentpricefrom", Integer.class);
        if(!CheckInputSearchUtils.isNullInteger(rentPriceFrom)){
            whereQuery.append(" AND b.rentprice >= ").append(rentPriceFrom);
        }
        Integer rentPriceTo = MapUtils.getObject(buildingSearch, "rentpriceto", Integer.class);
        if(!CheckInputSearchUtils.isNullInteger(rentPriceTo)){
            whereQuery.append(" AND b.rentprice <= ").append(rentPriceTo);
        }
        Integer rentAreaFrom = MapUtils.getObject(buildingSearch, "rentareafrom", Integer.class);
        if(!CheckInputSearchUtils.isNullInteger(rentAreaFrom)){
            whereQuery.append(" AND ra.value >= ").append(rentAreaFrom);
        }
        Integer rentAreaTo = MapUtils.getObject(buildingSearch, "rentareato", Integer.class);
        if(!CheckInputSearchUtils.isNullInteger(rentAreaTo)){
            whereQuery.append(" AND ra.value <= ").append(rentAreaTo);
        }
        if (buildingTypes != null && !buildingTypes.isEmpty()) {
            whereQuery.append(" AND (");
            whereQuery.append(buildingTypes.stream()
                    .map(type -> "r.code LIKE '%" + type + "%'")
                    .collect(Collectors.joining(" OR ")));
            whereQuery.append(")");
        }
        return whereQuery;
    }
}
