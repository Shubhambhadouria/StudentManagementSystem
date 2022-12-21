package com.platformcommons.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.app.exceptions.AdminException;
import com.platformcommons.app.exceptions.CourseException;
import com.platformcommons.app.exceptions.StudentException;
import com.platformcommons.app.model.Address;
import com.platformcommons.app.model.LoginDTO;
import com.platformcommons.app.model.Student;
import com.platformcommons.app.service.StudentService;
import com.platformcommons.app.service.StudentServiceImpl;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@GetMapping("/validate")
	public ResponseEntity<Boolean> studentValidationHandler(@RequestBody LoginDTO loginDTO) throws StudentException{
		
		 Boolean statusBoolean =  studentServiceImpl.validateStudent(loginDTO);
		 
		 return new ResponseEntity<Boolean>(statusBoolean,HttpStatus.FOUND);
	}
	
	@PatchMapping("/updateStudent/{key}/{email}/{mobile}")
	public ResponseEntity<Student> updateStudentEmailandMobileHandler(@PathVariable String key,String email, String mobile) throws AdminException, StudentException{
		return new ResponseEntity<Student>(studentServiceImpl.updateEmailandMobile(key, email, mobile),HttpStatus.CREATED);
	}
	
	@PatchMapping("/updateStudent/{key}")
	public ResponseEntity<Student> updateStudentEmailandMobileHandler(@PathVariable String key,@RequestBody Address address) throws AdminException, StudentException{
		return new ResponseEntity<Student>(studentServiceImpl.updateStudentAddress(key, address),HttpStatus.CREATED);
	}
	
	@PutMapping("/leaveCourse")
	public ResponseEntity<Boolean> leaveCourseHandler(@PathVariable String key,Integer courseId) throws AdminException, StudentException, CourseException{
		return new ResponseEntity<Boolean>(studentServiceImpl.leaveCourse(key, courseId),HttpStatus.CREATED);
	}
	
	
}
