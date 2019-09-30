package project.khusainov.organization.dao;

import project.khusainov.organization.model.Organization;
import project.khusainov.organization.view.OrganizationByIdRespView;
import project.khusainov.organization.view.OrganizationListReqView;
import project.khusainov.organization.view.OrganizationListRespView;
import project.khusainov.organization.view.OrganizationUpdateReqView;

import java.util.List;

public interface OrganizationDao {
    List<OrganizationListRespView> getListByFilter(OrganizationListReqView organizationListReqView);
    OrganizationByIdRespView getOrganizationById(Long id);
    void save(Organization organization);
    void update(OrganizationUpdateReqView organizationUpdateReqView);
}
