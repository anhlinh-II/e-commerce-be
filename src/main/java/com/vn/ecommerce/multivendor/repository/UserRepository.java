package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
