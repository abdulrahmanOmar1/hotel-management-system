package org.example.finalprojectweb.services.implementations;


import org.example.finalprojectweb.DTO.PasswordChangeRequest;
import org.example.finalprojectweb.DTO.UserDTO;
import org.example.finalprojectweb.entity.User;
import org.example.finalprojectweb.exceptions.ResourceNotFoundException;
import org.example.finalprojectweb.repository.UserRepository;
import org.example.finalprojectweb.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User newUser = userRepository.save(user);
        return convertToDto(newUser);
    }

    @Override
    public Optional<User> Login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (password.matches(user.getPass())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return convertToDto(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // Update user fields
        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhoneNumber(userDTO.getPhoneNumber());
        existingUser.setRole(User.Role.valueOf(userDTO.getRole()));

        // If you want to update the password, set it here
        if (userDTO.getPass() != null) {
            existingUser.setPass(userDTO.getPass());
        }

        User updatedUser = userRepository.save(existingUser);
        return convertToDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }
    @Override
    public UserDTO signUp(UserDTO userDTO) {
        // Set the role to CUSTOMER
        userDTO.setRole(User.Role.CUSTOMER.name());
        return createUser(userDTO);
    }
    @Override
    public boolean changePassword(Long userId, PasswordChangeRequest passwordChangeRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        if (passwordChangeRequest.getOldPassword().equals(user.getPass())) {
            user.setPass(passwordChangeRequest.getNewPassword());
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
    private UserDTO convertToDto(User user) {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setPass(user.getPass()); // Set password
        userDto.setRole(user.getRole().name());
        return userDto;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPass(userDTO.getPass()); // Set password
        user.setRole(User.Role.valueOf(userDTO.getRole()));
        return user;
    }
}
