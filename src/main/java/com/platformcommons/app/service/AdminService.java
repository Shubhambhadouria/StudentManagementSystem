package com.platformcommons.app.service;

import com.platformcommons.app.exceptions.AdminException;
import com.platformcommons.app.model.Admin;
import com.platformcommons.app.model.AdminDTO;
import com.platformcommons.app.model.CurrentSession;

public interface AdminService {

	public Admin registerAdmin(Admin admin) throws AdminException;
	
	public CurrentSession loginAdmin(AdminDTO adminDTO) throws AdminException; 
	
	public String logOutAdmin(String key) throws AdminException;
	
}
