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
@Table(name="employee")
public class Medicine implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="empId")
	private int empId;  
	
	

	@Column(name="empname",nullable=false)
	@NotNull(message="{Please provide medicine name}")
	private String empname;
	
	
	@NotNull(message="Please provide MRP")
	@Column(name="extension")
	private int extension;
	
	@NotNull(message="Please provide Quantity")
	@Column(name="deptid",nullable=false)	
	private int deptid;
	
	@NotNull(message="Please provide ExpiryDate")
	@Column(name="joiningdate",nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date joiningdate;
	
	@NotNull(message="Please provide ManuFacturer Date")
	@Column(name="dateofbirth",nullable=false)
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date dateofbirth;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public int getExtension() {
		return extension;
	}

	public void setExtension(int extension) {
		this.extension = extension;
	}

	public int getDeptid() {
		return deptid;
	}

	public void setDeptid(int deptid) {
		this.deptid = deptid;
	}

	public Date getJoiningdate() {
		return joiningdate;
	}

	public void setJoiningdate(Date joiningdate) {
		this.joiningdate = joiningdate;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	
    

	
}
