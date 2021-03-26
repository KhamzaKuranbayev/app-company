package uz.pdp.appcompany.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.constants.FrontURLs;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.payload.CompanyDTO;
import uz.pdp.appcompany.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping(FrontURLs.COMPANY)
public class CompanyController {

    final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> save(@RequestBody CompanyDTO companyDTO) {
        ApiResponse apiResponse = companyService.save(companyDTO);
        return ResponseEntity.status(apiResponse.isStatus() ? HttpStatus.CONFLICT : HttpStatus.ACCEPTED).body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll() {
        List<Company> companyList = companyService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(companyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findOneById(@PathVariable Integer id) {
        Company company = companyService.finOneById(id);
        return ResponseEntity.ok(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> edit(@RequestBody CompanyDTO companyDTO, @PathVariable Integer id) {
        ApiResponse response = companyService.edit(companyDTO, id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse response = companyService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

}
