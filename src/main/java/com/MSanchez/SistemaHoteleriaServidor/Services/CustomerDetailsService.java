package com.MSanchez.SistemaHoteleriaServidor.Services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryUsuario;
import com.MSanchez.SistemaHoteleriaServidor.Models.Usuario;

@Service
public class CustomerDetailsService implements UserDetailsService {
    private final IRepositoryUsuario iRepositoryUsuario;

    public CustomerDetailsService(IRepositoryUsuario iRepositoryUsuario) {
        this.iRepositoryUsuario = iRepositoryUsuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = iRepositoryUsuario.findByEmail(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }

        return User.withUsername(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.Rol.getNombre())
                .build();
    }
}
