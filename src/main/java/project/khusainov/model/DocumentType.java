package project.khusainov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity(name = "Document_type")
public class DocumentType {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Version
    private Integer version;

    @Column(name = "doc_code")
    private Integer docCode;

    @Column(name = "doc_name", length = 100)
    private String docName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

}
