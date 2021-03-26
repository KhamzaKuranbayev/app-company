package uz.pdp.appcompany.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appcompany.constants.FrontURLs;
import uz.pdp.appcompany.entity.Worker;
import uz.pdp.appcompany.payload.ApiResponse;
import uz.pdp.appcompany.payload.WorkerDTO;
import uz.pdp.appcompany.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping(FrontURLs.WORKER)
public class WorkerController {

    final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PostMapping
    public HttpEntity<ApiResponse> save(@RequestBody WorkerDTO workerDTO) {
        ApiResponse response = workerService.save(workerDTO);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping
    public HttpEntity<List<Worker>> findAll() {
        List<Worker> workerList = workerService.findAll();
        return ResponseEntity.ok(workerList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> findOneById(@PathVariable Integer id) {
        Worker worker = workerService.finOneById(id);
        if (worker == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Not found", false));
        return ResponseEntity.ok(worker);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@RequestBody WorkerDTO workerDTO, @PathVariable Integer id) {
        ApiResponse response = workerService.edit(workerDTO, id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> delete(@PathVariable Integer id) {
        ApiResponse response = workerService.delete(id);
        return ResponseEntity.status(response.isStatus() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(response);
    }
}
