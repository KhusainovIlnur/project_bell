package project.khusainov.handbook.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.khusainov.handbook.country.dao.CountryDao;
import project.khusainov.user.model.Country;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDao dao;

    @Autowired
    public CountryServiceImpl(CountryDao dao) {
        this.dao = dao;
    }

    @Override
    public Country getCountryByCode(Integer countryCode) {
        return dao.getCountryByCode(countryCode);
    }

    @Override
    public Country getCountryByName(String countryName) {
        return dao.getCountryByName(countryName);
    }
}
