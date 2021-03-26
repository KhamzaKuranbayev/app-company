package uz.pdp.appcompany.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.payload.DepartmentDTO;
import uz.pdp.appcompany.repository.CompanyRepository;
import uz.pdp.appcompany.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentImpl implements DepartmentService {

    final DepartmentRepository departmentRepository;
    final CompanyRepository companyRepository;

    public DepartmentImpl(DepartmentRepository departmentRepository,
                          CompanyRepository companyRepository) {
        this.departmentRepository = departmentRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public ApiResponse save(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setName(departmentDTO.getName());

        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
        if (!optionalCompany.isPresent())
             return new ApiResponse("Company not found!", false);
        department.setCompany(optionalCompany.get());

        departmentRepository.save(department);
        return new ApiResponse("Department saved!", true);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department finOneById(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElse(null);
    }

    @Override
    public ApiResponse edit(DepartmentDTO departmentDTO, Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(optionalDepartment.isPresent()){
            optionalDepartment.get().setName(departmentDTO.getName());

            Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
            if (!optionalCompany.isPresent())
                return new ApiResponse("Company not found!", false);
        }
        return new ApiResponse("Department not found!", false);
    }

    @Override
    public ApiResponse delete(Integer id) {
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if(optionalDepartment.isPresent()){
            departmentRepository.deleteById(id);
            return new ApiResponse("Department deleted!", true);
        }
        return new ApiResponse("Department not found!", false);
    }
}
