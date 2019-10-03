package project.khusainov.handbook.doc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.khusainov.handbook.doc.dao.DocDao;
import project.khusainov.handbook.doc.view.DocView;
import project.khusainov.user.model.DocumentType;

@Service
public class DocServiceImpl implements DocService {

    private DocDao dao;

    @Autowired
    public DocServiceImpl(DocDao dao) {
        this.dao = dao;
    }

    @Override
    public DocumentType getDocByCode(Integer docCode) {
        return dao.getDocByCode(docCode);
    }

    @Override
    public DocumentType getDocByName(String docName) {
        return dao.getDocByName(docName);
    }
}
