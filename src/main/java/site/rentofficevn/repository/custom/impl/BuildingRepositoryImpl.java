package site.rentofficevn.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import site.rentofficevn.builder.BuildingSearchBuilder;
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
    public List<BuildingEntity> findBuilding(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder finalQuery = new StringBuilder(
                "SELECT b.* from building b\n");
        finalQuery.append(buildJoiningClause(buildingSearchBuilder))
                .append(SystemConstant.WHERE_ONE_EQUAL_ONE)
                .append(buildCommonClause(buildingSearchBuilder))
                .append(buildSpecialClause(buildingSearchBuilder))
                .append(" GROUP BY b.id");
        return entityManager.createNativeQuery(finalQuery.toString(), BuildingEntity.class).getResultList();
        
    }
    private String buildJoiningClause(BuildingSearchBuilder buildingSearchBuilder){
        StringBuilder sqlJoining = new StringBuilder();
        Long staffId = buildingSearchBuilder.getStaffID();
        String districtCode = buildingSearchBuilder.getDistrict();

        if(!CheckInputSearchUtils.isNullLong(staffId)){
            sqlJoining.append(" INNER JOIN assignmentbuilding as ab ON ab.buildingid = b.id INNER JOIN user as u ON ab.staffid = u.id");
        }
        if(!CheckInputSearchUtils.isEmptyOrNullString(districtCode)){
            sqlJoining.append(" INNER JOIN district d ON d.id = b.districtid");
        }
        if(buildingSearchBuilder.getTypes() != null && buildingSearchBuilder.getTypes().size() > 0 ){
            sqlJoining.append(
                    " INNER JOIN buildingrenttype as br ON br.buildingid = b.id INNER JOIN renttype as r ON br.renttypeid = r.id");
        }
        return sqlJoining.toString();
    }
    private String buildCommonClause(BuildingSearchBuilder buildingSearchBuilder){
        StringBuilder sqlCommon = new StringBuilder();
        try{
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                String fieldName = field.getName();
                if(!fieldName.equals("types")
                        && !fieldName.startsWith("rentArea")
                        && !fieldName.equals("district")
                        && !fieldName.equals("staffID")
                        && !fieldName.startsWith("rentPrice")){
                    Object fieldValue = field.get(buildingSearchBuilder);
                    if(fieldValue != null && fieldValue != "") {
                        if (fieldValue instanceof String) {
                            sqlCommon.append(" and LOWER(b." + fieldName + ") LIKE '%" + fieldValue.toString().toLowerCase() + "%'");
                        } else if (fieldValue instanceof Integer) {
                            sqlCommon.append(" and b." + fieldName + " = " + fieldValue);
                        }
                    }
                }
            }
        }catch(IllegalAccessException e){
            e.printStackTrace();
        }
        return sqlCommon.toString();
    }
    private String buildSpecialClause(BuildingSearchBuilder buildingSearchBuilder){
        StringBuilder sqlSpecial = new StringBuilder();
        Long staffId = buildingSearchBuilder.getStaffID();
        if(!CheckInputSearchUtils.isNullLong(staffId)){
            sqlSpecial.append(" AND u.id = ").append(staffId);
        }

        String districtCode = buildingSearchBuilder.getDistrict();
        if(!CheckInputSearchUtils.isEmptyOrNullString(districtCode)){
            sqlSpecial.append(" AND d.code LIKE '%").append(districtCode).append("%'");
        }

        Integer rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Integer rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (!CheckInputSearchUtils.isNullInteger(rentPriceTo) || !CheckInputSearchUtils.isNullInteger(rentPriceFrom)) {
            if(!CheckInputSearchUtils.isNullInteger(rentPriceFrom)){
                sqlSpecial.append(" AND b.rentprice >= ").append(rentPriceFrom);
            }
            if (!CheckInputSearchUtils.isNullInteger(rentPriceTo)) {
                sqlSpecial.append(" AND b.rentprice <= ").append(rentPriceTo);
            }

        }
        Integer rentAreaFrom = buildingSearchBuilder.getRentAreaFrom();
        Integer rentAreaTo = buildingSearchBuilder.getRentAreaTo();
        if(!CheckInputSearchUtils.isNullInteger(rentAreaFrom) || !CheckInputSearchUtils.isNullInteger(rentAreaTo)){
            sqlSpecial.append(" AND EXISTS (SELECT * FROM rentarea ra WHERE ra.buildingid = b.id");
            if (!CheckInputSearchUtils.isNullInteger(rentAreaFrom)) {
                sqlSpecial.append(" AND ra.value >= ").append(rentAreaFrom);
            }
            if(!CheckInputSearchUtils.isNullInteger(rentAreaTo)){
                sqlSpecial.append(" AND ra.value <= ").append(rentAreaTo);
            }
            sqlSpecial.append(")");
        }
        if (buildingSearchBuilder.getTypes() != null && buildingSearchBuilder.getTypes().size() > 0 ) {
            sqlSpecial.append(" AND (");
            sqlSpecial.append(buildingSearchBuilder.getTypes().stream()
                    .map(type -> "r.code LIKE '%" + type + "%'")
                    .collect(Collectors.joining(" OR ")));
            sqlSpecial.append(")");
        }
        return sqlSpecial.toString();
    }
}
