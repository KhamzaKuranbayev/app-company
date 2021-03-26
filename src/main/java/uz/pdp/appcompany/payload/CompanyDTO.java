package uz.pdp.appcompany.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {

    @NotNull(message = "Company name can not be empty")
    private String corpName;

    @NotNull(message = "Director name can not be empty")
    private String directorName;

    private String street;

    private String home;
}
