package project.khusainov.controller.organization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.khusainov.exception.BadRequestException;
import project.khusainov.exception.MyTestException;
import project.khusainov.service.organization.OrganizationService;
import project.khusainov.view.ResponseWrapperView;
import project.khusainov.view.organization.OrganizationByIdRespView;
import project.khusainov.view.organization.OrganizationListReqView;
import project.khusainov.view.organization.OrganizationListRespView;
import project.khusainov.view.organization.OrganizationSaveReqView;
import project.khusainov.view.organization.OrganizationUpdateReqView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/list")

    public List<ResponseWrapperView> getList(@Valid @RequestBody OrganizationListReqView organizationListReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            return organizationService.getOrganizationByFilter(organizationListReqView);
        }
    }

    @GetMapping("/{id}")
    public OrganizationByIdRespView getById(@PathVariable String id) {
        Long convertToLong;
        try {
            convertToLong = Long.valueOf(id);
        }
        catch (NumberFormatException e) {
            throw new BadRequestException("id must be long: " + id);
        }
        return organizationService.getOrganizationById(convertToLong);

    }

    @PostMapping("/save")
    public void save(@Valid @RequestBody OrganizationSaveReqView organizationSaveReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            organizationService.saveOrganization(organizationSaveReqView);
        }
    }

    @PostMapping("/update")
    public String update(@Valid @RequestBody OrganizationUpdateReqView organizationUpdateReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            return organizationService.updateOrganization(organizationUpdateReqView);
        }
    }


/*    private List<String> getBindingErrors(List<ObjectError> errors) {
        new
        for (ObjectError er: errors) {
            er.getDefaultMessage();
        }
        return null;
    }*/


    @GetMapping(value = "/re")
    public ResponseEntity<String> re() {
        throw new MyTestException("ТЕст эксепшена");
    }
}
