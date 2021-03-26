package uz.pdp.appcompany.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public ResponseEntity<ApiResponse> save(Address address) {

        if (addressRepository.existsByStreetAndHome(address.getStreet(), address.getHome()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Such address is already exists!", false));

        addressRepository.save(address);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Address saved!", true));
    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address finOneById(Integer addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        return optionalAddress.orElse(new Address());
    }

    @Override
    public ApiResponse edit(Address address, Integer addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (optionalAddress.isPresent()) {
            optionalAddress.get().setHome(address.getHome());
            optionalAddress.get().setStreet(address.getStreet());
            addressRepository.save(optionalAddress.get());
            return new ApiResponse("The Address data updated!", true);
        }
        return new ApiResponse("This address id not found!", false);
    }

    @Override
    public ApiResponse delete(Integer addressId) {
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (!optionalAddress.isPresent())
            return new ApiResponse("Address not found!", false);
        addressRepository.deleteById(addressId);
        return new ApiResponse("Address deleted!", true);
    }
}
