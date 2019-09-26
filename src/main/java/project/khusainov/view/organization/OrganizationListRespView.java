package project.khusainov.view.organization;

import com.fasterxml.jackson.annotation.JsonRootName;
import project.khusainov.view.ResponseWrapperView;

@JsonRootName(value = "ResponseWrapperView")
public class OrganizationListRespView extends ResponseWrapperView {
    public Long id;
    public String name;
    public Boolean isActive;

    public OrganizationListRespView(Long id, String name, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
    }
}