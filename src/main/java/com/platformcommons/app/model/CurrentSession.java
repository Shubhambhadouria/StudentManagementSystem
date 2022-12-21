package com.platformcommons.app.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrentSession {

	@Id
	@Column(unique = true)
	private Integer adminId;//this adminId Used For admin and Student 
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "timeStamp")
	private LocalDateTime timestamp;
	@Column(name = "login_status")
	private Boolean islogin;

	
}
