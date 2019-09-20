package project.khusainov.controller.organization;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @GetMapping("/list")
    public String getList() {


        return "list";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable String id) {
        return "id: " + id;
    }

    @GetMapping("/update")
    public String update() {
        return "update";
    }

    @GetMapping("/save")
    public String save() {
        return "save";
    }
}
