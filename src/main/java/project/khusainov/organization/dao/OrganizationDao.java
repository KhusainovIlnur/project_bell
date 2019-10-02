package project.khusainov.organization.dao;

import project.khusainov.organization.model.Organization;
import project.khusainov.organization.view.OrganizationByIdRespView;
import project.khusainov.organization.view.OrganizationListReqView;
import project.khusainov.organization.view.OrganizationListRespView;
import project.khusainov.organization.view.OrganizationUpdateReqView;

import java.util.List;

/**
 * DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получить список организаций по фильтру
     * @param organizationListReqView
     * @return
     */
    public List<OrganizationListRespView> getListByFilter(OrganizationListReqView organizationListReqView);

    /**
     * Получить организацию по id
     * @param id
     * @return
     */
    public OrganizationByIdRespView getOrganizationById(Long id);

    /**
     * Добавить организацию
     * @param organization
     */
    public void save(Organization organization);

    /**
     * Обновить организацию
     * @param organizationUpdateReqView
     */
    public void update(OrganizationUpdateReqView organizationUpdateReqView);
}
