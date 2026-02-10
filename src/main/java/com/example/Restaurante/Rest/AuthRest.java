package com.example.Restaurante.Rest;

import com.example.Restaurante.DTOs.LoginRequestDTO;
import com.example.Restaurante.DTOs.LoginResponseDTO;
import com.example.Restaurante.Servicio.AuthServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthRest {
    private final AuthServicio authServicio;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authServicio.login(request));
    }
}



