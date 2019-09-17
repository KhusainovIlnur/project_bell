package project.khusainov.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import java.util.List;

@Entity(name = "Organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;

    @Column(name = "inn", nullable = false)
    private long inn;

    @Column(name = "kpp", nullable = false)
    private long kpp;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "phone", length = 18)
    private String phone;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "organization_id")
    private List<Office> offices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public long getInn() {
        return inn;
    }

    public void setInn(long inn) {
        this.inn = inn;
    }

    public long getKpp() {
        return kpp;
    }

    public void setKpp(long kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
