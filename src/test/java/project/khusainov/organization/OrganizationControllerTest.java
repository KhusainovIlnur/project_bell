package project.khusainov.organization;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import project.khusainov.organization.view.OrganizationListReqView;
import project.khusainov.organization.view.OrganizationUpdateReqView;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тестирование OrganizationController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrganizationControllerTest {
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    /**
     * Тестирование получения организаций по различным параметрам
     * @throws Exception
     */
    @Test
    public void getList() throws Exception {
        // получение организации по одному параметру
        OrganizationListReqView testReq1 = new OrganizationListReqView();
        testReq1.name = "Билайн";
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("Билайн"))
                .andExpect(jsonPath("$.data[0].isActive").value(true));

        // получение организации по нескольким параметрам
        OrganizationListReqView testReq2 = new OrganizationListReqView();
        testReq2.name = "МТС";
        testReq2.isActive = false;
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("МТС"))
                .andExpect(jsonPath("$.data[0].isActive").value(false));

        // получение пустого результата
        OrganizationListReqView testReq3 = new OrganizationListReqView();
        testReq3.name = "МТС55";
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isEmpty());

        // неккоректный запрос
        OrganizationListReqView testReq4 = new OrganizationListReqView();
        testReq4.name = "МТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТСМТС";
        mockMvc.perform(post("/api/organization/list").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq4)))
                .andExpect(status().isBadRequest());
    }

    /**
     * Тестирование получения организаций по id
     * @throws Exception
     */
    @Test
    public void getOrganizationById() throws Exception {
        // получение существующей организации по id
        mockMvc.perform(get("/api/organization/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Мегафон"))
                .andExpect(jsonPath("$.data.inn").value("7812014560"));

        // получение несуществующей организации по id
        mockMvc.perform(get("/api/organization/9"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Организация с таким id не найдена"));
    }

    /**
     * Тестирование обновления организации
     * @throws Exception
     */
    @Test
    public void updateOrganization() throws Exception {
        // обновление организации
        OrganizationUpdateReqView testReq = new OrganizationUpdateReqView();
        testReq.id = 1L;
        testReq.name = "МТС";
        testReq.fullName = "Публичное акционерное общество МТС";
        testReq.inn = "1458794763";
        testReq.kpp = "879542163";
        testReq.address = "г. Москва....";
        mockMvc.perform(post("/api/organization/update").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));

        // получение существующей организации по id
        mockMvc.perform(get("/api/organization/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("МТС"))
                .andExpect(jsonPath("$.data.fullName").value("Публичное акционерное общество МТС"))
                .andExpect(jsonPath("$.data.inn").value("1458794763"))
                .andExpect(jsonPath("$.data.kpp").value("879542163"))
                .andExpect(jsonPath("$.data.address").value("г. Москва...."))
                .andExpect(jsonPath("$.data.isActive").value(true));
    }

    /**
     * Тестирование создания организации
     * @throws Exception
     */
    @Test
    public void saveOrganization() throws Exception {
        // добавление организации
        OrganizationUpdateReqView testReq = new OrganizationUpdateReqView();
        testReq.name = "SMART";
        testReq.fullName = "ПАО SMART";
        testReq.inn = "1458794763";
        testReq.kpp = "879542163";
        testReq.address = "г. Москва....";
        testReq.isActive = false;
        mockMvc.perform(post("/api/organization/save").contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(testReq)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));

        // получение существующей организации по id
        mockMvc.perform(get("/api/organization/6"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("SMART"))
                .andExpect(jsonPath("$.data.fullName").value("ПАО SMART"))
                .andExpect(jsonPath("$.data.inn").value("1458794763"))
                .andExpect(jsonPath("$.data.kpp").value("879542163"))
                .andExpect(jsonPath("$.data.address").value("г. Москва...."))
                .andExpect(jsonPath("$.data.phone").isEmpty())
                .andExpect(jsonPath("$.data.isActive").value(false));
    }
}
