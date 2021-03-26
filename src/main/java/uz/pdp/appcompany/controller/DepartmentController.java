package uz.pdp.appcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.payload.DepartmentDTO;
import uz.pdp.appcompany.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping()
public class DepartmentController {

    final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public HttpEntity<?> save(@RequestBody DepartmentDTO departmentDTO) {
        ApiResponse response = departmentService.save(departmentDTO);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @GetMapping
    public HttpEntity<List<Department>> findAll() {
        List<Department> departmentList = departmentService.findAll();
        return ResponseEntity.ok(departmentList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findOneById(@PathVariable Integer id) {
        Department department = departmentService.finOneById(id);
        if (department == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Department not found!", false));
        return ResponseEntity.ok(department);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@RequestBody DepartmentDTO departmentDTO, @PathVariable Integer id) {
        ApiResponse response = departmentService.edit(departmentDTO, id);
        return ResponseEntity.status(response.isStatus()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse response = departmentService.delete(id);
        return ResponseEntity.status(response.isStatus()? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }
}
