package project.khusainov.handbook.country.dao;

import project.khusainov.handbook.country.model.Country;
import project.khusainov.handbook.country.view.CountryView;

import java.util.List;

public interface CountryDao {
    public Country getCountryByCode(Integer countryCode);
    public Country getCountryByName(String countryName);
    public List<CountryView> getList();

    }
