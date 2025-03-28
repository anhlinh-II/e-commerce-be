package com.vn.ecommerce.multivendor.repository;

import com.vn.ecommerce.multivendor.modal.Address;
import com.vn.ecommerce.multivendor.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
