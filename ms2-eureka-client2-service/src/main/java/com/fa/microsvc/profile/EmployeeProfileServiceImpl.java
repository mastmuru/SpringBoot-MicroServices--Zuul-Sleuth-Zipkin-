/**
 * 
 */
package com.fa.microsvc.profile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Muruganandam
 *
 */
@Service
public class EmployeeProfileServiceImpl {
	@Autowired
	ProfileRepository repository;
	List<EmployeeProfile> employeeProfileList = new ArrayList<>();

	public void addEmployeeProfile(EmployeeProfile profile) {
		repository.save(profile);
	}

	public List<EmployeeProfile> getEmployeeProfiles() {
		return repository.findAll();
	}
}
