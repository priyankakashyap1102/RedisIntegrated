package com.medicine.main.dao;

import java.util.List;

import com.medicine.main.entity.Medicine;
import com.medicine.main.entity.User;


public interface IMedicineDao {


	List<Medicine> getAllMedicines();
	void addData(Medicine sb);
	void addUserData(User u);
	void updateMedicine(Medicine medicine);
	Medicine getMedicineById(int mId);
	void deleteMedicine(int mId);
	boolean MedicineExists(int mId);
    User findByUsername(String username);

}
