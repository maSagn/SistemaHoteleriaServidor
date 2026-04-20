package com.MSanchez.SistemaHoteleriaServidor.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MSanchez.SistemaHoteleriaServidor.Models.Usuario;

public interface IRepositoryUsuario extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String email);
}
