package com.MSanchez.SistemaHoteleriaServidor.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MSanchez.SistemaHoteleriaServidor.Models.Room;

public interface IRepositoryRoom extends JpaRepository<Room, Integer> {
    List<Room> findByType(String type);
}
