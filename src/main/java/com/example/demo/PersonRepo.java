package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends CrudRepository<Person,Long> {

    @Override
    List<Person> findAll();
    
    Person findByName(String name);
}
