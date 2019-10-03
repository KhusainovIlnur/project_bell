package project.khusainov.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Version;
import java.util.List;

@Entity(name = "Document_type")
public class DocumentType {
    @Id
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "doc_code")
    private Integer docCode;

    @Column(name = "doc_name", length = 100)
    private String docName;

//    @OneToMany(mappedBy="documentType", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Document> document;


    public DocumentType() {
    }

    public DocumentType(Long id, Integer docCode, String docName) {
        this.id = id;
        this.docCode = docCode;
        this.docName = docName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDocCode() {
        return docCode;
    }

    public void setDocCode(Integer docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

/*    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }*/

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
