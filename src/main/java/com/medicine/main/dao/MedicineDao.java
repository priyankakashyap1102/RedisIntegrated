package com.medicine.main.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.main.entity.Medicine;
import com.medicine.main.entity.User;


@Transactional
@Repository
public class MedicineDao implements IMedicineDao {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public void addData(Medicine sb) {
		entityManager.persist(sb);

	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Medicine> getAllMedicines() {

		String hql="from Medicine";
		return (List<Medicine>) entityManager.createQuery(hql).getResultList();
	}
	@Override
	public void updateMedicine(Medicine medicine) {

		Medicine med=getMedicineById(medicine.getmId());

		med.setMrp(medicine.getMrp());
		med.setMedname(medicine.getMedname());
		med.setQuantity(medicine.getQuantity());
		med.setManuDate(medicine.getManuDate());
		med.setExpiryDate(medicine.getExpiryDate());
		entityManager.flush();


	}
	@Override
	public Medicine getMedicineById(int mId) {
		return  entityManager.find(Medicine.class,mId);
	}
	@Override
	public void deleteMedicine(int mId) {
		entityManager.remove(getMedicineById(mId));

	}
	@Override
	public boolean MedicineExists(int mId) {
		String hql = "FROM Medicine WHERE mId=?";
		int count = entityManager.createQuery(hql).setParameter(0, mId).getResultList().size();
		System.out.println("c"+count);
		return count > 0 ? true : false;
	}
	@Override
	public void addUserData(User u) {
		entityManager.persist(u);
		
	}
	@Override
	public User findByUsername(String username) {
		String hql = "FROM User WHERE username='"+username+"'";
		/*Query c=(Query) entityManager.createQuery(hql).setParameter(0, username);*/
		/*return entityManager.find(User.class, username);*/
		return (User) entityManager.createQuery(hql);
		
	}


}
