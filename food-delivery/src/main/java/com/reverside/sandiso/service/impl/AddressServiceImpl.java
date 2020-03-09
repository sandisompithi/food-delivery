package com.reverside.sandiso.service.impl;

import com.reverside.sandiso.model.Address;
import com.reverside.sandiso.repository.AddressRepository;
import com.reverside.sandiso.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address findByAddress(String address) {
        return addressRepository.findByAddress(address);
    }
}
