package com.example.springsection1.controller;

import com.example.springsection1.model.Customer;
import com.example.springsection1.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            Customer savedCustomer = customerRepository.save(customer);
            if (savedCustomer.getId()>0){
                return ResponseEntity.status(HttpStatus.CREATED).body("Given user details are saved successfully");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save the user details");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
