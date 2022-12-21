package com.platformcommons.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.app.exceptions.StudentException;
import com.platformcommons.app.model.LoginDTO;
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
	
	
	
	
}
