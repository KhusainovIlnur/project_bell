package project.khusainov.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Version;

@Entity(name = "Country")
public class Country {
    @Id
    @Column(name = "id")
    private Long id;

    @Version
    private Integer version;

    @Column(name = "country_code")
    private Integer countryCode;

    @Column(name = "country_name", length = 50)
    private String countryName;

    public Country() {
    }

    public Country(Long id, Integer countryCode, String countryName) {
        this.id = id;
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

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

    public Integer getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Integer countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
