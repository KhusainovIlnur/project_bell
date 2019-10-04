package project.khusainov.handbook.doc.dao;

import project.khusainov.handbook.doc.model.DocumentType;
import project.khusainov.handbook.doc.view.DocView;

import java.util.List;

public interface DocDao {
    public DocumentType getDocByCode(Integer docCode);
    public DocumentType getDocByName(String docName);
    public List<DocView> getList();
}

