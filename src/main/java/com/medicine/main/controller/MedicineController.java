package com.medicine.main.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.medicine.main.entity.Medicine;

import com.medicine.main.service.IMedicineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses; 

@RestController 
@RequestMapping(value="/medicine")
@ApiResponses(
		value = {
				@ApiResponse(code = 200, message = "Status OK"),
				@ApiResponse(code = 400, message = "Bad Request"),
				@ApiResponse(code = 403, message = "Forbidden"),
				@ApiResponse(code = 404, message = "Not found"),
				@ApiResponse(code = 500, message = "Internal Server Error")
				
		}
		)
@Api(value = "MedicineController", description = "SHOWS MEDICINE OPERATIONS")
public class MedicineController {

	@Autowired
	private IMedicineService getService;

	@PostMapping("/create")
	@ApiOperation(value="Add new Medicine")
	public ResponseEntity<?> addData(@RequestBody Medicine sb) throws Exception {
		String flag = getService.addData(sb);
		if(sb!=null)
		{
			if(!Pattern.matches("[a-zA-Z]*",sb.getMedname()))
			{
				throw new Exception("Please provide valid medicine name");
				
			}
			else if(sb.getMedname().equals("")||sb.getMedname()==null)
			{
				throw new Exception("Name cannot be null");
				
			}
			if(!Pattern.matches("\\d*",""+sb.getMrp()))
			{
				throw new Exception("Please provide valid MRP");
				
				
			}
			else if(sb.getMrp()==0)
			{
				throw new Exception("MRP cannot be null");
				
			}
			if(!Pattern.matches("\\d*",""+sb.getQuantity()))
			{
				throw new Exception("Please provide valid quantity");
				
			}
			else if(sb.getQuantity()==0)
			{
				throw new Exception("Quantity cannot be null");
			
			}
			return new ResponseEntity<String>(flag,HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>(flag,HttpStatus.CONFLICT);	
	}

	@GetMapping(value="/getAll", produces = "application/json")
	@ApiOperation(value="Get all medicine details")
	public ResponseEntity<?> getAllMedicines() {
		List<Medicine> list = getService.getAllMedicines();
		if(list.isEmpty() || list.size()==0)
		{
			return new ResponseEntity<String>("There is not any record", HttpStatus.OK);
		}
		return new ResponseEntity<List<Medicine>>(list, HttpStatus.OK);
	}

	@GetMapping(value="getId/{id}", produces = "application/json")
	@ApiOperation(value="Get medicine by id")
	public ResponseEntity<?> GetMedicineById(@PathVariable("id") Integer id) {
		Medicine med=getService.getMedicineById(id);
		if(med!=null && med.getmId()==id)
			return new ResponseEntity<Medicine>(med, HttpStatus.OK); 
		else
			return new ResponseEntity<String>("Id is not present", HttpStatus.CONFLICT); 

	}

	@PutMapping("/update")
	@ApiOperation(value="Update Medicine")
	public ResponseEntity<?> updateArticle(@RequestBody Medicine med) throws Exception {
		String flag=getService.updateMedicine(med);
		if(med!=null)
		{
			if(!Pattern.matches("[a-zA-Z]*",med.getMedname()))
			{
				throw new Exception("Please provide valid medicine name");
				
			}
			else if(med.getMedname().equals("")||med.getMedname()==null)
			{
				throw new Exception("Name cannot be null");
				
			}
			if(!Pattern.matches("\\d*",""+med.getMrp()))
			{
				throw new Exception("Please provide valid MRP");
				
				
			}
			else if(med.getMrp()==0)
			{
				throw new Exception("MRP cannot be null");
				
			}
			if(!Pattern.matches("\\d*",""+med.getQuantity()))
			{
				throw new Exception("Please provide valid quantity");
				
			}
			else if(med.getQuantity()==0)
			{
				throw new Exception("Quantity cannot be null");
				
			}
			return new ResponseEntity<String>(flag,HttpStatus.OK);
		}
		else
			return new ResponseEntity<String>(flag,HttpStatus.OK);	
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value="Delete Medicine by ID")
	public ResponseEntity<String> deleteMedicine(@PathVariable("id") Integer id) {
		String flag=getService.deleteMedicine(id);
		return new ResponseEntity<String>(flag,HttpStatus.OK);	
	}
	
	@GetMapping("/users")
	@ApiOperation(value="Sample endpoint")
	 public @ResponseBody String getUsers() {
		    return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}]";
		  }
	
}
