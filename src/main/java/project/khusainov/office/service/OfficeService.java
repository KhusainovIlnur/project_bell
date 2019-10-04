package project.khusainov.office.service;

import project.khusainov.office.model.Office;
import project.khusainov.office.view.OfficeByIdRespView;
import project.khusainov.office.view.OfficeListReqView;
import project.khusainov.office.view.OfficeListRespView;
import project.khusainov.office.view.OfficeSaveReqView;
import project.khusainov.office.view.OfficeUpdateReqView;

import java.util.List;

public interface OfficeService {

    /**
     * Получить список офисов организации по фильтру
     * @param organizationListReqView
     * @return
     */
    public List<OfficeListRespView> getOfficeByFilter(OfficeListReqView organizationListReqView);

    /**
     * Получить офис организации по id
     * @param id
     * @return
     */
    public OfficeByIdRespView getOfficeById(Long id);

    /**
     * Добавить офис
     * @param organizationSaveReqView
     */
    public void saveOffice(OfficeSaveReqView organizationSaveReqView);

    /**
     * Обновить офис
     * @param organizationUpdateReqView
     */
    public void updateOffice(OfficeUpdateReqView organizationUpdateReqView);
}
