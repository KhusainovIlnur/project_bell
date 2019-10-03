package project.khusainov.handbook.doc.dao;

import org.springframework.stereotype.Repository;
import project.khusainov.user.model.DocumentType;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DocDaoImpl implements DocDao {

    private final EntityManager em;

    public DocDaoImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public DocumentType getDocByCode(Integer docCode) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocumentType> criteriaQuery = criteriaBuilder.createQuery(DocumentType.class); // какого типа возвращаются
        Root<DocumentType> documentType = criteriaQuery.from(DocumentType.class); // откуда берем

        criteriaQuery.where(
                criteriaBuilder.equal(documentType.get("docCode"), docCode)
        );

        criteriaQuery.select(documentType);

        TypedQuery<DocumentType> query = em.createQuery(criteriaQuery);
        List<DocumentType> documentTypes = query.getResultList();

        return documentTypes.size() > 0 ? documentTypes.get(0) : null;
    }

    @Override
    public DocumentType getDocByName(String docName) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<DocumentType> criteriaQuery = criteriaBuilder.createQuery(DocumentType.class); // какого типа возвращаются
        Root<DocumentType> documentType = criteriaQuery.from(DocumentType.class); // откуда берем

        criteriaQuery.where(
                criteriaBuilder.equal(documentType.get("docName"), docName)
        );

        criteriaQuery.select(documentType);

        TypedQuery<DocumentType> query = em.createQuery(criteriaQuery);
        List<DocumentType> documentTypes = query.getResultList();

        return documentTypes.size() > 0 ? documentTypes.get(0) : null;
    }
}
