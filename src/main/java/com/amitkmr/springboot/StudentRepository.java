package com.amitkmr.springboot;

import java.util.List;
import com.amitkmr.springboot.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	public List<Student> findByActive(boolean active);
	
	public List<Student> findByStandard(int standard);
	
	public List<Student> findByAdmissionYear(String year);
	
	public List<Student> findByAdmissionYearLessThanEqual(String year);
	
	public List<Student> findByAdmissionYearGreaterThanEqual(String year);
	
	public List<Student> findByStandardAndActiveAndAdmissionYearLessThanEqual(int standard
			,boolean active, String date);
	
	public List<Student> findByStandardAndActiveAndAdmissionYearGreaterThanEqual(int standard
			,boolean active, String date);
	
	public List<Student> findByStandardAndAdmissionYearLessThanEqual(int standard
			,String date);
	
	public List<Student> findByStandardAndAdmissionYearGreaterThanEqual(int standard
			,String date);
	
	
	@Query("select u from Student u where (?1 is null or u.standard = ?1) and (?2 is null or u.active = ?2) and (?3 is null or u.admissionYear >= ?3) and (?4 is null or u.admissionYear <= ?4)")
		 	
	 public List<Student> findByParameters(Integer standard, Boolean active, String admissionYearAfter, String admissionYearBefore);
	
	
}