package com.anpr.server.controller;

import com.anpr.server.exception.CustomMessage;
import com.anpr.server.model.User;
import com.anpr.server.repository.UserValidator;
import com.anpr.server.resorces.EndPoints;
import com.anpr.server.resorces.Resources;
import com.anpr.server.service.SecurityServiceImpl;
import com.anpr.server.service.UserDetailsServiceImpl;
import com.anpr.server.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequestMapping("auth")
@RestController

public class UserController{

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    SecurityServiceImpl securityService;

    @GetMapping(path = EndPoints.USER_HOME)
    public CustomMessage home() {

        return new CustomMessage("Welcome in ANPR system",HttpStatus.OK);
    }


    @GetMapping(path = EndPoints.CURRENT_USER)
    public ResponseEntity user() {

        Optional<User> user = userRepository.findByUsername(securityService.findLoggedInUsername());
        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CustomMessage(Resources.USER_NOT_FOUND,HttpStatus.NO_CONTENT));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(Resources.OPERATION_SUCCESSFUL,HttpStatus.OK,user.get()));
    }


    @GetMapping(path = EndPoints.FIND_USER)
    public ResponseEntity findByUserID(@PathVariable("userId") Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CustomMessage(Resources.USER_NOT_FOUND,HttpStatus.NO_CONTENT));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(Resources.OPERATION_SUCCESSFUL,HttpStatus.OK,user.get()));
    }


    @PostMapping(path = EndPoints.REGISTER_USER)
    public ResponseEntity<CustomMessage> register(@Valid @RequestBody User user, BindingResult bindingResult) {

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {

            List<String> errors = new ArrayList<>();
            bindingResult
                    .getAllErrors()
                    .forEach(f -> errors.add(f.getCode() + ": " + f.getDefaultMessage()));

            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new CustomMessage("Multiple Errors",HttpStatus.NOT_ACCEPTABLE,errors));

        }

        User createdUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomMessage(Resources.USER_CREATED,HttpStatus.CREATED,createdUser));
    }


    @GetMapping(path = EndPoints.LOG_OUT)
    public ResponseEntity logout(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        SecurityContextHolder.clearContext();

        return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(Resources.USER_LOGGED_OUT,HttpStatus.OK));
    }
}