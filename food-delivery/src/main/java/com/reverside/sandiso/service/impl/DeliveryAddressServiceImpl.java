package com.reverside.sandiso.service.impl;

import com.reverside.sandiso.model.DeliveryAddress;
import com.reverside.sandiso.repository.DeliveryAddressRepository;
import com.reverside.sandiso.service.DeliveryAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeliveryAddressServiceImpl implements DeliveryAddressService {

    @Autowired
    private DeliveryAddressRepository addressRepository;

    @Override
    public List<DeliveryAddress> findDeliveryAddressList(Principal principal) {
        String username = principal.getName();
        List<DeliveryAddress> addressList = addressRepository.findAll().stream()
                .filter(userAddress -> username.equals(userAddress.getUser().getUsername()))
                .collect(Collectors.toList());
        return addressList;
    }

    @Override
    public DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress) {
        return addressRepository.save(deliveryAddress);
    }

    @Override
    public DeliveryAddress findDeliveryAddressBySuburb(String suburb) {
        return addressRepository.findBySuburb(suburb);
    }

    @Override
    public void deleteBySuburb(String suburb) {
        addressRepository.deleteBySuburb(suburb);
    }

	@Override
	public DeliveryAddress findById(Long id) {
		Optional<DeliveryAddress> optional = addressRepository.findById(id);
		
		DeliveryAddress address = null;
		if (optional.isPresent()) {
			address = optional.get();
		} else {
			throw new RuntimeException("Address not found for id :: " + id);
		}
		return address;
	}
}
