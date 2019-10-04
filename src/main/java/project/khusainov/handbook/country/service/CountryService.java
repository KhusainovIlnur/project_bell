package project.khusainov.handbook.country.service;

import project.khusainov.handbook.country.model.Country;
import project.khusainov.handbook.country.view.CountryView;

import java.util.List;

public interface CountryService {
    public Country getCountryByCode(Integer countryCode);
    public Country getCountryByName(String countryName);
    public List<CountryView> getList();
}
