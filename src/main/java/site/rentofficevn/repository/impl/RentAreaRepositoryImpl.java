package site.rentofficevn.repository.impl;

import java.util.List;

import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.entity.RentAreaEntity;

public class RentAreaRepositoryImpl extends JdbcRepositoryImpl<RentAreaEntity> implements RentAreaRepository {

	@Override
	public List<RentAreaEntity> findByBuildingId(Long id) {
		String sql = "select * from rentarea";
		sql += " where rentarea.buildingid = " + id;
		return findByCondition(sql);
	}

}