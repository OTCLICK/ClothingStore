//package org.example.clothingstore.cml;
//
//import jakarta.validation.Valid;
//import org.example.clothingstore.entities.Role;
//import org.example.clothingstore.entities.User;
//import org.example.clothingstore.entities.UserRolesEnum;
//import org.example.clothingstore.repositories.UserRepository;
//import org.example.clothingstore.repositories.UserRolesRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class UserDataLoader implements CommandLineRunner {
//
//    private final UserRepository userRepository;
//    private final UserRolesRepository rolesRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final String defaultPassword;
//
//    public UserDataLoader(UserRepository userRepository, UserRolesRepository rolesRepository, PasswordEncoder passwordEncoder,
//                          @Value("${app.default.password}") String defaultPassword) {
//        this.userRepository = userRepository;
//        this.rolesRepository = rolesRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.defaultPassword = defaultPassword;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
//        loadRoles();
//        loadUsers();
//    }
//
//    private void loadRoles() {
//        if (rolesRepository.count() == 0) {
//            var customerRole = new Role(UserRolesEnum.CUSTOMER);
//            var adminRole = new Role(UserRolesEnum.ADMIN);
//            rolesRepository.save(customerRole);
//            rolesRepository.save(adminRole);
//        }
//    }
//
//    private void loadUsers() {
//        if (userRepository.count() == 0) {
//            loadCustomer();
//            loadAdmin();
//        }
//    }
//
//    private void loadCustomer() {
//        var customerRole = rolesRepository.findRoleByName(UserRolesEnum.CUSTOMER).orElseThrow();
//        var customerUser = new User("customer", passwordEncoder.encode(defaultPassword), "customer@gmail.com",
//                "customer_name", 20);
//        customerUser.setRoles(List.of(customerRole));
//        userRepository.save(customerUser);
//    }
//
//    private void loadAdmin() {
////        var adminRole = rolesRepository.findRoleByName(UserRolesEnum.ADMIN).orElseThrow();
////        var adminUser = new User("0tcl1ck", "OTCLICK", passwordEncoder.encode(defaultPassword),
////                "ivan.budko.2004@mail.ru", "89204376867");
////        adminUser.setRoles(List.of(adminRole));
////        userRepository.save(adminUser);
//        var adminRole = rolesRepository.findRoleByName(UserRolesEnum.ADMIN).orElseThrow();
//        var adminUser = new User("admin", passwordEncoder.encode(defaultPassword), "admin@gmail.com",
//                "admin_name", 30);
//        adminUser.setRoles(List.of(adminRole));
//        userRepository.save(adminUser);
//    }
//}
