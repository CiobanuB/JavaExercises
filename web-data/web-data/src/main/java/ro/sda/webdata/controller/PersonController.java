package ro.sda.webdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.sda.webdata.persistence.person.PersonEntity;
import ro.sda.webdata.persistence.service.person.PersonService;

import java.util.List;
import java.util.StringJoiner;

@Controller
@RequestMapping("person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // POST request http://localhost:8080/person/save
    @PostMapping("save")
    @ResponseBody
    public String save(@RequestBody String name) {
        personService.save(name);
        return "Saved";
    }

    // GET request http://localhost:8080/person/all
    @GetMapping("all")
    @ResponseBody
    public String findAll() {
        List<PersonEntity> persons = personService.findAll();
        StringJoiner joiner = new StringJoiner(",");

        for (PersonEntity person : persons) {
            joiner.add(person.getName());
        }

        return joiner.toString();
    }
}