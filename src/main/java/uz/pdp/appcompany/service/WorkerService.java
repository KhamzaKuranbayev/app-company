package uz.pdp.appcompany.service;

import org.springframework.stereotype.Service;
import uz.pdp.appcompany.entity.Worker;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.payload.WorkerDTO;

import java.util.List;

@Service
public interface WorkerService {

    ApiResponse save(WorkerDTO workerDTO);                      // CREATE

    List<Worker> findAll();                                    // READ

    Worker finOneById(Integer id);

    ApiResponse edit(WorkerDTO workerDTO, Integer id);          // UPDATE

    ApiResponse delete(Integer id);                              // DELETE
}
