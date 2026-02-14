// package Tileproject.service;

// import Tileproject.model.Role;
// import Tileproject.model.User;
// import Tileproject.repository.RoleRepository;
// import Tileproject.repository.UserRepository;

// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;

// @Service
// public class UserService {

//     private final UserRepository userRepository;
//     private final RoleRepository roleRepository;

//     public UserService(UserRepository userRepository, RoleRepository roleRepository) {
//         this.userRepository = userRepository;
//         this.roleRepository = roleRepository;
//     }

//     public User getOrCreateUserFromJWT() {

//         String email = SecurityContextHolder.getContext()
//                 .getAuthentication()
//                 .getName();

//         return userRepository.findByEmail(email)
//                 .orElseGet(() -> {
//                     User user = new User();
//                     user.setEmail(email);
//                     user.setFullName("User");
//                     user.setCreatedAt(LocalDateTime.now());

//                     Role role = roleRepository.findByRoleName("CUSTOMER").orElseThrow();
//                     user.setRole(role);

//                     return userRepository.save(user);
//                 });
//     }
// }


// package Tileproject.service;

// import Tileproject.model.Role;
// import Tileproject.model.User;
// import Tileproject.repository.RoleRepository;
// import Tileproject.repository.UserRepository;

// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Service;

// import java.time.LocalDateTime;

// @Service
// public class UserService {

//     private final UserRepository userRepository;
//     private final RoleRepository roleRepository;

//     public UserService(UserRepository userRepository, RoleRepository roleRepository) {
//         this.userRepository = userRepository;
//         this.roleRepository = roleRepository;
//     }

//     // 🔥 GOLD METHOD – DO NOT CHANGE
//     public User getOrCreateUserFromJWT() {

//         String email = SecurityContextHolder.getContext()
//                 .getAuthentication()
//                 .getName();

//         return userRepository.findByEmail(email)
//                 .orElseGet(() -> {
//                     User user = new User();
//                     user.setEmail(email);
//                     user.setFullName("User");
//                     user.setCreatedAt(LocalDateTime.now());

//                     Role role = roleRepository.findByRoleName("CUSTOMER")
//                             .orElseThrow();

//                     user.setRole(role);

//                     return userRepository.save(user);
//                 });
//     }
// }


package Tileproject.service;

import Tileproject.model.Role;
import Tileproject.model.User;
import Tileproject.repository.RoleRepository;
import Tileproject.repository.UserRepository;
import Tileproject.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtUtil jwtUtil;
    private final HttpServletRequest request;


    public UserService(UserRepository userRepository, RoleRepository roleRepository,JwtUtil jwtUtil,HttpServletRequest request) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
         this.jwtUtil = jwtUtil;
         this.request = request;
    }

    public User getOrCreateUserFromJWT() {

    String email = SecurityContextHolder.getContext()
            .getAuthentication()
            .getName();
            
    String header = request.getHeader("Authorization");
    String token = header.substring(7);

    Claims claims = jwtUtil.validateToken(token);
    String fullName = claims.get("fullName", String.class);


    return userRepository.findByEmail(email)
            .orElseGet(() -> {
                User user = new User();
                user.setEmail(email);
                // user.setFullName("User");
                user.setFullName(fullName);
                user.setCreatedAt(LocalDateTime.now());

                Role role = roleRepository.findByRoleName("CUSTOMER").orElseThrow();
                user.setRole(role);

                return userRepository.save(user);
            });
}


}

