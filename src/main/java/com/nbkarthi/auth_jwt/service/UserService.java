package com.nbkarthi.auth_jwt.service;

import com.nbkarthi.auth_jwt.auth.UpdateRequest;
import com.nbkarthi.auth_jwt.model.Role;
import com.nbkarthi.auth_jwt.model.User;
import com.nbkarthi.auth_jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Optional<List<User>> getAllUsers() {
        return userRepository.findAllByFirstNameIsNotNull();
    }

    public User updateUser(UpdateRequest updateRequest) {
        System.out.println("Hii " + updateRequest.getFirstname());
        System.out.println("Role  " + updateRequest.getRole());
                Optional<User> u =     userRepository.findByEmail(updateRequest.getEmail());
                User upUser = u.orElse(new User());
                if(u.isPresent()){
                    upUser.setFirstName(updateRequest.getFirstname());
                    upUser.setLastName(updateRequest.getLastname());
                    upUser.setRole(updateRequest.getRole());
                    userRepository.save(u.get());
                }
    //        var user = User.builder()
    //                .firstName(updateRequest.getFirstname())
    //                .lastName(updateRequest.getLastname())
    //                .role(Role.USER)
    //                .id(updateRequest.getId())
    //                .build();

        return  userRepository.save(upUser);
    }

    public boolean deleteUser(UpdateRequest user) {
        try {
            User user2 = User.builder().id(user.getId()).build();
            userRepository.delete(user2);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
