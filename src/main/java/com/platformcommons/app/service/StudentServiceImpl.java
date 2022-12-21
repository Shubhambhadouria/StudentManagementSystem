package com.platformcommons.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformcommons.app.exceptions.AdminException;
import com.platformcommons.app.exceptions.CourseException;
import com.platformcommons.app.exceptions.StudentException;
import com.platformcommons.app.model.Address;
import com.platformcommons.app.model.Admin;
import com.platformcommons.app.model.Course;
import com.platformcommons.app.model.CurrentSession;
import com.platformcommons.app.model.LoginDTO;
import com.platformcommons.app.model.Student;
import com.platformcommons.app.repository.AdminRepository;
import com.platformcommons.app.repository.CourseRepository;
import com.platformcommons.app.repository.CurrentSessionRepository;
import com.platformcommons.app.repository.StudentRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CurrentSessionRepository currentSessionRepository;
	
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Override
	public Student registerStudent(String key,Student student) throws StudentException, AdminException {
		
		CurrentSession currentSession = adminServiceImpl.findByStudentandAdminByUuid(key);
		
		Optional<Admin> optional = adminRepository.findById(currentSession.getAdminId());
		
		if(!optional.isPresent()) {
			
			throw new AdminException("Admin is not Found");
			
		}
		
		return studentRepository.save(student);
		
	}

	@Override
	public Student getStudentById(Integer studentId) throws StudentException {
		
		return studentRepository.findById(studentId).orElseThrow(()-> new StudentException("Invalid Student ID : "+studentId));
		
	}

	@Override
	public CurrentSession loginStudent(LoginDTO loginDTO) throws StudentException {
		Optional<Student> optional =  studentRepository.findById(loginDTO.getStudentId());
		
		if(!optional.isPresent()) {
			throw new StudentException("Student ID not exist "+loginDTO.getStudentId());
		}
		
		Student students =  optional.get();
		
		if(!students.getDob().equals(loginDTO.getDob())) {
			throw new StudentException("Dob is Mismatched");
		}
		
		if(!students.getPassword().equals(loginDTO.getPassword())) {
			throw new StudentException("Password is incorrect");
		}
		
		CurrentSession currentSession = new CurrentSession();
		String key = RandomString.make(6); 
		currentSession.setAdminId(students.getStudentId());
		currentSession.setTimestamp(LocalDateTime.now());
		currentSession.setIslogin(true);
		currentSession.setUuid(key);
		
		return currentSessionRepository.save(currentSession);
		
	
		
	}

	@Override
	public Boolean validateStudent(LoginDTO loginDTO) throws StudentException {
		
		Optional<Student> optional =  studentRepository.findById(loginDTO.getStudentId());
		
		if(!optional.isPresent()) {
			throw new StudentException("Student ID not exist "+loginDTO.getStudentId());
		}
		
		Student students =  optional.get();
		
		if(!students.getDob().equals(loginDTO.getDob())) {
			throw new StudentException("Dob is Mismatched");
		}
		
//		if(!students.getPassword().equals(loginDTO.getPassword())) {
//			throw new StudentException("Password is incorrect");
//		}
		
		return true;
	}

	@Override
	public List<Student> getStudentByName(String name) throws StudentException {
		
		List<Student> list =  studentRepository.findByName(name);
		
		if(list.isEmpty()) {
			throw new StudentException("No Student Found with Name :"+name);
		}
		
		return list;
		
	}

	@Override
	public Student updateEmailandMobile(String key,String email,String mobile) throws StudentException, AdminException {
		
		CurrentSession currentSession = adminServiceImpl.findByStudentandAdminByUuid(key);
		
		Optional<Student> optional = studentRepository.findById(currentSession.getAdminId());
		
		if(!optional.isPresent()) {
			throw new StudentException("Student does not exist");
		}
		
		Student student = optional.get();
		student.setEmail(email);
		student.setMobile(mobile);
		
		return studentRepository.save(student);
		
	}

	@Override
	public Student updateStudentAddress(String key,Address address) throws StudentException, AdminException {
		
		CurrentSession currentSession = adminServiceImpl.findByStudentandAdminByUuid(key);
		
		Optional<Student> optional = studentRepository.findById(currentSession.getAdminId());
		
		if(!optional.isPresent()) {
			throw new StudentException("Student does not exist");
		}

		Student student = optional.get();
		List<Address> addresses = student.getAddresses();
		
		for(Address address2 : addresses) {
			if(address2.getAddressType().equals(address.getAddressType())) {
				address2.setAddressId(address2.getAddressId());
				address2.setArea(address.getArea());
				address2.setCity(address.getCity());
				address2.setDistrict(address.getDistrict());
				address2.setPincode(address.getPincode());
				address2.setState(address.getState());
			}
		}
		
		student.setAddresses(addresses);
		
		return studentRepository.save(student);
		
	}

	@Override
	public String getStudentCourse(String key) throws StudentException, AdminException {
		
		CurrentSession currentSession = adminServiceImpl.findByStudentandAdminByUuid(key);
		
		Optional<Student> optional = studentRepository.findById(currentSession.getAdminId());
		
		if(!optional.isPresent()) {
			throw new StudentException("Student does not exist");
		}
		
		return optional.get().getCourses().toString();

	}

	@Override
	public Boolean leaveCourse(String key,Integer courseId) throws StudentException, AdminException, CourseException {
		
		CurrentSession currentSession = adminServiceImpl.findByStudentandAdminByUuid(key);
		
		Optional<Student> optional = studentRepository.findById(currentSession.getAdminId());
		
		if(!optional.isPresent()) {
			throw new StudentException("Student does not exist");
		}
		Student student=optional.get();
		List<Course> courses = student.getCourses();		
		
		for(Course course : courses) {
			if(course.getCourseId().equals(courseId)) {
				courseRepository.delete(course);
				student.setCourses(courses);
				
				studentRepository.save(student);
				
				return true;
			}
		}
		throw new CourseException("Cousre is not  found with student name :"+student.getName());
	
		
	}

	@Override
	public Student registerCourseToStudent(String key,Integer courseId, Integer studentId) throws StudentException, AdminException {
		
		CurrentSession currentSession = adminServiceImpl.findByStudentandAdminByUuid(key);
		
		Optional<Admin> optional = adminRepository.findById(currentSession.getAdminId());
		
		if(!optional.isPresent()) {
			throw new StudentException("Admin does not exist");
		}
		
		Student student = studentRepository.findById(studentId).get();
		
		List<Course> courses = student.getCourses();
		courses.add(courseRepository.findById(courseId).get());
		
		student.setCourses(courses);
		
		return studentRepository.save(student);
		
	}

}
