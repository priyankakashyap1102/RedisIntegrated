package com.medicine.main.service;

import java.util.List;

import com.medicine.main.entity.Medicine;
import com.medicine.main.entity.User;

public interface IMedicineService {

	List<Medicine> getAllMedicines();
	String addData(Medicine sb)throws Exception;
	Medicine getMedicineById(int mId);
	 String updateMedicine(Medicine med)throws Exception;
	 String deleteMedicine(int mId);
	 void addUserData(User u);
	
}
