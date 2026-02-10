package Tileproject.controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import Tileproject.model.Role;
//import Tileproject.service.RoleService;
//
//@RestController
//@RequestMapping("/api/roles")
//public class RoleController {
//
//    private final RoleService roleService;
//
//    public RoleController(RoleService roleService) {
//        this.roleService = roleService;
//    }
//
//    @PostMapping
//    public Role createRole(@RequestBody Role role) {
//        return roleService.saveRole(role);
//    }
//}
//


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import Tileproject.model.Role;
import Tileproject.service.RoleService;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;
    
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }
}
