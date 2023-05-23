package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDate;
@Entity
@Service
@SessionScope
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    LocalDate birthday;
    LocalDate nameday;
    long daysToBirthday;
    String image;

    public Person() {
    }

    public Person(String name, LocalDate birthday, LocalDate nameday) {
        this.name = name;
        this.birthday = birthday;
        this.nameday = nameday;
    }

    public Person(String name, LocalDate birthday, LocalDate nameday, String image) {
        this.name = name;
        this.birthday = birthday;
        this.nameday = nameday;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getDaysToBirthday() {
        return daysToBirthday;
    }

    public void setDaysToBirthday(long daysToBirthday) {
        this.daysToBirthday = daysToBirthday;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LocalDate getNameday() {
        return nameday;
    }

    public void setNameday(LocalDate nameday) {
        this.nameday = nameday;
    }
}
/*public record Person (@Id Long id, String name, LocalDate birthday, LocalDate nameday) {
}*/
