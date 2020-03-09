package com.reverside.sandiso.repository;

import com.reverside.sandiso.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByAddress(String address);
}
