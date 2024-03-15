package site.rentofficevn.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import site.rentofficevn.repository.BuildingRepository;
import site.rentofficevn.repository.enity.BuildingEntity;
import site.rentofficevn.utils.StringUtils;


@Repository
public class BuildingRepositoryImpl extends JdbcRepositoryImpl<BuildingEntity> implements BuildingRepository {

    @Override
    public List<BuildingEntity> findBuilding(Map<String, Object> buildingSearch, List<String> buildingSearchType) {
        StringBuilder finalQuery = new StringBuilder();
        StringBuilder joinQuery = new StringBuilder();
        StringBuilder whereQuery = new StringBuilder();

        finalQuery.append(
                "SELECT b.id, b.name, b.street, b.ward, b.districtid, b.managername, b.managerphone, b.floorArea, b.rentPrice, b.rentPriceDescription, b.serviceFee, b.brokerageFee")
                .append("\nFrom building b");

        buildJoinQuery(buildingSearch, buildingSearchType, whereQuery, joinQuery);
        buildNormalQuery(buildingSearch, whereQuery);

        finalQuery.append(joinQuery)
                .append(" WHERE 1 = 1")
                .append(whereQuery)
                .append("\n group by b.id");
        return findByCondition(finalQuery.toString());
    }

    private void buildJoinQuery(Map<String, Object> buildingSearch, List<String> buildingSearchType,
            StringBuilder whereQuery, StringBuilder joinQuery) {
        String rentAreaFrom = (String) buildingSearch.get("rentAreaFrom");

        String rentAreaTo = (String) buildingSearch.get("rentAreaTo");
        String staffId = (String) buildingSearch.get("staffId");
        String districtCode = (String) buildingSearch.get("districtCode");
        if (rentAreaFrom != null || rentAreaTo != null) {
            joinQuery.append(" INNER JOIN rentarea as ra ON ra.buildingid = b.id");
            if (rentAreaFrom != null) {
                whereQuery.append(" AND ra.value >= " + rentAreaFrom);
            }
            if (rentAreaTo != null) {
                whereQuery.append(" AND ra.value <= " + rentAreaTo);
            }
        }

        if (staffId != null) {
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

    private void buildNormalQuery(Map<String, Object> buildingSearch, StringBuilder whereQuery) {
        String name = (String) buildingSearch.get("name");
        String street = (String) buildingSearch.get("street");
        String ward = (String) buildingSearch.get("ward");
        Integer floorArea = (Integer) buildingSearch.get("floorArea");
        Integer numberOfBasement = (Integer) buildingSearch.get("numberOfBasement");
        String direction = (String) buildingSearch.get("direction");
        String level = (String) buildingSearch.get("level");
        String managerName = (String) buildingSearch.get("managerName");
        String managerPhone = (String) buildingSearch.get("managerPhone");
        Long rentPriceFrom = (Long) buildingSearch.get("rentPriceFrom");
        Long rentPriceTo = (Long) buildingSearch.get("rentPriceTo");
        if (!StringUtils.isNullOrEmpty(name)) {
            whereQuery.append(" AND name LIKE '%" + name + "%'");
        }
        if (!StringUtils.isNullOrEmpty(street)) {
            whereQuery.append(" AND street LIKE '%" + street + "%'");
        }
        if (!StringUtils.isNullOrEmpty(ward)) {
            whereQuery.append(" AND ward LIKE '%" + ward + "%'");
        }
        if (floorArea != null) {
            whereQuery.append(" AND floorArea = " + floorArea);
        }
        if (numberOfBasement != null) {
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
        if (rentPriceFrom != null) {
            whereQuery.append(" AND b.rentPrice >= " + rentPriceFrom);
        }
        if (rentPriceTo != null) {
            whereQuery.append(" AND b.rentPrice <= " + rentPriceTo);
        }

    }

}
