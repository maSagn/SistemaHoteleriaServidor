package com.MSanchez.SistemaHoteleriaServidor.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MSanchez.SistemaHoteleriaServidor.Models.Room;

public interface IRepositoryRoom extends JpaRepository<Room, Integer> {

}
