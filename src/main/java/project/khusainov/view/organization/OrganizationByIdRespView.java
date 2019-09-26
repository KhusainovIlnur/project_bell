package project.khusainov.view.organization;

public class OrganizationByIdRespView {
    public Long id;
    public String name;
    public String fullName;
    public String inn;
    public String kpp;
    public String address;
    public String phone;
    public Boolean isActive;

    public OrganizationByIdRespView(Long id, String name, String fullName, String inn, String kpp, String address, String phone, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
    }

}
