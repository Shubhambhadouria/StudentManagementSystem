package com.platformcommons.app.service;

import java.time.LocalDate;
import java.util.List;

import com.platformcommons.app.exceptions.AdminException;
import com.platformcommons.app.exceptions.CourseException;
import com.platformcommons.app.exceptions.StudentException;
import com.platformcommons.app.model.Address;
import com.platformcommons.app.model.CurrentSession;
import com.platformcommons.app.model.LoginDTO;
import com.platformcommons.app.model.Student;

public interface StudentService {

	public Student registerStudent(String key,Student student) throws StudentException,AdminException;
	
	public Student getStudentById(Integer studentId) throws StudentException;
	
	public CurrentSession loginStudent(LoginDTO loginDTO) throws StudentException ;
	
	public Boolean validateStudent(LoginDTO loginDTO) throws StudentException ;
	
	public List<Student> getStudentByName(String name) throws StudentException;
	
	public Student updateEmailandMobile(String key,String email,String mobile) throws StudentException,AdminException;
	
	public Student updateStudentAddress(String key,Address address) throws StudentException,AdminException;
	
	public String getStudentCourse(String key) throws StudentException,AdminException;
	
	public Boolean leaveCourse(String key,Integer courseId) throws StudentException,AdminException,CourseException;
	
	public Student registerCourseToStudent(String key,Integer courseId,Integer studentId) throws StudentException,AdminException; 
	
}
