package uz.pdp.appcompany.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Company;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.payload.CompanyDTO;
import uz.pdp.appcompany.payload.DepartmentDTO;

import java.util.List;

@Service
public interface DepartmentService {

    ApiResponse save(DepartmentDTO departmentDTO);                      // CREATE

    List<Department> findAll();                                    // READ

    Department finOneById(Integer id);

    ApiResponse edit(DepartmentDTO departmentDTO, Integer id);   // UPDATE

    ApiResponse delete(Integer id);                  // DELETE

}
