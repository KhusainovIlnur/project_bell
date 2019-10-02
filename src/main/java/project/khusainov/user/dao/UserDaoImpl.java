package project.khusainov.user.dao;

import org.springframework.stereotype.Repository;
import project.khusainov.user.model.Country;
import project.khusainov.user.model.Document;
import project.khusainov.user.model.DocumentType;
import project.khusainov.user.model.User;
import project.khusainov.user.view.UserByIdRespView;
import project.khusainov.user.view.UserListReqView;
import project.khusainov.user.view.UserListRespView;
import project.khusainov.user.view.UserUpdateReqView;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Join;
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
                criteriaBuilder.equal(country.get("userId"), user.get("id")),
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
//        updateDocumentType(userUpdateReqView);
        updateCountry(userUpdateReqView);
    }

    private void updateUser(UserUpdateReqView userUpdateReqView) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class); // что обновляем
        Root<User> user = criteriaUpdate.from(User.class); // откуда берем

        criteriaUpdate
                .set(user.get("officeId"),      userUpdateReqView.officeId)
                .set(user.get("firstName"),     userUpdateReqView.firstName)
                .set(user.get("secondName"),    userUpdateReqView.secondName)
                .set(user.get("middleName"),    userUpdateReqView.middleName)
                .set(user.get("position"),      userUpdateReqView.position)
                .set(user.get("phone"),         userUpdateReqView.phone)
//                .set(user.get("documentType"),  3)
                .set(user.get("isIdentified"),  userUpdateReqView.isIdentified);

        criteriaUpdate
                .where(criteriaBuilder.equal(user.get("id"), userUpdateReqView.id)
                );

        em.createQuery(criteriaUpdate).executeUpdate();
    }

    private void updateDocument(UserUpdateReqView userUpdateReqView) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<Document> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Document.class); // что обновляем
        Root<Document> document = criteriaUpdate.from(Document.class); // откуда берем

        criteriaUpdate
                .set(document.get("docNumber"), userUpdateReqView.docNumber)
                .set(document.get("docDate"),   userUpdateReqView.docDate);

        criteriaUpdate
                .where(criteriaBuilder.equal(document.get("userId"), userUpdateReqView.id)
                );

        em.createQuery(criteriaUpdate).executeUpdate();
    }

    private void updateDocumentType(UserUpdateReqView userUpdateReqView) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<DocumentType> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(DocumentType.class); // что обновляем
        Root<DocumentType> documentType = criteriaUpdate.from(DocumentType.class); // откуда берем

        criteriaUpdate
                .set(documentType.get("docName"),  userUpdateReqView.docName);

        criteriaUpdate
                .where(criteriaBuilder.equal(documentType.get("id"), userUpdateReqView.id)
                );

        em.createQuery(criteriaUpdate).executeUpdate();
    }

    private void updateCountry(UserUpdateReqView userUpdateReqView) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaUpdate<Country> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Country.class); // что обновляем
        Root<Country> country = criteriaUpdate.from(Country.class); // откуда берем

        criteriaUpdate
                .set(country.get("countryCode"),  userUpdateReqView.citizenshipCode);

        criteriaUpdate
                .where(criteriaBuilder.equal(country.get("userId"), userUpdateReqView.id)
                );

        em.createQuery(criteriaUpdate).executeUpdate();
    }
}
