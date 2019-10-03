package project.khusainov.handbook.country.dao;

import project.khusainov.user.model.Country;

public interface CountryDao {
    public Country getCountryByCode(Integer countryCode);
    public Country getCountryByName(String countryName);
}
