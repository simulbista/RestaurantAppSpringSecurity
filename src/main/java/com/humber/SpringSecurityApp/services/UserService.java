package com.humber.SpringSecurityApp.services;

import com.humber.SpringSecurityApp.models.MyUser;
import com.humber.SpringSecurityApp.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    //save user to database
    public void saveUser(MyUser user){
        //encode the password before saving it to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    //check if user exists by username
    public boolean userExists(String userName){
        return userRepository.findByUsername(userName).isPresent();
    }

}
