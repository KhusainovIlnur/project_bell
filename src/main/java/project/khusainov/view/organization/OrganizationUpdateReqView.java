package project.khusainov.view.organization;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrganizationUpdateReqView {

    @NotNull(message = "name cannot be null")
    @Min(1)
    public Long id;

    @NotEmpty(message = "name cannot be null")
    @Size(min = 2, max = 50)
    public String name;

    @NotEmpty(message = "fullName cannot be null")
    @Size(min = 2, max = 100)
    public String fullName;

    @NotEmpty(message = "inn cannot be null")
    @Size(min = 10, max = 12)
    public String inn;

    @NotEmpty(message = "kpp cannot be null")
    @Size(min = 9, max = 9)
    public String kpp;

    @NotEmpty(message = "address cannot be null")
    @Size(min = 2, max = 100)
    public String address;

    @Size(min = 11, max = 18)
    public String phone;

    public Boolean isActive;
}
