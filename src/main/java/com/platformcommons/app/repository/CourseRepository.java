package com.platformcommons.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.platformcommons.app.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer>{

	
	
}
