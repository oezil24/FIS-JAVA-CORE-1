package com.dao.impl;

import com.dao.IDAO;
import com.model.Detective;
import com.model.Evidence;
import com.model.Person;

import java.util.*;

public class PersionDAO implements IDAO<Person> {

    @Override
    public Optional<Person> get(long id) {
        return MemoryDataSource.PERSONS.stream()
                .filter(person -> person.getId()==id)
                .findFirst();
    }

    @Override
    public void insert(Person person) {
        MemoryDataSource.PERSONS.add(person);
    }

    @Override
    public void update(Person person) {
        Optional<Person> personOptional = get(person.getId());
        if(personOptional.isPresent()){
            Person updatedPerson = personOptional.get();
            updatedPerson.replaceWith(person);
        }
    }

    @Override
    public List<Person> findAll() {
        return MemoryDataSource.PERSONS;
    }

    @Override
    public void deleteById(Long id) {
        for(Person person : MemoryDataSource.PERSONS) {
            if (person.getId() == id){
                MemoryDataSource.PERSONS.remove(person);
                return;
            }
        }

    }
}
