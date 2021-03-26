package uz.pdp.appcompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appcompany.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    boolean existsByStreetAndHome(String street, String home);
}
