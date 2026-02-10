// package Tileproject.controller;

// import Tileproject.model.Address;
// import Tileproject.model.User;
// import Tileproject.repository.AddressRepository;
// import Tileproject.service.UserService;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/user")
// public class UserAddressController {

//     private final AddressRepository addressRepository;
//     private final UserService userService;

//     public UserAddressController(AddressRepository addressRepository, UserService userService) {
//         this.addressRepository = addressRepository;
//         this.userService = userService;
//     }

//     // 🔹 GET logged-in user's address
//     @GetMapping("/address")
//     public Address getAddress() {
//         User user = userService.getOrCreateUserFromJWT();

//         return addressRepository.findByUser(user).orElse(null);
//     }

//     // 🔹 SAVE or UPDATE logged-in user's address
//     @PostMapping("/address")
//     public Address saveAddress(@RequestBody Address address) {

//         User user =userService.getOrCreateUserFromJWT();


//         address.setUser(user);

//         return addressRepository.save(address);
//     }
// }


// @RestController
// @RequestMapping("/api/user/address")
// public class UserAddressController {

//     private final AddressRepository addressRepository;
//     private final UserService userService;

//     public UserAddressController(AddressRepository addressRepository, UserService userService) {
//         this.addressRepository = addressRepository;
//         this.userService = userService;
//     }

//     // 🔹 GET all addresses of user
//     @GetMapping
//     public List<Address> getAddresses() {
//         User user = userService.getOrCreateUserFromJWT();
//         return addressRepository.findByUser(user);
//     }

//     // 🔹 ADD new address
//     @PostMapping
//     public Address addAddress(@RequestBody Address address) {
//         User user = userService.getOrCreateUserFromJWT();
//         address.setUser(user);
//         return addressRepository.save(address);
//     }

//     // 🔹 UPDATE address
//     @PutMapping("/{id}")
//     public Address update(@PathVariable Integer id, @RequestBody Address address) {

//         User user = userService.getOrCreateUserFromJWT();

//         Address existing = addressRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Address not found"));

//         if (!existing.getUser().getUserId().equals(user.getUserId())) {
//             throw new RuntimeException("Unauthorized");
//         }

//         existing.setName(address.getName());
//         existing.setPhone(address.getPhone());
//         existing.setAddress(address.getAddress());
//         existing.setCity(address.getCity());
//         existing.setState(address.getState());
//         existing.setPincode(address.getPincode());

//         return addressRepository.save(existing);
//     }

//     // 🔹 DELETE address
//     @DeleteMapping("/{id}")
//     public void delete(@PathVariable Integer id) {

//         User user = userService.getOrCreateUserFromJWT();

//         Address existing = addressRepository.findById(id)
//                 .orElseThrow(() -> new RuntimeException("Address not found"));

//         if (!existing.getUser().getUserId().equals(user.getUserId())) {
//             throw new RuntimeException("Unauthorized");
//         }

//         addressRepository.delete(existing);
//     }
// }


package Tileproject.controller;

import Tileproject.model.Address;
import Tileproject.model.User;
import Tileproject.repository.AddressRepository;
import Tileproject.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAddressController {

    private final AddressRepository addressRepository;
    private final UserService userService;

    public UserAddressController(AddressRepository addressRepository,
                                 UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    // 1️⃣ Get all addresses
    @GetMapping("/addresses")
    public List<Address> getAllAddresses() {
        User user = userService.getOrCreateUserFromJWT();
        return addressRepository.findByUser(user);
    }

    // 2️⃣ Add new address
    @PostMapping("/address")
    public Address addAddress(@RequestBody Address address) {
        User user = userService.getOrCreateUserFromJWT();
        address.setUser(user);
        return addressRepository.save(address);
    }

    // 3️⃣ Update existing address
    @PutMapping("/address/{id}")
    public Address updateAddress(@PathVariable Integer id,
                                 @RequestBody Address updated) {

        User user = userService.getOrCreateUserFromJWT();

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        updated.setAddressId(id);
        updated.setUser(user);

        return addressRepository.save(updated);
    }

    // 4️⃣ Delete address
    @DeleteMapping("/address/{id}")
    public void deleteAddress(@PathVariable Integer id) {

        User user = userService.getOrCreateUserFromJWT();

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getUserId().equals(user.getUserId())) {
            throw new RuntimeException("Unauthorized");
        }

        addressRepository.delete(address);
    }
}
