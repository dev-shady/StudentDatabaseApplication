package com.amitkmr.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amitkmr.springboot.Student;
import com.amitkmr.springboot.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAll() {
		//this would also work
		//return studentRepository.findByParameters(null, null,null,null);
		List<Student> students = new ArrayList<Student>();
		studentRepository.findAll().forEach(students::add);
		return students;
	}
	
	public List<Student> getByClass(int standard) {
		return studentRepository.findByStandard(standard);
	}
	
	public List<Student> getByActive(boolean active) {
		return studentRepository.findByActive(active);
	}
	
	public List<Student> getByAdmissionYearAfter(String year) {
		return studentRepository.findByAdmissionYearGreaterThanEqual(year);
	}	

	public List<Student> getByAdmissionYearBefore(String year) {
		return studentRepository.findByAdmissionYearLessThanEqual(year);
	}	
	
	public void addStudent(Student student) {
		studentRepository.save(student);
	}


	public Student getStudent(long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).orElse(null);
	}
	
	public void deleteStudent(long id) {
		Student st = studentRepository.findById(id).orElse(null);
		st.setActive(false);
		if(st!=null) {
			studentRepository.save(st);
		}
	}

	public void updateStudent(long id, Student student) {
		Student st =studentRepository.findById(id).orElse(null);
		st.setStandard(student.getStandard());
		studentRepository.save(st);
	}
	
	public List<Student> findByParameters(Integer standard, Boolean active,String admissionYearAfter, String admissionYearBefore) {
		if(standard!=null) {
			System.out.println("standard "+standard);
		}
		if(active!=null) {
			System.out.println("active "+active);
		}
		if(admissionYearAfter!=null) {
			System.out.println("admissionYearAfter "+admissionYearAfter);
		}
		if(admissionYearBefore!=null) {
			System.out.println("admissionYearBefore " + admissionYearBefore);
		}
								
		return studentRepository.findByParameters(standard, active,admissionYearAfter, admissionYearBefore);
	}
	
	
	
}
