package com.api.gestion.demo.servicios;

import com.api.gestion.demo.constantes.FacturaConstantes;
import com.api.gestion.demo.dao.UserDao;
import com.api.gestion.demo.user.User;
import com.api.gestion.demo.utils.FacturaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public ResponseEntity<String> singUp(Map<String, String> requestmap) {
        try {
            log.info("Registro interno de un usuario {} : " + requestmap);

            if (validateSingUpMap(requestmap)) {
                User user = userDao.finByEmail(requestmap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestmap));
                    return FacturaUtils.getResponseEntity("Usuario creado exitosamente!", HttpStatus.CREATED);
                } else {
                    return FacturaUtils.getResponseEntity("El usuario con ese email ya existe", HttpStatus.BAD_REQUEST);
                }
            } else {
                return FacturaUtils.getResponseEntity(FacturaConstantes.DATOS_INVALIDOS, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Boolean validateSingUpMap(Map<String, String> requestmap) {
        if (requestmap.containsKey("nombre") &&
                requestmap.containsKey("numeroDeContacto") &&
                requestmap.containsKey("email") &&
                requestmap.containsKey("password")) {
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestmap) {
        User user = new User();
        user.setNombre(requestmap.get("nombre"));
        user.setNumeroDeContacto(requestmap.get("numeroDeContacto"));
        user.setEmail(requestmap.get("email"));
        user.setPassword(requestmap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
    }
}
