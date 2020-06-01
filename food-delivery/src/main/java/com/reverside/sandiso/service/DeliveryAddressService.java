package com.reverside.sandiso.service;

import com.reverside.sandiso.model.DeliveryAddress;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface DeliveryAddressService {
    List<DeliveryAddress> findDeliveryAddressList(Principal principal);
    DeliveryAddress saveDeliveryAddress(DeliveryAddress deliveryAddress);
    DeliveryAddress findDeliveryAddressBySuburb(String suburb);
    void deleteBySuburb(String suburb);
    
    DeliveryAddress findById(Long id);
}
