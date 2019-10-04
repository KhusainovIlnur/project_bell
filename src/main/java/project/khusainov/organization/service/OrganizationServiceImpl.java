package project.khusainov.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.khusainov.exception.NotFoundException;
import project.khusainov.organization.dao.OrganizationDao;
import project.khusainov.organization.model.Organization;
import project.khusainov.organization.view.OrganizationByIdRespView;
import project.khusainov.organization.view.OrganizationListReqView;
import project.khusainov.organization.view.OrganizationListRespView;
import project.khusainov.organization.view.OrganizationSaveReqView;
import project.khusainov.organization.view.OrganizationUpdateReqView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationListRespView> getOrganizationByFilter(OrganizationListReqView organizationListReqView) {
        return dao.getListByFilter(organizationListReqView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OrganizationByIdRespView getOrganizationById(Long id) {
        return dao.getOrganizationById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOrganization(OrganizationSaveReqView organizationSaveReqView) {
        Organization organization = new Organization
                (
                        organizationSaveReqView.name,
                        organizationSaveReqView.fullName,
                        organizationSaveReqView.inn,
                        organizationSaveReqView.kpp,
                        organizationSaveReqView.address,
                        organizationSaveReqView.phone,
                        organizationSaveReqView.isActive
                );

        dao.save(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOrganization(OrganizationUpdateReqView organizationUpdateReqView) {
        dao.update(organizationUpdateReqView);
    }
}
