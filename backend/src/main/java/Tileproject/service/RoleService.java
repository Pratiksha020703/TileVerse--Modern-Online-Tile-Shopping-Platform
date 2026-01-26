package Tileproject.service;

	import org.springframework.stereotype.Service;

import Tileproject.model.Role;
import Tileproject.repository.RoleRepository;

import java.util.List;

	@Service
	public class RoleService {

	    private final RoleRepository roleRepository;

	    public RoleService(RoleRepository roleRepository) {
	        this.roleRepository = roleRepository;
	    }

	    // CREATE
	    public Role createRole(Role role) {
	        return roleRepository.save(role);
	    }

	    // READ ALL
	    public List<Role> getAllRoles() {
	        return roleRepository.findAll();
	    }

	    // READ BY ID
	    public Role getRoleById(Integer id) {
	        return roleRepository.findById(id).orElse(null);
	    }
	  
	}


