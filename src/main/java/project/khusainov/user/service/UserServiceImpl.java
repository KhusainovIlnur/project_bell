package project.khusainov.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.khusainov.exception.NotFoundException;
import project.khusainov.office.service.OfficeService;
import project.khusainov.user.dao.UserDao;
import project.khusainov.user.model.Country;
import project.khusainov.user.model.Document;
import project.khusainov.user.model.DocumentType;
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
    private UserDao dao;

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

        DocumentType documentType = new DocumentType();
        documentType.setDocCode(userSaveReqView.docCode);
        documentType.setDocName(userSaveReqView.docName);

        Document document = new Document();
        document.setDocNumber(userSaveReqView.docNumber);
        document.setDocDate(userSaveReqView.docDate);
        document.setDocumentType(documentType);

        documentType.setDocument(document);

        Country country = new Country();
        country.setCountryCode(userSaveReqView.citizenshipCode);

        User user = new User();

        user.setOfficeId(userSaveReqView.officeId);
        user.setFirstName(userSaveReqView.firstName);
        user.setSecondName(userSaveReqView.secondName);
        user.setMiddleName(userSaveReqView.secondName);
        user.setPosition(userSaveReqView.position);
        user.setPhone(userSaveReqView.phone);
        user.setIdentified(userSaveReqView.isIdentified);

        user.setDocument(document);
        user.setCountry(country);

        document.setUser(user);
        country.setUser(user);

        dao.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateUser(UserUpdateReqView userUpdateReqView) {
        dao.update(userUpdateReqView);
    }
}