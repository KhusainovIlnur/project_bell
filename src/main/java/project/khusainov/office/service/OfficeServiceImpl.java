package project.khusainov.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.khusainov.office.dao.OfficeDao;
import project.khusainov.office.model.Office;
import project.khusainov.office.view.OfficeByIdRespView;
import project.khusainov.office.view.OfficeListReqView;
import project.khusainov.office.view.OfficeListRespView;
import project.khusainov.office.view.OfficeSaveReqView;
import project.khusainov.office.view.OfficeUpdateReqView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeService {
    private OfficeDao dao;

    @Autowired
    public OfficeServiceImpl(OfficeDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OfficeListRespView> getOfficeByFilter(OfficeListReqView officeListReqView) {
        return dao.getListByFilter(officeListReqView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public OfficeByIdRespView getOfficeById(Long id) {
        return dao.getOfficeById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOffice(OfficeSaveReqView officeSaveReqView) {
        Office office = new Office
                (
                        officeSaveReqView.orgId,
                        officeSaveReqView.name,
                        officeSaveReqView.address,
                        officeSaveReqView.phone,
                        officeSaveReqView.isActive
                );

        dao.save(office);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOffice(OfficeUpdateReqView officeUpdateReqView) {
        dao.update(officeUpdateReqView);
    }
}
