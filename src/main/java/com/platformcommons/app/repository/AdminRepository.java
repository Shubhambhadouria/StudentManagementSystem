package com.platformcommons.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.platformcommons.app.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	@Query("SELECT a FROM Admin a WHERE a.email = ?1")
	public Admin findByEmail(String email);
	
	
	
}
