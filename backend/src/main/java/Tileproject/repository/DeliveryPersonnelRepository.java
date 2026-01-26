package Tileproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Tileproject.model.DeliveryPersonnel;

public interface DeliveryPersonnelRepository extends JpaRepository<DeliveryPersonnel, Integer>{

	Optional<DeliveryPersonnel> findByUser_UserId(Integer userId);
}
