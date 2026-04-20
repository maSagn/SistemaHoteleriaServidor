package com.MSanchez.SistemaHoteleriaServidor.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MSanchez.SistemaHoteleriaServidor.Component.JwtUtil;
import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryUsuario;
import com.MSanchez.SistemaHoteleriaServidor.Models.Usuario;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("api/auth")
public class LoginRestController {

    private final IRepositoryUsuario iRepositoryUsuario;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginRestController(IRepositoryUsuario iRepositoryUsuario, PasswordEncoder passwordEncoder,
            JwtUtil jwtUtil) {
        this.iRepositoryUsuario = iRepositoryUsuario;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody Usuario loginRequest, HttpSession session) {
        Usuario dbUser = iRepositoryUsuario.findByEmail(loginRequest.getEmail());

        if (dbUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas.");
        }

        if (passwordEncoder.matches(loginRequest.getPassword(), dbUser.getPassword())) {
            String token = jwtUtil.generateToken(dbUser.getEmail(), dbUser.Rol.getNombre(), dbUser.getIdUsuario());

            // Guardar token en sesion
            // session.setAttribute("jwt", token);

            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas.");
        }
    }

}
