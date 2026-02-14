package Tileproject.config;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import Tileproject.model.Role;
import Tileproject.repository.RoleRepository;

@Component
public class RoleSeeder {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void seed() {

        if (roleRepository.findByRoleName("ADMIN").isEmpty()) {
            Role admin = new Role();
            admin.setRoleName("ADMIN");
            roleRepository.save(admin);
        }

        if (roleRepository.findByRoleName("CUSTOMER").isEmpty()) {
            Role customer = new Role();
            customer.setRoleName("CUSTOMER");
            roleRepository.save(customer);
        }
    }
}
