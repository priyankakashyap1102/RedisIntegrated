package com.medicine.main.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicine.main.dao.MedicineDao;
import com.medicine.main.entity.Medicine;
import com.medicine.main.entity.User;

@Service
public class MedicineDaoService implements IMedicineService {

	@Autowired
	private MedicineDao d;


	@Override
	public String addData(Medicine sb) throws Exception  {

		
		Date eDate=sb.getExpiryDate();
		Date mDate=sb.getManuDate();
		boolean flag=validateDate(mDate);
		boolean flag2=validateDate(eDate);

		System.out.println(flag);
		System.out.println(flag2);
		if(flag && flag2)
		{
			if((mDate.compareTo(eDate)<0))
			{
				d.addData(sb);
				return "Medidcine Record Saved Successfully!";
			}
			return "Manufacturing Date should be less than Expiry Date!";
		}
		else
			return"Invalid date format!";


	}

	@Override
	public List<Medicine> getAllMedicines() {

		return d.getAllMedicines();
	}
	@Override
	public Medicine getMedicineById(int mId) {
		Medicine obj=null;
		try
		{
			obj=d.getMedicineById(mId);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return obj;
	}
	@Override
	public String updateMedicine(Medicine med) throws Exception {


		Date eDate=med.getExpiryDate();
		Date mDate=med.getManuDate();
		boolean flag=validateDate(mDate);
		boolean flag2=validateDate(eDate);
		System.out.println(flag);
		System.out.println(flag2);
		if(d.MedicineExists(med.getmId()))
		{
			if(flag && flag2)
			{
				if(mDate.compareTo(eDate)<0)
				{
					d.updateMedicine(med);
					return "Medicine Record Updated Successfully!";
				}
				else
				{
					return "Manufacturing Date should be less than Expiry Date!";
				}
			}
			else
				return "Invalid Date Format!";
		}
		else
			return "Medicine Id not present!";
	}



	@Override
	public String deleteMedicine(int mId) {	
		if(d.MedicineExists(mId))
		{
			d.deleteMedicine(mId);
			return "Medicine Record Deleted";
		}
		return "Medicine Id not present";

	}
	public boolean validateDate(Date d) throws Exception
	{

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String strDate = dateFormat.format(d);
		Pattern dateFrmtPtrn = Pattern.compile("(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-((19|20)\\d\\d)");
		Matcher mtch = dateFrmtPtrn.matcher(strDate);
		if(mtch.matches())
		{
			if(strDate.equals(""))
			{
				throw new Exception("Enter Date");
			}
			return true;
		}

		return false;
	}

	@Override
	public void addUserData(User u) {
		d.addUserData(u);
		
	}

}
