package fis.com.sprint4.service.impl;

import fis.com.sprint4.entity.Person;
import fis.com.sprint4.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = this.personRepository.findByUsername(username);
        if(person == null){
            System.out.println("User not found");
            throw new RuntimeException("No username found");
        }
        return person;
    }
}
