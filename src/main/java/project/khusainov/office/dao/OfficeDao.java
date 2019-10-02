package project.khusainov.office.dao;

import project.khusainov.office.model.Office;
import project.khusainov.office.view.OfficeByIdRespView;
import project.khusainov.office.view.OfficeListReqView;
import project.khusainov.office.view.OfficeListRespView;
import project.khusainov.office.view.OfficeUpdateReqView;


import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {
    /**
     * Получить список офисов организаци по фильтру
     * @param officeListReqView
     * @return
     */
    public List<OfficeListRespView> getListByFilter(OfficeListReqView officeListReqView);

    /**
     * Получить офис организации по id
     * @param id
     * @return
     */
    public OfficeByIdRespView getOfficeById(Long id);

    /**
     * Добавить офис
     * @param office
     */
    public void save(Office office);

    /**
     * Обновить офис
     * @param officeUpdateReqView
     */
    public void update(OfficeUpdateReqView officeUpdateReqView);
}
