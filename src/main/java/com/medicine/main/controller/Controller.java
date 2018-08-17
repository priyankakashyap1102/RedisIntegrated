package com.medicine.main.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.medicine.main.entity.Medicine;
import com.medicine.main.entity.User;
import com.medicine.main.service.IMedicineService; 

@RestController 
@RequestMapping(value="/medicine")
public class Controller {

	@Autowired
	private IMedicineService getService;

	@PostMapping()
	public ResponseEntity<?> addData(@RequestBody Medicine sb, UriComponentsBuilder builder) throws Exception {
		String flag = getService.addData(sb);
		return null;
		
	}

	@GetMapping("")
	public ResponseEntity<?> getAllMedicines() {
		List<Medicine> list = getService.getAllMedicines();
		if(list.isEmpty() || list.size()==0)
		{
			return new ResponseEntity<String>("There is not any record", HttpStatus.OK);
		}
		return new ResponseEntity<List<Medicine>>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> GetMedicineById(@PathVariable("id") Integer id) {
		Medicine med=getService.getMedicineById(id);
		if(med!=null)
			return new ResponseEntity<Medicine>(med, HttpStatus.OK); 
		else
			return new ResponseEntity<String>("Id is not present", HttpStatus.OK); 

	}

	@PutMapping("")
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

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMedicine(@PathVariable("id") Integer id) {
		String flag=getService.deleteMedicine(id);
		return new ResponseEntity<String>(flag,HttpStatus.OK);	
	}
	
	@GetMapping("/users")
	 public @ResponseBody String getUsers() {
		    return "{\"users\":[{\"firstname\":\"Richard\", \"lastname\":\"Feynman\"}," +
		           "{\"firstname\":\"Marie\",\"lastname\":\"Curie\"}]}";
		  }
	
}
