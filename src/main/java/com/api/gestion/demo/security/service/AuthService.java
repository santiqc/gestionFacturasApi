package com.api.gestion.demo.security.service;

import com.api.gestion.demo.user.IUserRepository;
import com.api.gestion.demo.security.jwt.JwtUtilService;
import com.api.gestion.demo.user.User;
import com.api.gestion.demo.user.Role;
import com.api.gestion.demo.utils.FacturaUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IUserRepository userRepository;
    private final JwtUtilService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //    @Override
//    public ResponseEntity<String> singUp(Map<String, String> requestmap) {
//        try {
//            log.info("Registro interno de un usuario {} : " + requestmap);
//
//            if (validateSingUpMap(requestmap)) {
//                User user = userDao.finByEmail(requestmap.get("email"));
//                if (Objects.isNull(user)) {
//                    user = userDao.save(getUserFromMap(requestmap));
//                    return FacturaUtils.getResponseEntity("Usuario creado exitosamente!," +
//                            "Token:" +
//                            jwtUtil.generateToken(user.getEmail(), user.getRole()), HttpStatus.CREATED);
//                } else {
//                    return FacturaUtils.getResponseEntity("El usuario con ese email ya existe", HttpStatus.BAD_REQUEST);
//                }
//            } else {
//                return FacturaUtils.getResponseEntity(FacturaConstantes.DATOS_INVALIDOS, HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return FacturaUtils.getResponseEntity(FacturaConstantes.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
    @Override
    public ResponseEntity<String> singUp(Map<String, String> requestmap) {
        User user = User.builder()
                .nombre(requestmap.get("nombre"))
                .numeroDeContacto(requestmap.get("numeroDeContacto"))
                .email(requestmap.get("email"))
                .password(passwordEncoder.encode(requestmap.get("password")))
                .status("false")
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return FacturaUtils.getResponseEntity("Usuario creado exitosamente!," +
                "Token:" +
                jwtService.getToken(user), HttpStatus.CREATED);

    }


    @Override
    public ResponseEntity<String> login(Map<String, String> requestmap) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestmap.get("email"), requestmap.get("password")));
        User user = userRepository.finByEmail(requestmap.get("email")).orElse(null);
        String token = jwtService.getToken(user);
        return FacturaUtils.getResponseEntity("Usuario logueado exitosamente!," +
                "Token:" +
                token, HttpStatus.CREATED);

    }

//    @Override
//    public ResponseEntity<String> login(Map<String, String> requestmap) {
//        log.info("Dentro de login");
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(requestmap.get("email"), requestmap.get("password"))
//            );
//
//            if (authentication.isAuthenticated()) {
//                if (customerDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
//                    return new ResponseEntity<String>(
//                            "{\"token\":\"" + jwtUtil.generateToken(customerDetailsService.getUserDetail().getEmail(),
//                                    customerDetailsService.getUserDetail().getRole()) + "\"}",
//                            HttpStatus.OK
//                    );
//                } else {
//                    return new ResponseEntity<String>("Espera la aprobaciond el administrador", HttpStatus.BAD_REQUEST);
//                }
//            }
//        } catch (Exception e) {
//            log.error("{}", e);
//        }
//        return new ResponseEntity<String>("Credenciales incorrectas", HttpStatus.BAD_REQUEST);
//    }
//
//    private Boolean validateSingUpMap(Map<String, String> requestmap) {
//        if (requestmap.containsKey("nombre") &&
//                requestmap.containsKey("numeroDeContacto") &&
//                requestmap.containsKey("email") &&
//                requestmap.containsKey("password")) {
//            return true;
//        }
//        return false;
//    }
//
//    private User getUserFromMap(Map<String, String> requestmap) {
//        User user = new User();
//        user.setNombre(requestmap.get("nombre"));
//        user.setNumeroDeContacto(requestmap.get("numeroDeContacto"));
//        user.setEmail(requestmap.get("email"));
//        user.setPassword(passwordEncoder.encode(requestmap.get("password")));
//        user.setStatus("false");
//        user.setRole(Role.USER);
//        return user;
//    }
}
