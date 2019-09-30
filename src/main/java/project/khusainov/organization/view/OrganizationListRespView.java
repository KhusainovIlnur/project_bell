package project.khusainov.organization.view;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ResponseWrapperView")
public class OrganizationListRespView {
    public Long id;
    public String name;
    public Boolean isActive;

    public OrganizationListRespView(Long id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }
}