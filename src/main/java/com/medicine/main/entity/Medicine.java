package com.medicine.main.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name="medicinedetails")
public class Medicine implements Serializable {

	private static final long serialVersionUID = 1L;
	

	public Medicine() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="mId")
	private int mId;  


	@Column(name="medname",nullable=false)
	@NotNull(message="{Please provide medicine name}")
	private String medname;
	
	
	@NotNull(message="Please provide MRP")
	@Column(name="mrp")
	private int mrp;
	
	@NotNull(message="Please provide Quantity")
	@Column(name="quantity",nullable=false)	
	private int quantity;
	
	@NotNull(message="Please provide ExpiryDate")
	@Column(name="expirydate",nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date expiryDate;
	
	@NotNull(message="Please provide ManuFacturer Date")
	@Column(name="manudate",nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date manuDate;

	public int getmId() {
		return mId;
	}

	public void setmId(int mId) {
		this.mId = mId;
	}

	public String getMedname() {
		return medname;
	}

	public void setMedname(String medname) {
		this.medname = medname;
	}

	public int getMrp() {
		return mrp;
	}

	public void setMrp(int mrp) {
		this.mrp = mrp;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getManuDate() {
		return manuDate;
	}

	public void setManuDate(Date manuDate) {
		this.manuDate = manuDate;
	}

	
	
}
