package com.api.gestion.demo.service;

import com.api.gestion.demo.config.JwtService;
import com.api.gestion.demo.dto.LoginDTO;
import com.api.gestion.demo.dto.RegisterDTO;
import com.api.gestion.demo.dto.ResponseDTO;
import com.api.gestion.demo.entity.User;
import com.api.gestion.demo.repository.IUserRepository;
import com.api.gestion.demo.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public ResponseDTO register(RegisterDTO registerDTO) {
        User user = User.builder()
                .firstName(registerDTO.getFirstName())
                .lastName(registerDTO.getLastName())
                .email(registerDTO.getEmail())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return ResponseDTO.builder()
                .mensaje(jwtService.generateToken(user))
                .codigoRespuesta(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public ResponseDTO login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        User user = userRepository.findUserByEmail(loginDTO.getEmail()).orElseThrow();
        return ResponseDTO.builder()
                .mensaje(jwtService.generateToken(user))
                .codigoRespuesta(HttpStatus.OK.value())
                .build();
    }
}
