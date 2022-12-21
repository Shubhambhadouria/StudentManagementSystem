package com.platformcommons.app.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer courseId;
	private String courseName;
	private String courseDescription;
	private String courseType;
	private String courseDuration;
	private String courseTopics;
	
	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "courses")
	private List<Student> students;
	
}
