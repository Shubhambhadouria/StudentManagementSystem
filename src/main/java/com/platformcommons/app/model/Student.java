package com.platformcommons.app.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.platformcommons.app.enums.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	private String name;
	private LocalDate dob;
	private Gender gender;
	private String password;
	private String email;
	private String mobile;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	@ManyToMany(cascade = CascadeType.MERGE)
	private List<Course> courses;
	
	
}
