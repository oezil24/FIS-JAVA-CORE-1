package fis.com.sprint4.controller;


import fis.com.sprint4.entity.Person;
import fis.com.sprint4.entity.role.Role;
import fis.com.sprint4.entity.role.UserRole;
import fis.com.sprint4.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class AccountController {
    @Autowired
    private PersonService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/")
    public Person createUser(@RequestBody Person user) throws Exception {
        // encoding pasword with bcry
        user.setCreateAt(LocalDateTime.now());
        user.setModifiedAt(LocalDateTime.now());
        user.setHiringDate(LocalDateTime.now());
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        Set<UserRole> roleSet = new HashSet<>();
        Role role = new Role();
        role.setRoleId(1L);
        role.setRoleName("TRAINEE");
        UserRole userRole = new UserRole();
        userRole.setPerson(user);
        userRole.setRole(role);
        roleSet.add(userRole);
        return this.userService.createUser(user, roleSet);
    }

}
