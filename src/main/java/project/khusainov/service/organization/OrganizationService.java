package project.khusainov.service.organization;

import project.khusainov.view.ResponseWrapperView;
import project.khusainov.view.organization.OrganizationByIdRespView;
import project.khusainov.view.organization.OrganizationListReqView;
import project.khusainov.view.organization.OrganizationListRespView;
import project.khusainov.view.organization.OrganizationSaveReqView;
import project.khusainov.view.organization.OrganizationUpdateReqView;

import java.util.List;

public interface OrganizationService {
    List<ResponseWrapperView> getOrganizationByFilter(OrganizationListReqView organizationListReqView);
    OrganizationByIdRespView getOrganizationById(Long id);
    void saveOrganization(OrganizationSaveReqView organizationSaveReqView);
    String updateOrganization(OrganizationUpdateReqView organizationUpdateReqView);
}
