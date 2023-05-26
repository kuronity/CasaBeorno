package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Controller
public class SomeController {

    @Autowired
    SomeService someService;
    @Autowired
    PersonRepo personRepo;

    @GetMapping("/")
    String homeRedirect(){
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String getHome(Model model) {
        return "home";
    }
    @PostMapping("/home")
    public String postHome() {
        return "home";
    }

    // Login controller
    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("currentURL", "localhost:8080/login");
        return "login";
    }
    @GetMapping("/login-error")
    String loginError(Model model){
        model.addAttribute("failedLogin", true);
        model.addAttribute("currentURL", "localhost:8080/login-error");
        return "login";
    }

    @GetMapping("/birthdays")
    public String getBirthdays(@RequestParam(required = false) Long id, Model model) {
        model.addAttribute("someService", someService);
        List<Person> persons = someService.getAllPersons();
/*        for (Person person: persons) {
            long daysToBirthday = someService.daysToBirthday(person);
            person.setDaysToBirthday(daysToBirthday);
        }*/
        model.addAttribute("persons", persons);
        return PaginatedBirthdays(id,model,1, "name", "asc");
    }

    @GetMapping("/birthdays/{id}")
    public String getBirthdays2(Model model, @PathVariable Long id) {
        Person person = personRepo.findById(id).get();
        model.addAttribute("person", person);
        model.addAttribute("daysToBirthday", someService.daysToBirthday(person));
        return "detailBirthday";
    }
    @GetMapping("/birthdays/page/{currentPage}")
    public String PaginatedBirthdays(@RequestParam(required = false) Long id, Model model,
                                     @PathVariable(required = false) int currentPage,
                                     @Param("sortField") String sortField, @Param("sortDir") String sortDir) {
        someService.paginationModelGeneration(model, id != null ? personRepo.findById(id).get() :
                new Person("H", LocalDate.now(), LocalDate.now(), "none.jpg"), currentPage, sortField, sortDir);
        model.addAttribute("someService", someService);
        List<Person> persons = someService.getAllPersons();
        model.addAttribute("persons",persons);
        return "birthdays";
    }
    @GetMapping("/wendussy")
    public String Wendussy() {
        return "wendussy";
    }

    @GetMapping("/notableDates")
    public String getNotableDates(Model model, @RequestParam(required = false, defaultValue = "Mamma") String name) {
        Person person = someService.getPerson(name);
        model.addAttribute("persons", someService.getAllPersons());
        model.addAttribute("daysToBirthday", someService.daysToBirthday(person));
        model.addAttribute("daysToNameday", someService.daysToNameday(person));
        System.out.println(someService.daysToBirthday(personRepo.findByName("Mamma")));
        return "notableDates";
    }
    @PostMapping("/notableDates")
    public String postNotableDates(Model model, @RequestParam(required = false, defaultValue = "Pappa") String name) {
        Person person = someService.getPerson(name);
        model.addAttribute("persons", someService.getAllPersons());
        model.addAttribute("person", person);
        model.addAttribute("daysToBirthday", someService.daysToBirthday(person));
        model.addAttribute("daysToNameday", someService.daysToNameday(person));
        System.out.println(someService.daysToBirthday(personRepo.findByName("Mamma")));
        return "notableDates";
    }
}
