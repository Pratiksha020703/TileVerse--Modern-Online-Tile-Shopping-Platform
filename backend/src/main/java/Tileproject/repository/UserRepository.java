package Tileproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}


// package Tileproject.repository;

// import Tileproject.model.User;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface UserRepository extends JpaRepository<User, Integer> {
//     Optional<User> findByEmail(String email);
// }