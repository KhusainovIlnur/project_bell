package project.khusainov.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.khusainov.exception.NotFoundException;
import project.khusainov.handbook.country.model.Country;
import project.khusainov.handbook.country.service.CountryService;
import project.khusainov.handbook.doc.model.Document;
import project.khusainov.handbook.doc.model.DocumentType;
import project.khusainov.handbook.doc.service.DocService;
import project.khusainov.office.service.OfficeService;
import project.khusainov.user.dao.UserDao;
import project.khusainov.user.model.User;
import project.khusainov.user.view.UserByIdRespView;
import project.khusainov.user.view.UserListReqView;
import project.khusainov.user.view.UserListRespView;
import project.khusainov.user.view.UserSaveReqView;
import project.khusainov.user.view.UserUpdateReqView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserDao dao;

    @Autowired
    private DocService docService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private OfficeService officeService;


    @Autowired
    public UserServiceImpl(UserDao dao) {
        this.dao = dao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserListRespView> getUserByFilter(UserListReqView userListReqView) {
        return dao.getListByFilter(userListReqView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserByIdRespView getUserById(Long id) {
        return dao.getUserById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveUser(UserSaveReqView userSaveReqView) {
        if (officeService.getOfficeById(userSaveReqView.officeId) == null) {
            throw new NotFoundException("Офис с таким id не найден");
        }
        DocumentType dtByCode = docService.getDocByCode(userSaveReqView.docCode);
        DocumentType dtByName = docService.getDocByName(userSaveReqView.docName);

        if (userSaveReqView.docCode != null && dtByCode == null) {
            throw new NotFoundException("Документ с таким кодом не найден");
        }
        if (userSaveReqView.docName != null && dtByName == null) {
            throw new NotFoundException("Документ с таким именем не найден");
        }

        Country countryByCode = countryService.getCountryByCode(userSaveReqView.citizenshipCode);
        if (userSaveReqView.citizenshipCode != null && countryByCode == null) {
            throw new NotFoundException("Страна с таким кодом не найдена");
        }

        Document document = new Document();
        document.setDocNumber(userSaveReqView.docNumber);
        document.setDocDate(userSaveReqView.docDate);

        // установка документа по коду, имени или ни один параметр не указан
        if (dtByCode != null) {
            document.setDocumentType(dtByCode);
        }
        else if (dtByName != null) {
            document.setDocumentType(dtByName);
        }
        else {
            document.setDocumentType(null);
        }

        User user = new User();

        user.setOfficeId(officeService.getOfficeById(userSaveReqView.officeId).id);
        user.setFirstName(userSaveReqView.firstName);
        user.setSecondName(userSaveReqView.secondName);
        user.setMiddleName(userSaveReqView.middleName);
        user.setPosition(userSaveReqView.position);
        user.setPhone(userSaveReqView.phone);
        user.setDocument(document);
        user.setCountry(countryByCode);
        user.setIdentified(userSaveReqView.isIdentified);

        dao.save(user);
        LOGGER.debug("Новый пользователь добавлен");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateUser(UserUpdateReqView userUpdateReqView) {
        LOGGER.debug("Пользователь с id={} изменен", userUpdateReqView.id);
        dao.update(userUpdateReqView);
    }
}