package project.khusainov.organization.service;

import project.khusainov.organization.view.OrganizationByIdRespView;
import project.khusainov.organization.view.OrganizationListReqView;
import project.khusainov.organization.view.OrganizationListRespView;
import project.khusainov.organization.view.OrganizationSaveReqView;
import project.khusainov.organization.view.OrganizationUpdateReqView;

import java.util.List;

public interface OrganizationService {

    /**
     * Получить список организаций по фильтру
     * @param organizationListReqView
     * @return
     */
    public List<OrganizationListRespView> getOrganizationByFilter(OrganizationListReqView organizationListReqView);

    /**
     * Получить организацию по id
     * @param id
     * @return
     */
    public OrganizationByIdRespView getOrganizationById(Long id);

    /**
     * Добавить организацию
     * @param organizationSaveReqView
     */
    public void saveOrganization(OrganizationSaveReqView organizationSaveReqView);

    /**
     * Обновить организацию
     * @param organizationUpdateReqView
     */
    public void updateOrganization(OrganizationUpdateReqView organizationUpdateReqView);
}
