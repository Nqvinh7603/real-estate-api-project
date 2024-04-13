package site.rentofficevn.repository.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import site.rentofficevn.repository.RentAreaRepository;
import site.rentofficevn.repository.entity.RentAreaEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RentAreaRepositoryImpl extends JdbcRepositoryImpl<RentAreaEntity> implements RentAreaRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<RentAreaEntity> findByBuildingId(Long id) {
		//JPQL
		String sql = "FROM RentAreaEntity r WHERE building.id = " + id +"";
		return entityManager.createQuery(sql, RentAreaEntity.class).getResultList();

	}
	//jdbc
	/*@Override
	public List<RentAreaEntity> findByBuildingId(Long id) {
		String sql = "select * from rentarea";
		sql += " where rentarea.buildingid = " + id;
		return findByCondition(sql);
	}*/
}