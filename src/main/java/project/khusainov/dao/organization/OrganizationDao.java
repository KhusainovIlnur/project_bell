package project.khusainov.dao.organization;

import project.khusainov.model.Organization;
import project.khusainov.view.ResponseWrapperView;
import project.khusainov.view.organization.OrganizationByIdRespView;
import project.khusainov.view.organization.OrganizationListReqView;
import project.khusainov.view.organization.OrganizationListRespView;
import project.khusainov.view.organization.OrganizationUpdateReqView;

import java.sql.SQLException;
import java.util.List;

public interface OrganizationDao {
    List<ResponseWrapperView> getListByFilter(OrganizationListReqView organizationListReqView);
    OrganizationByIdRespView getOrganizationById(Long id);
    void save(Organization organization);
    String update(OrganizationUpdateReqView organizationUpdateReqView);
}
