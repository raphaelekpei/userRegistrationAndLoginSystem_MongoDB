package com.raphael.usersystem.service;

import com.raphael.usersystem.data.model.Address;
import com.raphael.usersystem.data.model.User;
import com.raphael.usersystem.data.repository.UserRepository;
import com.raphael.usersystem.dtos.request.LoginRequest;
import com.raphael.usersystem.dtos.request.SignUpRequest;
import com.raphael.usersystem.dtos.response.LoginResponse;
import com.raphael.usersystem.dtos.response.SignUpResponse;
import com.raphael.usersystem.exceptions.UserManagementException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private final UserRepository userRepository;

    @Override
    public SignUpResponse addUser(SignUpRequest signUpRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(signUpRequest.getEmail());
        if (optionalUser.isPresent()){
            throw new UserManagementException("email has already been used");
        }
        User user = new User(
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword(),
                signUpRequest.getPhoneNumber(),
                signUpRequest.getAddress(),
                signUpRequest.getGender(),
                signUpRequest.getDateOfBirth(),
                signUpRequest.getNationality()
        );
        userRepository.save(user);
        SignUpResponse signUpResponse = new SignUpResponse();
        signUpResponse.setMessage("Registration was successful");
        return signUpResponse;
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
        if (optionalUser.isEmpty()){
            throw new UserManagementException("email or password incorrect");
        }
        User user = optionalUser.get();
        if (user.getPassword().equals(loginRequest.getPassword())){
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setMessage("you have successfully logged in");
            return loginResponse;
        }
        throw new UserManagementException("email or password incorrect");
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()){
            throw new UserManagementException("user with email" + email + "does not exist");
        }
        return optionalUser.get();
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()){
            throw new UserManagementException("user with username" + username + "does not exist");
        }
        return optionalUser.get();
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (optionalUser.isEmpty()){
            throw new UserManagementException("user not found");
        }
        return optionalUser.get();
    }

    @Override
    public User getUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new UserManagementException("user not found");
        }
        return optionalUser.get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(String userId, String firstName, String lastName, String phoneNumber, String password, Address address) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new UserManagementException("user not found");
        }
        User user = optionalUser.get();
        if (firstName != null){
            user.setFirstName(firstName);
        }
        if (lastName != null){
            user.setLastName(lastName);
        }
        if (phoneNumber != null){
            user.setPhoneNumber(phoneNumber);
        }
        if (password != null){
            user.setPassword(password);
        }
        if (address != null){
            user.setAddress(address);
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(String userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new UserManagementException("user not found");
        }
        User user = optionalUser.get();
        userRepository.delete(user);
    }
    @Override
    public void deleteAllUser() {
        userRepository.deleteAll();
    }


}
