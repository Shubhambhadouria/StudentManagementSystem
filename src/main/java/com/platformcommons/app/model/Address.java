package com.platformcommons.app.model;

import com.platformcommons.app.enums.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer addressId;
	private String area;
	private String city;
	private String state;
	private String district;
	private String pincode;
	
	private AddressType addressType;
	
}
