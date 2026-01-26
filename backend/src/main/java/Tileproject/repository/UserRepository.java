package Tileproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
