package project.khusainov.handbook.doc.service;

import project.khusainov.handbook.doc.model.DocumentType;
import project.khusainov.handbook.doc.view.DocView;

import java.util.List;

public interface DocService {
    public DocumentType getDocByCode(Integer docCode);
    public DocumentType getDocByName(String docName);
    public List<DocView> getList();
}
