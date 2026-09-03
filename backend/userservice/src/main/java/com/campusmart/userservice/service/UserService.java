package com.campusmart.userservice.service;

import com.campusmart.userservice.dto.MeResponseDTO;
import com.campusmart.userservice.dto.RegisterRequestDTO;
import com.campusmart.userservice.exception.UserAlreadyExistException;
import com.campusmart.userservice.exception.UserNotFoundException;
import com.campusmart.userservice.model.UserModel;
import com.campusmart.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public ResponseEntity<?> me(String email){
        UserModel userModel =  userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        MeResponseDTO responseDTO = MeResponseDTO.builder()
                .name(userModel.getName())
                .phone(userModel.getPhone())
                .image(userModel.getImage())
                .email(userModel.getEmail())
                .college(userModel.getCollege())
                .onboarded(userModel.isOnboarded())
                .createdAt(userModel.getCreatedAt())
                .department(userModel.getDepartment())
                .build();
        return ResponseEntity.ok(responseDTO);
    }

    public ResponseEntity<?> register(RegisterRequestDTO requestDTO, String email){
        UserModel userModel = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        userModel.setName(requestDTO.getName());
        userModel.setPhone(requestDTO.getPhone());
        userModel.setCollege(requestDTO.getCollege());
        userModel.setDepartment(requestDTO.getDepartment());
        if (requestDTO.getImage() != null) {
            userModel.setImage(requestDTO.getImage());
        }
        userModel.setOnboarded(true);
        userRepository.save(userModel);
        return ResponseEntity.ok("User registered successfully");
    }

    public void createUser(String email){
        if (userRepository.findByEmail(email).isPresent()) {
            return;
        }
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setCreatedAt(LocalDateTime.now());
        userModel.setOnboarded(false);
        userRepository.save(userModel);
    }

    public void deleteUser(String email){
        UserModel userModel = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        userRepository.delete(userModel);
    }
}
