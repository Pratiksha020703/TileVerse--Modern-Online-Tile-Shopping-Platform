// package Tileproject.repository;

// import Tileproject.model.Address;
// import Tileproject.model.User;
// import org.springframework.data.jpa.repository.JpaRepository;

// import java.util.Optional;

// public interface AddressRepository extends JpaRepository<Address, Integer> {
//     // Optional<Address> findByUser(User user);
//     List<Address> findByUser(User user);

// }


package Tileproject.repository;

import Tileproject.model.Address;
import Tileproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByUser(User user);

}
