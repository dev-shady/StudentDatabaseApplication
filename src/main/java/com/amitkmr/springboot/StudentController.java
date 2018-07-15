package com.amitkmr.springboot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.amitkmr.springboot.Student;
import com.amitkmr.springboot.StudentService;
import com.amitkumar.springboot.exception.BadRequestException;
import com.amitkumar.springboot.exception.NotFoundException;

@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/students")
	public List<Student> getAll(@RequestParam Map<String,String> params) {
		if(params.isEmpty())
			return studentService.getAll();
		else {
			List<Student> students = new ArrayList<>();
			List<Integer> classes = new ArrayList<>();
			Boolean active = null;
			String admissionYearAfter=null, admissionYearBefore=null;
			boolean bClass=false, bActive=false, bAdmissionYearAfter=false, bAdmissionYearBefore=false;
			if(params.containsKey("classes")) {
				bClass=true;
				String items = params.get("classes");
				StringTokenizer st = new StringTokenizer(items, ",");
				while(st.hasMoreTokens()) {
					classes.add(Integer.parseInt(st.nextToken()));
				}
			}
			
			if(params.containsKey("active")) {
				bActive=true;
				active = Boolean.parseBoolean(params.get("active"));
			}
			
			if(params.containsKey("admissionYearAfter")) {
				bAdmissionYearAfter=true;
				admissionYearAfter=params.get("admissionYearAfter");
			}
			
			if(params.containsKey("admissionYearBefore")) {
				bAdmissionYearBefore=true;
				admissionYearBefore=params.get("admissionYearBefore");
			}
			
			if(bClass) {
				for(int i=0; i<classes.size();i++) {
					//System.out.println("class "+classes.get(i));
					List<Student> ls = studentService.findByParameters(classes.get(i), active, admissionYearAfter, admissionYearBefore);
					students.addAll(ls);
				}
			}else {
				students = studentService.findByParameters(null, active, admissionYearAfter, admissionYearBefore);
			}
			
			return students;
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/students")
	@ResponseStatus(HttpStatus.CREATED)
	public void addStudent(@RequestBody Student student) {
		
		if(student==null || student.getStandard()==null || student.getStandard()==0
				|| student.getName()==null || student.getName().isEmpty()
				|| student.getAdmissionYear()==null || student.getAdmissionYear().isEmpty())
			throw new BadRequestException();
		else {
			student.setActive(true);
			studentService.addStudent(student);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/students/{id}")
	public Student getStudent(@PathVariable String id) {
		Student st = studentService.getStudent(Long.parseLong(id));
		if(st==null)
			throw new NotFoundException();
		else 
			return st;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/students/{id}")
	public void deleteStudent(@PathVariable String id) {
		Student st = studentService.getStudent(Long.parseLong(id));
		if(st==null)
			throw new NotFoundException();
		else
			studentService.deleteStudent(Long.parseLong(id));
	}
	
	@RequestMapping(method=RequestMethod.PATCH, value="/students/{id}")
	public void updateStudent(@PathVariable String id, @RequestBody Student student) {
		Student st = studentService.getStudent(Long.parseLong(id));
		if(st==null)
			throw new NotFoundException();
		else {
			if(student == null || student.getStandard()==0)
				throw new BadRequestException();
			else
				studentService.updateStudent(Long.parseLong(id),student);
		}
	}
	

}
