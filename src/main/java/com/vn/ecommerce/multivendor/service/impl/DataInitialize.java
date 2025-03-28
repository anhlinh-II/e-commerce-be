package com.vn.ecommerce.multivendor.service.impl;

import com.vn.ecommerce.multivendor.domain.USER_ROLE;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitialize implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        String adminUsername = "nguyenanhlinh775@gmail.com";

        if (userRepository.findByEmail(adminUsername) == null) {
            User adminUser = new User();

            adminUser.setPassword(passwordEncoder.encode("12345678"));
            adminUser.setFullName("anhlinh");
            adminUser.setEmail(adminUsername);
            adminUser.setRole(USER_ROLE.ROLE_ADMIN);

            User admin = userRepository.save(adminUser);
        }
    }
}
