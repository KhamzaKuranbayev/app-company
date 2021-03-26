package uz.pdp.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
