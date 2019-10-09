package project.khusainov.user.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.khusainov.exception.NotFoundException;
import project.khusainov.handbook.country.service.CountryService;
import project.khusainov.handbook.doc.service.DocService;
import project.khusainov.office.service.OfficeService;
import project.khusainov.handbook.country.model.Country;
import project.khusainov.handbook.doc.model.Document;
import project.khusainov.handbook.doc.model.DocumentType;
import project.khusainov.office.view.OfficeByIdRespView;
import project.khusainov.user.model.User;
import project.khusainov.user.service.UserService;
import project.khusainov.user.view.UserByIdRespView;
import project.khusainov.user.view.UserListReqView;
import project.khusainov.user.view.UserListRespView;
import project.khusainov.user.view.UserUpdateReqView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
public class UserDaoImpl implements UserDao {

    private final EntityManager em;

    @Autowired
    private DocService docService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private OfficeService officeService;

    public UserDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UserListRespView> getListByFilter(UserListReqView userListReqView) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UserListRespView> criteriaQuery = criteriaBuilder.createQuery(UserListRespView.class); // какого типа возвращаются
        Root<User> user = criteriaQuery.from(User.class); // откуда берем

        criteriaQuery.select(criteriaBuilder.construct(UserListRespView.class, user.get("id"), user.get("firstName"), user.get("secondName"), user.get("middleName"), user.get("position"))); // выборка определенных полей

        // построение динамического запроса
        List<Predicate> allPredicates = new ArrayList<>(); // список фильтров
        allPredicates.add(criteriaBuilder.equal(user.get("officeId"), userListReqView.officeId)); // обязательный фильтр

        if (userListReqView.firstName != null) {
            allPredicates.add(criteriaBuilder.equal(user.get("firstName"), userListReqView.firstName)); // необязательный фильтр
        }
        if (userListReqView.secondName != null) {
            allPredicates.add(criteriaBuilder.equal(user.get("secondName"), userListReqView.secondName)); // необязательный фильтр
        }
        if (userListReqView.middleName != null) {
            allPredicates.add(criteriaBuilder.equal(user.get("middleName"), userListReqView.middleName)); // необязательный фильтр
        }
        if (userListReqView.position != null) {
            allPredicates.add(criteriaBuilder.equal(user.get("position"), userListReqView.position)); // необязательный фильтр
        }

        criteriaQuery.where(
                criteriaBuilder.and(
                        allPredicates.toArray(new Predicate[0])
                )
        );

        TypedQuery<UserListRespView> query = em.createQuery(criteriaQuery);
        List<UserListRespView> userList = query.getResultList();

        return userList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserByIdRespView getUserById(Long id) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<UserByIdRespView> criteriaQuery = criteriaBuilder.createQuery(UserByIdRespView.class); // какого типа возвращаются
        Root<User> user = criteriaQuery.from(User.class); // откуда берем
        Root<Document> document = criteriaQuery.from(Document.class); // откуда берем
        Root<DocumentType> documentType = criteriaQuery.from(DocumentType.class); // откуда берем
        Root<Country> country = criteriaQuery.from(Country.class); // откуда берем

        criteriaQuery.multiselect(
                user.get("id"),
                user.get("firstName"),
                user.get("secondName"),
                user.get("middleName"),
                user.get("position"),
                user.get("phone"),
                documentType.get("docName"),
                document.get("docNumber"),
                document.get("docDate"),
                country.get("countryName"),
                country.get("countryCode"),
                user.get("isIdentified")
        );

        criteriaQuery.where(
                criteriaBuilder.equal(document.get("userId"), user.get("id")),
                criteriaBuilder.equal(document.get("documentType"), documentType.get("id")),
                criteriaBuilder.equal(country.get("id"), user.get("country")),
                criteriaBuilder.equal(user.get("id"), id)
        );

        TypedQuery<UserByIdRespView> query = em.createQuery(criteriaQuery);
        List<UserByIdRespView> userByIdRespView = query.getResultList();

        return userByIdRespView.size() > 0 ? userByIdRespView.get(0) : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(User user) {
        em.persist(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(UserUpdateReqView userUpdateReqView) {
        updateUser(userUpdateReqView);
        updateDocument(userUpdateReqView);
    }

    private void updateUser(UserUpdateReqView userUpdateReqView) {
        OfficeByIdRespView officeId;
        if (userUpdateReqView.officeId != null) {
            try {
                officeService.getOfficeById(userUpdateReqView.officeId);
            } catch (NotFoundException ex) {
                throw ex;
            }
            officeId = officeService.getOfficeById(userUpdateReqView.officeId);
        }
        else {
            officeId = null;
        }

        Country countryByCode = countryService.getCountryByCode(userUpdateReqView.citizenshipCode);
        if (userUpdateReqView.citizenshipCode != null && countryByCode == null) {
            throw new NotFoundException("Страна с таким кодом не найдена");
        }

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class); // что обновляем
        Root<User> user = criteriaUpdate.from(User.class); // откуда берем

        // изменение обязательных параметров запроа
        criteriaUpdate
                .set(user.get("firstName"),     userUpdateReqView.firstName)
                .set(user.get("position"),      userUpdateReqView.position);
        // необязательные параметры, изменяем только, если они указаны
        if (officeId != null) {
            criteriaUpdate.set(user.get("officeId"), userUpdateReqView.officeId);
        }
        if (userUpdateReqView.secondName != null) {
            criteriaUpdate.set(user.get("secondName"), userUpdateReqView.secondName);
        }
        if (userUpdateReqView.middleName != null) {
            criteriaUpdate.set(user.get("middleName"), userUpdateReqView.middleName);
        }
        if (userUpdateReqView.phone != null) {
            criteriaUpdate.set(user.get("phone"), userUpdateReqView.phone);
        }
        if (countryByCode != null) {
            criteriaUpdate.set(user.get("country"), countryByCode);
        }
        if (userUpdateReqView.isIdentified != null){
            criteriaUpdate.set(user.get("isIdentified"), userUpdateReqView.isIdentified);
        }

        criteriaUpdate.where(
                criteriaBuilder.equal(user.get("id"), userUpdateReqView.id)
        );

        em.createQuery(criteriaUpdate).executeUpdate();
    }

    private void updateDocument(UserUpdateReqView userUpdateReqView) {
        DocumentType documentType = docService.getDocByName(userUpdateReqView.docName);

        if (userUpdateReqView.docName != null && documentType == null) {
            throw new NotFoundException("Документ с таким именем не найден");
        }

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<Document> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Document.class); // что обновляем
        Root<Document> document = criteriaUpdate.from(Document.class); // откуда берем

        // необязательные параметры, изменяем только, если они указаны
        if (documentType != null) {
            criteriaUpdate.set(document.get("documentType"), documentType);
        }
        if (userUpdateReqView.docNumber != null) {
            criteriaUpdate.set(document.get("docNumber"), userUpdateReqView.docNumber);
        }
        if (userUpdateReqView.docDate != null) {
            criteriaUpdate.set(document.get("docDate"), userUpdateReqView.docDate);
        }

        criteriaUpdate.where(
                criteriaBuilder.equal(document.get("userId"), userUpdateReqView.id)
        );

        em.createQuery(criteriaUpdate).executeUpdate();
    }
}
