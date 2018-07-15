package com.amitkmr.springboot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String name;
	private int standard;
	private boolean active;
	private String admissionYear;
	
	public Student(String name, int standard, String admissionYear) {
		this.name=name;
		this.standard=standard;
		this.admissionYear=admissionYear;
		active=true;
	}
	
	public Student() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStandard() {
		return standard;
	}
	public void setStandard(int standard) {
		this.standard = standard;
	}
	public Boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(String admissionYear) {
		this.admissionYear = admissionYear;
	}

}
