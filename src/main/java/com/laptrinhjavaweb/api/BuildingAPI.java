package com.laptrinhjavaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.customexception.FieldRequireException;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.ErrorResponseBean;
import com.laptrinhjavaweb.dto.request.AssignmentBuildingRequest;
import com.laptrinhjavaweb.dto.response.BuildingSearchResponse;

import com.laptrinhjavaweb.service.BuildingService;

@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	@Autowired private BuildingService buildingService;
	
	@GetMapping
	public List<BuildingSearchResponse> getBuilding(@RequestParam(value = "name", required = false) String name,
														@RequestParam(value = "numberofbasement", required = false) Integer numberOfBasement,
														@RequestParam(value= "types", required = false) List<String> types ){
		List<BuildingSearchResponse> results = buildingService.findAll();		
		return results; 
	}
	@GetMapping("/{buildingid}")
	public BuildingDTO getDetail(@PathVariable("buildingid") Long buildingId) {
		System.out.println(buildingId);
		return null;
	}

	/*@PostMapping
	public BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding) {
		 loi server nhung ngoai client van hoat dong
		try {
			System.out.println(10 / 0);
		} catch (Exception e) {
			System.out.println("10 sao chia het cho 0 ???");
			e.getMessage();
		}
		 System.out.println(10/0); demo http status code 500
		return newBuilding;
	}*/
	
	//custom error response
	@PostMapping
	public BuildingDTO createBuilding(@RequestBody BuildingDTO newBuilding) {
		
		/*try {
			//System.out.println(10/0);
			System.out.println(0/0);
			//success
			return newBuilding;
		} catch (Exception e) {
			//return detail error 
			ErrorResponseBean errorResponseBean = new ErrorResponseBean();
			errorResponseBean.setError(e.getMessage());
			List<String> details = new ArrayList<>();
			details.add("10 sao chia het cho 0?");
			errorResponseBean.setDetails(details);
			return errorResponseBean;
		}*/
		System.out.println(10/0);
		validateData(newBuilding);
		return newBuilding;
	}

	private void validateData(BuildingDTO newBuilding) {
	if(newBuilding.getName() == null || newBuilding.getName().equals("") || newBuilding.getNumberOfBasement() == null) {
		throw new  FieldRequireException("name or numberofbasement is required");
	}
	
}

	@PutMapping
	public BuildingDTO updateBuilding(@RequestBody BuildingDTO updateBuilding) {
		
		return updateBuilding;
	}

	@DeleteMapping
	public void deleteBuilding(@RequestBody Long[] ids) {
		System.out.println("Delete success");
	}
	
	@PostMapping("/assignment")
	public void assignmentBuilding(@RequestBody AssignmentBuildingRequest assignmentBuildingBean) {
		System.out.println(assignmentBuildingBean.getBuildingId());
	}

}
