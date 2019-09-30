package project.khusainov.organization.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.khusainov.exception.BadRequestException;
import project.khusainov.organization.service.OrganizationService;
import project.khusainov.organization.view.OrganizationByIdRespView;
import project.khusainov.organization.view.OrganizationListReqView;
import project.khusainov.organization.view.OrganizationListRespView;
import project.khusainov.organization.view.OrganizationSaveReqView;
import project.khusainov.organization.view.OrganizationUpdateReqView;

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
    public List<OrganizationListRespView> getList(@Valid @RequestBody OrganizationListReqView organizationListReqView, BindingResult bindingResult) {
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
    public void update(@Valid @RequestBody OrganizationUpdateReqView organizationUpdateReqView, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult.toString());
        }
        else {
            organizationService.updateOrganization(organizationUpdateReqView);
        }
    }
}
