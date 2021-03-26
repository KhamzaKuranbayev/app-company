package uz.pdp.appcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.constants.FrontURLs;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.service.AddressService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(FrontURLs.ADDRESS)
public class AddressController {

    final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@Valid @RequestBody Address address) {
        return addressService.save(address);
    }

    @GetMapping
    public HttpEntity<List<Address>> findAll(){
        List<Address> addressList = addressService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(addressList);
    }

    @GetMapping("/{id}")
    public HttpEntity<Address> findOneById(@PathVariable Integer id){
        Address address = addressService.finOneById(id);
        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@RequestBody Address address, @PathVariable Integer id) {
        ApiResponse response = addressService.edit(address, id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse response = addressService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
