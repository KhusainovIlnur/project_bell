package project.khusainov.organization.view;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class OrganizationListReqView {

    @NotEmpty(message = "name cannot be null")
    @Size(min = 2, max = 50)
    public String name;

    @Size(min = 10, max = 12)
    public String inn;

    public Boolean isActive;
}