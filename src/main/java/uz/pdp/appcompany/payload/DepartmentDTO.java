package uz.pdp.appcompany.payload;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class DepartmentDTO {

    @NotNull(message = "Department name can not be empty")
    private String name;

    @NotNull(message = "Company Id can not be empty")
    private Integer companyId;

}
