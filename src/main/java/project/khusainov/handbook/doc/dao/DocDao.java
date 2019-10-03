package project.khusainov.handbook.doc.dao;

import project.khusainov.handbook.doc.view.DocView;
import project.khusainov.user.model.DocumentType;

public interface DocDao {
    public DocumentType getDocByCode(Integer docCode);
    public DocumentType getDocByName(String docName);
}
