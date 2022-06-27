package fis.com.sprint4.controller;
import fis.com.sprint4.config.JwtUtils;
import fis.com.sprint4.entity.Person;
import fis.com.sprint4.entity.security.JwtRequest;
import fis.com.sprint4.entity.security.JwtResponse;
import fis.com.sprint4.service.impl.AccountDetailServiceImpl;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
    private final AuthenticationManager authenticationManager;

    private final AccountDetailServiceImpl accountDetailService;

    private final JwtUtils jwtUtils;

    public AuthenticateController(AuthenticationManager authenticationManager, AccountDetailServiceImpl accountDetailService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.accountDetailService = accountDetailService;
        this.jwtUtils = jwtUtils;
    }

    @SneakyThrows
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("User not found ");
        }
        //////
        UserDetails userDetails = this.accountDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled " + e.getMessage());
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new Exception("Invalid Credentials " + e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public Person getCurrentUser(Principal principal) {
        return (Person) this.accountDetailService.loadUserByUsername(principal.getName());
    }
}
