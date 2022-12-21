package com.platformcommons.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.app.exceptions.AdminException;
import com.platformcommons.app.exceptions.StudentException;
import com.platformcommons.app.model.Admin;
import com.platformcommons.app.model.AdminDTO;
import com.platformcommons.app.model.CurrentSession;
import com.platformcommons.app.model.Student;
import com.platformcommons.app.service.AdminService;
import com.platformcommons.app.service.AdminServiceImpl;
import com.platformcommons.app.service.StudentServiceImpl;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@PostMapping("/register")
	public ResponseEntity<Admin> registerAdminHandler(@RequestBody Admin admin) throws AdminException{
		return new ResponseEntity<Admin>(adminServiceImpl.registerAdmin(admin),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<CurrentSession> loginAdminHandler(@RequestBody AdminDTO adminDTO) throws AdminException{
		return new ResponseEntity<CurrentSession>(adminServiceImpl.loginAdmin(adminDTO),HttpStatus.CREATED);
	}
	
	@PostMapping("/logout/{keyString}")
	public ResponseEntity<String> logoutAdminHandler(@PathVariable String keyString) throws AdminException{
		return new ResponseEntity<String>(adminServiceImpl.logOutAdmin(keyString),HttpStatus.OK);
	}
	
	@PostMapping("/registerStudent/{key}")
	public ResponseEntity<Student> registerStudentHandler(@PathVariable String key, @RequestBody Student student) throws AdminException, StudentException{
		return new ResponseEntity<Student>(studentServiceImpl.registerStudent(key, student),HttpStatus.CREATED);
	}
	
	
}
