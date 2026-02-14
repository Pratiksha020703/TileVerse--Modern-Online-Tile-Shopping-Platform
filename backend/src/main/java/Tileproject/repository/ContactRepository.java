package Tileproject.repository;

import Tileproject.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactMessage, Integer> {
}
