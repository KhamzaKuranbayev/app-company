package uz.pdp.appcompany.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.payload.CompanyDTO;

import java.util.List;

@Service
public interface CompanyService {

    ApiResponse save(CompanyDTO companyDTO);                         // CREATE

    List<Company> findAll();                                         // READ

    Company finOneById(Integer companyId);

    ApiResponse edit(CompanyDTO companyDTO, Integer companyId);      // UPDATE

    ApiResponse delete(Integer companyId);                           // DELETE

}
