package project.khusainov.service.organization;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;
import project.khusainov.dao.organization.OrganizationDao;
import project.khusainov.model.Organization;
import project.khusainov.view.ResponseWrapperView;
import project.khusainov.view.organization.OrganizationByIdRespView;
import project.khusainov.view.organization.OrganizationListReqView;
import project.khusainov.view.organization.OrganizationListRespView;
import project.khusainov.view.organization.OrganizationSaveReqView;
import project.khusainov.view.organization.OrganizationUpdateReqView;

import java.sql.SQLException;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    private OrganizationDao dao;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao) {
        this.dao = dao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseWrapperView> getOrganizationByFilter(OrganizationListReqView organizationListReqView) {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
//        String result = mapper.writeValueAsString(user);
        ObjectMapper mapper = new ObjectMapper();
        List<ResponseWrapperView> res = dao.getListByFilter(organizationListReqView);
//        String rootName = ResponseWrapperView.class.getAnnotation(JsonRootName.class).value();
//        String r = "";
//        try {
//            r = mapper.writer().withRootName(rootName).writeValueAsString(res);
//        }
//        catch (JsonProcessingException ex) {
//
//        }
        return res;
    }

    @Override
    @Transactional(readOnly = true)
    public OrganizationByIdRespView getOrganizationById(Long id) {
        return dao.getOrganizationById(id);
    }

    @Override
    @Transactional
    public void saveOrganization(OrganizationSaveReqView organizationSaveReqView) {
        Organization organization = new Organization
                (
                        organizationSaveReqView.name,
                        organizationSaveReqView.fullName,
                        organizationSaveReqView.inn,
                        organizationSaveReqView.kpp,
                        organizationSaveReqView.address,
                        organizationSaveReqView.phone,
                        organizationSaveReqView.isActive
                );

        dao.save(organization);
    }

    @Override
    @Transactional
    public String updateOrganization(OrganizationUpdateReqView organizationUpdateReqView) {
        return dao.update(organizationUpdateReqView);
    }
}
