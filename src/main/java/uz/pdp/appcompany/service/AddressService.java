package uz.pdp.appcompany.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.payload.ApiResponse;

import java.util.List;

@Service
public interface AddressService {

    ResponseEntity<ApiResponse> save(Address address);                      // CREATE

    List<Address> findAll();                                // READ

    Address finOneById(Integer addressId);

    ApiResponse edit(Address address, Integer addressId);   // UPDATE

    ApiResponse delete(Integer addressId);                  // DELETE

}
