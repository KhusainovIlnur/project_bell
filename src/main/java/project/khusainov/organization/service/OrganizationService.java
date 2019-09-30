package project.khusainov.organization.service;

import project.khusainov.organization.view.OrganizationByIdRespView;
import project.khusainov.organization.view.OrganizationListReqView;
import project.khusainov.organization.view.OrganizationListRespView;
import project.khusainov.organization.view.OrganizationSaveReqView;
import project.khusainov.organization.view.OrganizationUpdateReqView;

import java.util.List;

public interface OrganizationService {
    List<OrganizationListRespView> getOrganizationByFilter(OrganizationListReqView organizationListReqView);
    OrganizationByIdRespView getOrganizationById(Long id);
    void saveOrganization(OrganizationSaveReqView organizationSaveReqView);
    void updateOrganization(OrganizationUpdateReqView organizationUpdateReqView);
}
