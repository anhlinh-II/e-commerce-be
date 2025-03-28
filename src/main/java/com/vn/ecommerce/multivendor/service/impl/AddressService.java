package com.vn.ecommerce.multivendor.service.impl;

import com.vn.ecommerce.multivendor.modal.Address;
import com.vn.ecommerce.multivendor.modal.User;
import com.vn.ecommerce.multivendor.repository.AddressRepository;
import com.vn.ecommerce.multivendor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Set<Address> getAddressesByUser(User user) {
        return user.getAddresses();
    }

    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    public Address saveAddress(Address address) {

        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address addressDetails) {
        return addressRepository.findById(id).map(address -> {
            address.setName(addressDetails.getName());
            address.setLocality(addressDetails.getLocality());
            address.setAddress(addressDetails.getAddress());
            address.setCity(addressDetails.getCity());
            address.setRegion(addressDetails.getRegion());
            address.setPinCode(addressDetails.getPinCode());
            address.setMobile(addressDetails.getMobile());
            return addressRepository.save(address);
        }).orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}

