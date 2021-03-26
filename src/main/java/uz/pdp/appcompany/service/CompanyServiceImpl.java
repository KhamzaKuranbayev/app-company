package uz.pdp.appcompany.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.payload.CompanyDTO;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    final CompanyRepository companyRepository;
    final AddressRepository addressRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository,
                              AddressRepository addressRepository) {
        this.companyRepository = companyRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public ApiResponse save(CompanyDTO companyDTO) {
        if (companyRepository.existsByCorpName(companyDTO.getCorpName()))
            return new ApiResponse("This company name is already exists!", false);
        Company company = new Company();
        company.setCorpName(companyDTO.getCorpName());
        company.setDirectorName(companyDTO.getDirectorName());

        Address address = new Address();
        address.setHome(companyDTO.getHome());
        address.setStreet(companyDTO.getStreet());

        Address savedAddress = addressRepository.save(address);
        company.setAddress(savedAddress);

        companyRepository.save(company);
        return new ApiResponse("Address saved!", true);
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Company finOneById(Integer companyId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        return optionalCompany.orElse(new Company());
    }

    @Override
    public ApiResponse edit(CompanyDTO companyDTO, Integer companyId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isPresent()) {
            optionalCompany.get().setCorpName(companyDTO.getCorpName());
            optionalCompany.get().setDirectorName(companyDTO.getDirectorName());

            Address editAddress = optionalCompany.get().getAddress();
            editAddress.setHome(companyDTO.getHome());
            editAddress.setStreet(companyDTO.getStreet());

            optionalCompany.get().setAddress(editAddress);

            companyRepository.save(optionalCompany.get());
            return new ApiResponse("Company updated!", true);
        }
        return new ApiResponse("Company not found!", false);
    }

    @Override
    public ApiResponse delete(Integer companyId) {
        Optional<Company> optionalCompany = companyRepository.findById(companyId);
        if (optionalCompany.isPresent()) {
            companyRepository.deleteById(companyId);

            return new ApiResponse("Company deleted!", true);
        }
        return new ApiResponse("Company not found!", false);
    }
}
