package project.khusainov.handbook.country.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.khusainov.handbook.country.dao.CountryDao;
import project.khusainov.handbook.country.model.Country;
import project.khusainov.handbook.country.view.CountryView;
import project.khusainov.handbook.doc.view.DocView;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private CountryDao dao;

    @Autowired
    public CountryServiceImpl(CountryDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public Country getCountryByCode(Integer countryCode) {
        return dao.getCountryByCode(countryCode);
    }

    @Override
    @Transactional(readOnly = true)
    public Country getCountryByName(String countryName) {
        return dao.getCountryByName(countryName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryView> getList() {
        return dao.getList();
    }
}
