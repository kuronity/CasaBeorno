package com.example.demo;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class SomeService {
    @Autowired
    PersonRepo personRepo;

    public List<Person> getAllPersons() {
        return personRepo.findAll();
    }
    public Person getPerson(String name) {
        return personRepo.findByName(name);
    }
    public long daysToBirthday(Person person) {
        LocalDate personsBirthday = person.getBirthday();
        LocalDate today = LocalDate.now();
        LocalDate nextBday = personsBirthday.withYear(today.getYear());
        if(nextBday.isBefore(today) || nextBday.isEqual(today)) {
            nextBday = nextBday.plusYears(1);
        }
        return ChronoUnit.DAYS.between(today, nextBday);
    }
    public long daysToNameday(Person person) {
        LocalDate personsNameday = person.getNameday();
        LocalDate today = LocalDate.now();
        LocalDate nextNameday = personsNameday.withYear(today.getYear());
        if(nextNameday.isBefore(today) || nextNameday.isEqual(today)) {
            nextNameday = nextNameday.plusYears(1);
        }
        return ChronoUnit.DAYS.between(today, nextNameday);
    }
    public Page<Person> getPersonsPagination(int pageNumber, String sortField, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(pageNumber - 1,8, sort);
        System.out.println(personRepo.findAll(pageable));
        return personRepo.findAll(pageable);
    }
    public Model paginationModelGeneration(Model model, Person person, int currentPage, String sortField, String sortDir) {
        Page<Person> page = getPersonsPagination(currentPage, sortField, sortDir);
        List<Person> persons = getAllPersons();
        int totalPages = page.getTotalPages();
        long totalPersons = page.getTotalElements();
        model.addAttribute("personsOnCurrentPage", page);
        model.addAttribute("totalPersons", totalPersons);
        model.addAttribute("person", person);
        //model.addAttribute("persons", persons);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);

        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDir",reverseSortDir);

        return model;
    }
    public List<Person> birthday() {
        HashMap<String, LocalDate> birthdays = new HashMap<>();
        birthdays.put("Björn", LocalDate.of(1990, Month.DECEMBER,25));
        birthdays.put("Ester", LocalDate.of(1991, Month.JULY,16));
        birthdays.put("Mamma", LocalDate.of(1955, Month.MAY,14));
        birthdays.put("Pappa", LocalDate.of(1953, Month.MAY,6));

        // Andrén
        birthdays.put("Karin", LocalDate.of(1987, Month.MARCH,29));
        birthdays.put("Jakob", LocalDate.of(1991, Month.JUNE,28));
        birthdays.put("Tom", LocalDate.of(1991, Month.JULY,16));
        birthdays.put("Li", LocalDate.of(1991, Month.JULY,16));

        // Ullén
        birthdays.put("Helene", LocalDate.of(1991, Month.JULY,16));
        birthdays.put("Lars", LocalDate.of(1991, Month.JULY,16));
        birthdays.put("Hanna", LocalDate.of(1991, Month.JULY,16));
        birthdays.put("Nike", LocalDate.of(1991, Month.JULY,16));
        birthdays.put("Jack", LocalDate.of(1991, Month.JULY,16));
        birthdays.put("Elsa", LocalDate.of(1991, Month.JULY,16));

        List<Person> personList = new ArrayList<>();
        /*personList.add(new Person("Björn", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Mamma", LocalDate.of(1955, Month.MAY,14),LocalDate.of(1990,Month.OCTOBER,9)));
        personList.add(new Person("Pappa", LocalDate.of(1953, Month.MAY,6),LocalDate.of(1990,Month.JUNE,11)));
        //Andrén
        personList.add(new Person("Karin", LocalDate.of(1987, Month.MARCH,29),LocalDate.of(1990,Month.AUGUST,2)));
        personList.add(new Person("Jakob", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Tom", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Li", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        //Ullén
        personList.add(new Person("Helene", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Lars", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Hanna", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Nike", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Jack", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Elsa", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        //Friends
        personList.add(new Person("Ester", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Aul", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Rieger", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Morkan", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));
        personList.add(new Person("Steft", LocalDate.of(1990, Month.DECEMBER,25),LocalDate.of(1990,Month.JUNE,18)));*/

        return personList;
    }
}
