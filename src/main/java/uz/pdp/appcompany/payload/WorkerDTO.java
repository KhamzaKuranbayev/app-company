package uz.pdp.appcompany.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class WorkerDTO {

    @NotNull(message = "Worker name can not be empty")
    private String name;

    @NotNull(message = "Phone number can not be empty")
    private String phone;

    private String street;

    private String home;

    @NotNull(message = "Department Id can not be empty")
    private Integer departmentId;
}
