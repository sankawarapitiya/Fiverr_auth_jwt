package com.nbkarthi.auth_jwt.auth;

import com.nbkarthi.auth_jwt.model.Role;
import com.nbkarthi.auth_jwt.model.User;
import com.nbkarthi.auth_jwt.repository.UserRepository;
import com.nbkarthi.auth_jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class SystemUserGenerator  implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("admin@123.com");
        user.setFirstName("admin");
        user.setLastName("user");
        user.setPublicKey(passwordEncoder.encode("adm"));
        user.setRole(Role.ADMIN);
        userRepository.save(user);

        User user2 = new User();
        user2.setEmail("user@123.com");
        user2.setFirstName("user");
        user2.setLastName("user2");
        user2.setPublicKey(passwordEncoder.encode("usr"));
        user2.setRole(Role.USER);
        userRepository.save(user2);
    }
}
