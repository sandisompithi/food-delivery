package com.reverside.sandiso.repository;

import com.reverside.sandiso.model.DeliveryAddress;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress, Long> {
    DeliveryAddress findBySuburb(String suburb);
    List<DeliveryAddress> findAll();
    void deleteBySuburb(String suburb);
}
