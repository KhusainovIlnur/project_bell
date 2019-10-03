package project.khusainov.handbook.doc.service;

import project.khusainov.handbook.doc.view.DocView;
import project.khusainov.user.model.DocumentType;

public interface DocService {
    public DocumentType getDocByCode(Integer docCode);
    public DocumentType getDocByName(String docName);

}
