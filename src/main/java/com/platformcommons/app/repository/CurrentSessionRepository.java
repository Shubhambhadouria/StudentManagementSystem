package com.platformcommons.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.platformcommons.app.model.CurrentSession;

@Repository
public interface CurrentSessionRepository extends JpaRepository<CurrentSession, Integer>{

	@Query("SELECT s from CurrentSession s WHERE s.uuid = ?1")
	public CurrentSession findByUuid(String uuid);
	
}
