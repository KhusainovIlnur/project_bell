package project.khusainov.handbook.country.service;

import project.khusainov.user.model.Country;
import project.khusainov.user.model.DocumentType;

public interface CountryService {
    public Country getCountryByCode(Integer countryCode);
    public Country getCountryByName(String countryName);

}
