package com.reverside.sandiso.service;

import com.reverside.sandiso.model.Address;

public interface AddressService {

    Address findByAddress(String address);
}
