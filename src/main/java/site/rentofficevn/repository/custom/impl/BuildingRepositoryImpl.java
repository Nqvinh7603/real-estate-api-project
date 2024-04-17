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
import site.rentofficevn.utils.NumberUtils;
import site.rentofficevn.utils.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BuildingRepositoryImpl	  implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BuildingEntity> findBuilding(Map<String, Object> buildingSearch, List<String> buildingTypes) {
        //b.id, b.name, b.street, b.ward, b.districtid, b.managername, b.managerphone, b.floorarea, b.rentprice, b.rentpriceDescription, b.servicefee, b.brokeragefee, b.numberofbasement
        StringBuilder finalQuery = new StringBuilder("SELECT b.id, b.name, b.street, b.ward, b.districtid, b.managername, b.managerphone, b.floorarea, b.rentprice, b.rentpriceDescription, b.servicefee, b.brokeragefee, b.numberofbasement from building b\n");
        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();
        finalQuery.append(buildSqlJoining(buildingSearch, buildingTypes, joinQuery))
                .append(SystemConstant.WHERE_ONE_EQUAL_ONE)
                .append(buildSqlCommon(buildingSearch, buildingTypes, whereQuery))
                .append(buildSqlSpecial(buildingSearch, buildingTypes, whereQuery))
                .append(" GROUP BY b.id");
        return entityManager.createNativeQuery(finalQuery.toString(), BuildingEntity.class).getResultList();
        
    }
    private StringBuilder buildSqlJoining(Map<String, Object> buildingSearch, List<String> buildingTypes, StringBuilder sqlJoining){
        Long staffId = MapUtils.getObject(buildingSearch, "staffid", Long.class);
        String districtCode = MapUtils.getObject(buildingSearch, "districtcode", String.class);

        if(!CheckInputSearchUtils.isNullLong(staffId)){
            sqlJoining.append(" INNER JOIN assignmentbuilding as ab ON ab.buildingid = b.id INNER JOIN user as u ON ab.staffid = u.id");
        }
        if(!CheckInputSearchUtils.isEmptyOrNullString(districtCode)){
            sqlJoining.append(" INNER JOIN district d ON d.id = b.districtid");
        }
        if(buildingTypes != null && !buildingTypes.isEmpty()){
            sqlJoining.append(
                    " INNER JOIN buildingrenttype as br ON br.buildingid = b.id INNER JOIN renttype as r ON br.renttypeid = r.id");
        }
        return sqlJoining;
    }
    private StringBuilder buildSqlCommon(Map<String, Object> buildingSearch, List<String> buildingTypes, StringBuilder sqlCommon){
        for(Map.Entry<String, Object> item : buildingSearch.entrySet()){
            if(!item.getKey().equals("buildingTypes") && !item.getKey().startsWith("rentarea") && !item.getKey().equals("districtcode") && !item.getKey().equals("staffid") && !item.getKey().startsWith("rentprice")){
                String value = item.getValue().toString();
                if(NumberUtils.isInteger(value)) {
                    sqlCommon.append(" AND b.").append(item.getKey().toLowerCase()).append(" = ").append(Integer.parseInt(value));
                }else if(!StringUtils.isNullOrEmpty(value)){
                    sqlCommon.append(" AND b.").append(item.getKey().toLowerCase()).append(" LIKE '%").append(value).append("%'");
                }
            }
        }
        return sqlCommon;
    }
    private StringBuilder buildSqlSpecial(Map<String, Object> buildingSearch, List<String> buildingTypes, StringBuilder sqlSpecial){
        Long staffId = MapUtils.getObject(buildingSearch, "staffid", Long.class);
        if(!CheckInputSearchUtils.isNullLong(staffId)){
            sqlSpecial.append(" AND u.id = ").append(staffId);
        }

        String districtCode = MapUtils.getObject(buildingSearch, "districtcode", String.class);
        if(!CheckInputSearchUtils.isEmptyOrNullString(districtCode)){
            sqlSpecial.append(" AND d.code LIKE '%").append(districtCode).append("%'");
        }

        Integer rentPriceFrom = MapUtils.getObject(buildingSearch, "rentpricefrom", Integer.class);
        Integer rentPriceTo = MapUtils.getObject(buildingSearch, "rentpriceto", Integer.class);
        if (!CheckInputSearchUtils.isNullInteger(rentPriceTo) || !CheckInputSearchUtils.isNullInteger(rentPriceFrom)) {
            if(!CheckInputSearchUtils.isNullInteger(rentPriceFrom)){
                sqlSpecial.append(" AND b.rentprice >= ").append(rentPriceFrom);
            }
            if (!CheckInputSearchUtils.isNullInteger(rentPriceTo)) {
                sqlSpecial.append(" AND b.rentprice <= ").append(rentPriceTo);
            }

        }
        Integer rentAreaFrom = MapUtils.getObject(buildingSearch, "rentareafrom", Integer.class);
        Integer rentAreaTo = MapUtils.getObject(buildingSearch, "rentareato", Integer.class);
        if(!CheckInputSearchUtils.isNullInteger(rentAreaFrom) || !CheckInputSearchUtils.isNullInteger(rentAreaTo)){
            sqlSpecial.append(" EXISTS (SELECT * FROM rentarea ra WHERE ra.buildingid = b.id");
            if (!CheckInputSearchUtils.isNullInteger(rentAreaFrom)) {
                sqlSpecial.append(" AND ra.value >= ").append(rentAreaFrom);
            }
            if(!CheckInputSearchUtils.isNullInteger(rentAreaTo)){
                sqlSpecial.append(" AND ra.value <= ").append(rentAreaTo);
            }
            sqlSpecial.append(")");
        }

        if (buildingTypes != null && !buildingTypes.isEmpty()) {
            sqlSpecial.append(" AND (");
            sqlSpecial.append(buildingTypes.stream()
                    .map(type -> "r.code LIKE '%" + type + "%'")
                    .collect(Collectors.joining(" OR ")));
            sqlSpecial.append(")");
        }
        return sqlSpecial;
    }
}
