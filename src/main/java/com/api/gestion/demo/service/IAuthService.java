package com.api.gestion.demo.service;

import com.api.gestion.demo.dto.LoginDTO;
import com.api.gestion.demo.dto.RegisterDTO;
import com.api.gestion.demo.dto.ResponseDTO;

public interface IAuthService {

    ResponseDTO register(RegisterDTO registerDTO);

    ResponseDTO login(LoginDTO loginDTO);
}
