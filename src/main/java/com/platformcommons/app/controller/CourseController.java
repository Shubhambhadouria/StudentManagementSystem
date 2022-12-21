package com.platformcommons.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.platformcommons.app.exceptions.AdminException;
import com.platformcommons.app.exceptions.CourseException;
import com.platformcommons.app.exceptions.StudentException;
import com.platformcommons.app.model.Course;
import com.platformcommons.app.service.StudentServiceImpl;

@RestController
public class CourseController {

	@Autowired
	StudentServiceImpl studentServiceImpl;
	
	@PostMapping("/addCourse/{key}")
	public ResponseEntity<Course> addCourseHandler(@PathVariable String key,@RequestBody Course course) throws AdminException, StudentException, CourseException{
		return new ResponseEntity<Course>(studentServiceImpl.addNewCourse(key, course),HttpStatus.CREATED);
	}
	
}
