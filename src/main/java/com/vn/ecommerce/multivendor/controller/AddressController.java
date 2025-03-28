package com.vn.ecommerce.multivendor.controller;

import com.vn.ecommerce.multivendor.modal.Address;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.repository.UserRepository;
import com.vn.ecommerce.multivendor.service.UserService;
import com.vn.ecommerce.multivendor.service.impl.AddressService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Address> createAddress(@RequestBody Address address, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        var savedAddress = addressService.saveAddress(address);
        if (user != null) {
            user.getAddresses().add(savedAddress);
            userRepository.save((user));
        }
        return ResponseEntity.ok(savedAddress);
    }

    @GetMapping
    public ResponseEntity<Set<Address>> getAddressesByUser(@RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(addressService.getAddressesByUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressDetails) {
        try {
            Address updatedAddress = addressService.updateAddress(id, addressDetails);
            return ResponseEntity.ok(updatedAddress);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }
}

