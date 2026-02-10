//package com.example.Restaurante.Rest;
//
//import com.example.Restaurante.DTOs.LoginRequestDTO;
//import com.example.Restaurante.DTOs.LoginResponseDTO;
//import com.example.Restaurante.Seguridad.JwtUtil;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
////Controlador de autenticación
//@RestController
//@RequestMapping("/usuarios/")
//@RequiredArgsConstructor
//public class UsuariosRest {
//    private final AuthenticationManager authenticationManager;
//    private final JwtUtil jwtUtil;
//
//    //Endpoint para login
//    @PostMapping("/login")
//    public LoginResponseDTO login(
//            @Valid @RequestBody LoginRequestDTO dto) {
//
//        // Autentica usuario
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        dto.getUsuario(),
//                        dto.getPassword()
//                )
//        );
//
//        // Genera token
//        String token = jwtUtil.generarToken(dto.getUsuario());
//
//        return new LoginResponseDTO(token);
//    }
//
//    @PostMapping("/login-test")
//    public String testAuth(@RequestBody LoginRequestDTO dto) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        dto.getUsuario(),
//                        dto.getPassword()
//                )
//        );
//        return "OK";
//    }
//
//}
