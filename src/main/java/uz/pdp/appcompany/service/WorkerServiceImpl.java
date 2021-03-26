package uz.pdp.appcompany.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Address;
import uz.pdp.appcompany.entity.Department;
import uz.pdp.appcompany.entity.Worker;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.payload.WorkerDTO;
import uz.pdp.appcompany.repository.AddressRepository;
import uz.pdp.appcompany.repository.DepartmentRepository;
import uz.pdp.appcompany.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {

    final WorkerRepository workerRepository;
    final DepartmentRepository departmentRepository;
    final AddressRepository addressRepository;

    public WorkerServiceImpl(WorkerRepository workerRepository,
                             DepartmentRepository departmentRepository,
                             AddressRepository addressRepository) {
        this.workerRepository = workerRepository;
        this.departmentRepository = departmentRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public ApiResponse save(WorkerDTO workerDTO) {
        Worker worker = new Worker();
        worker.setName(workerDTO.getName());
        worker.setPhone(workerDTO.getPhone());

        Address address = new Address();
        address.setHome(workerDTO.getHome());
        address.setStreet(workerDTO.getStreet());

        Address savedAddress = addressRepository.save(address);
        worker.setAddress(savedAddress);

        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getDepartmentId());
        if(!optionalDepartment.isPresent())
            return new ApiResponse("Not found department!", false);
        worker.setDepartment(optionalDepartment.get());

        workerRepository.save(worker);

        return new ApiResponse("Worker saved!", true);
    }

    @Override
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    @Override
    public Worker finOneById(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElse(null);
    }

    @Override
    public ApiResponse edit(WorkerDTO workerDTO, Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if(optionalWorker.isPresent()){
            optionalWorker.get().setName(workerDTO.getName());
            optionalWorker.get().setPhone(workerDTO.getPhone());

            Address editAddress = optionalWorker.get().getAddress();
            editAddress.setHome(workerDTO.getHome());
            editAddress.setStreet(workerDTO.getStreet());

            optionalWorker.get().setAddress(editAddress);

            workerRepository.save(optionalWorker.get());

            return new ApiResponse("Worker updated!", true);
        }
        return new ApiResponse("Worker not found!", false);
    }

    @Override
    public ApiResponse delete(Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if(optionalWorker.isPresent()){
            workerRepository.deleteById(id);
            return new ApiResponse("Worker deleted!", true);
        }
        return new ApiResponse("Worker not found!", false);
    }
}
