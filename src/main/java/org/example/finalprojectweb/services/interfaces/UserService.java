package org.example.finalprojectweb.services.interfaces;


import org.example.finalprojectweb.DTO.PasswordChangeRequest;
import org.example.finalprojectweb.DTO.UserDTO;
import org.example.finalprojectweb.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserDTO updateUser(UserDTO userDTO, Long id);
    void deleteUser(Long id);
    List<UserDTO> getAllUsers();
    Optional<User> Login(String email, String password);
    UserDTO signUp(UserDTO userDTO);
    boolean changePassword(Long userId, PasswordChangeRequest passwordChangeRequest);

}