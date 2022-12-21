package com.platformcommons.app.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class LoginDTO {

	private Integer studentId;
	private LocalDate dob;
	private String password;
	
}
