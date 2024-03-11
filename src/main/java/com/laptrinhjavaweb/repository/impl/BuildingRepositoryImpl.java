package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.repository.enity.BuildingEntity;


@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	private String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	private String USER = "root";
	private String PASS = "Nqv@762003";

	@Override
	public List<BuildingEntity> findAll() {
		List<BuildingEntity> results = new ArrayList<>();
		String query = "SELECT * FROM building";
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);) {
			while (rs.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setName(rs.getString("name"));
				buildingEntity.setStreet(rs.getString("street"));
				buildingEntity.setWard(rs.getString("ward"));
				buildingEntity.setDistrictId(rs.getLong("districtid"));
				results.add(buildingEntity);
			}
			return results;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
