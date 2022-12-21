package com.platformcommons.app.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.platformcommons.app.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{

	@Query("SELECT s FROM Student s WHERE s.dob = ?1")
	public List<Student> findByDob(LocalDate dob);
	
	@Query("SELECT name FROM Student s WHERE s.name LIKE '?1'")
	public List<Student> findByName(String name);
	
}
