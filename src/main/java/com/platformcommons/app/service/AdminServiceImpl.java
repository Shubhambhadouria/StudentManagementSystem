package com.platformcommons.app.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformcommons.app.exceptions.AdminException;
import com.platformcommons.app.model.Admin;
import com.platformcommons.app.model.AdminDTO;
import com.platformcommons.app.model.CurrentSession;
import com.platformcommons.app.repository.AdminRepository;
import com.platformcommons.app.repository.CurrentSessionRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private CurrentSessionRepository currentSessionRepository;
	
	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		
		Admin admin2 = adminRepository.findByEmail(admin.getEmail());
		
		if(admin2!=null) {
			throw new AdminException("Admin already exist with email : "+admin.getEmail());
		}
		
		return adminRepository.save(admin);
	}

	@Override
	public CurrentSession loginAdmin(AdminDTO adminDTO) throws AdminException {
		
		Admin admin = adminRepository.findByEmail(adminDTO.getEmail());
		
		if(admin==null) {
			throw new AdminException("Admin is not registed with eamil "+adminDTO.getEmail());
		}
		
		if(!admin.getPassword().equals(adminDTO.getPassword())){
			throw new AdminException("Wrong Password");
		}
		
		Optional<CurrentSession> optional = currentSessionRepository.findById(admin.getAdminId());
		
		if(optional.isPresent()) {
			throw new AdminException("Admin already logged in");
		}
		
		CurrentSession currentSession = new CurrentSession();
		String key = RandomString.make(6); 
		currentSession.setAdminId(admin.getAdminId());
		currentSession.setTimestamp(LocalDateTime.now());
		currentSession.setIslogin(true);
		currentSession.setUuid(key);
		return currentSessionRepository.save(currentSession);
	}

	@Override
	public String logOutAdmin(String key) throws AdminException {
		
		CurrentSession currentSession = findByStudentandAdminByUuid(key);
		currentSessionRepository.delete(currentSession);
		
		return "Logged Out Successfully";
	}
	
	public CurrentSession findByStudentandAdminByUuid(String key) throws AdminException {
		
		CurrentSession keyString = currentSessionRepository.findByUuid(key);
		
		if (keyString == null) {
			throw new AdminException("Please Login First");
		}
		
		return keyString;
		
	}

}
