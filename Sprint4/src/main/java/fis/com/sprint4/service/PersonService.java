package fis.com.sprint4.service;

import fis.com.sprint4.entity.Person;
import fis.com.sprint4.entity.role.UserRole;

import java.util.Set;

public interface PersonService {
    Person createUser(Person user, Set<UserRole> userRoles) throws Exception;

    Person addPerson(Person person);

    Person updatePerson(Person person);

    Set<Person> getPeople();

    Person getPerson(Long id);

    void deletePerson(Long id);
}
