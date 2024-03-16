package site.rentofficevn.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.entity.BuildingEntity;
import site.rentofficevn.utils.StringUtils;


@Repository
public class BuildingRepositoryImpl extends JdbcRepositoryImpl<BuildingEntity> implements BuildingRepository {

    @Override
    public List<BuildingEntity> findBuilding(Map<String, String> buildingSearch, List<String> buildingSearchType) {
        StringBuilder finalQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();

        finalQuery.append(
                "SELECT b.id, b.name, b.street, b.ward, b.districtid, b.managername, b.managerphone, b.floorarea, b.rentprice, b.rentpriceDescription, b.servicefee, b.brokeragefee")
                .append("\nFrom building b");

        buildJoinQuery(buildingSearch, buildingSearchType, whereQuery, joinQuery);
        buildNormalQuery(buildingSearch, whereQuery);

        finalQuery.append(joinQuery)
                .append(" WHERE 1 = 1")
                .append(whereQuery)
                .append("\n group by b.id");
        return findByCondition(finalQuery.toString());
    }

    private void buildJoinQuery(Map<String, String> buildingSearch, List<String> buildingSearchType,
            StringBuilder whereQuery, StringBuilder joinQuery) {
        String rentAreaFrom = buildingSearch.get("rentAreaFrom");
        String rentAreaTo = buildingSearch.get("rentAreaTo");
        String staffId = buildingSearch.get("staffId");
        String districtCode = buildingSearch.get("districtCode");
        if (!StringUtils.isNullOrEmpty(rentAreaFrom) || !StringUtils.isNullOrEmpty(rentAreaTo)) {
            joinQuery.append(" INNER JOIN rentarea as ra ON ra.buildingid = b.id");
            if (!StringUtils.isNullOrEmpty(rentAreaFrom)) {
                whereQuery.append(" AND ra.value >= " + rentAreaFrom);
            }
            if (!StringUtils.isNullOrEmpty(rentAreaTo)) {
                whereQuery.append(" AND ra.value <= " + rentAreaTo);
            }
        }

        if (!StringUtils.isNullOrEmpty(staffId)) {
            joinQuery.append(
                    " INNER JOIN assignmentbuilding as ab ON ab.buildingid = b.id INNER JOIN user as u ON ab.staffid = u.id");
            whereQuery.append(" AND u.id = " + staffId);
        }
        if (buildingSearchType != null && !buildingSearchType.isEmpty()) {
            joinQuery.append(
                    " INNER JOIN buildingrenttype as br ON br.buildingid = b.id INNER JOIN renttype as r ON br.renttypeid = r.id");
            whereQuery.append(" AND (");
            for (int i = 0; i < buildingSearchType.size(); i++) {
                if (i > 0) {
                    whereQuery.append(" OR ");
                }
                whereQuery.append("r.code LIKE '%" + buildingSearchType.get(i) + "%'");
            }
            whereQuery.append(")");
        }

        if (!StringUtils.isNullOrEmpty(districtCode)) {
            joinQuery.append("\nINNER JOIN district d ON d.id = b.districtid");
            whereQuery.append(" AND d.code LIKE '%" + districtCode + "%'");
        }
    }

    private void buildNormalQuery(Map<String, String> buildingSearch, StringBuilder whereQuery) {
        String name =  buildingSearch.get("name");
        String street = buildingSearch.get("street");
        String ward = buildingSearch.get("ward");
        String floorArea = buildingSearch.get("floorArea");
        String numberOfBasement = buildingSearch.get("numberOfBasement");
        String direction = buildingSearch.get("direction");
        String level = buildingSearch.get("level");
        String managerName = buildingSearch.get("managerName");
        String managerPhone = buildingSearch.get("managerPhone");
        String rentPriceFrom = buildingSearch.get("rentPriceFrom");
        String rentPriceTo = buildingSearch.get("rentPriceTo");
        if (!StringUtils.isNullOrEmpty(name)) {
            whereQuery.append(" AND name LIKE '%" + name + "%'");
        }
        if (!StringUtils.isNullOrEmpty(street)) {
            whereQuery.append(" AND street LIKE '%" + street + "%'");
        }
        if (!StringUtils.isNullOrEmpty(ward)) {
            whereQuery.append(" AND ward LIKE '%" + ward + "%'");
        }
        if (!StringUtils.isNullOrEmpty(floorArea)) {
            whereQuery.append(" AND floorArea = " + floorArea);
        }
        if (!StringUtils.isNullOrEmpty(numberOfBasement)) {
            whereQuery.append(" AND numberofbasement = " + numberOfBasement);
        }
        if (!StringUtils.isNullOrEmpty(direction)) {
            whereQuery.append(" AND direction LIKE '%" + direction + "%'");
        }
        if (!StringUtils.isNullOrEmpty(level)) {
            whereQuery.append(" AND level LIKE '%" + level + "%'");
        }
        if (!StringUtils.isNullOrEmpty(managerName)) {
            whereQuery.append(" AND managername LIKE '%" + managerName + "%'");
        }
        if (!StringUtils.isNullOrEmpty(managerPhone)) {
            whereQuery.append(" AND managerphone LIKE '%" + managerPhone + "%'");
        }
        if (!StringUtils.isNullOrEmpty(rentPriceFrom)) {
            whereQuery.append(" AND b.rentPrice >= " + rentPriceFrom);
        }
        if (!StringUtils.isNullOrEmpty(rentPriceTo)) {
            whereQuery.append(" AND b.rentPrice <= " + rentPriceTo);
        }

    }

}
