package com.MSanchez.SistemaHoteleriaServidor.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MSanchez.SistemaHoteleriaServidor.Models.Room;

public interface IRepositoryRoom extends JpaRepository<Room, Integer> {
    List<Room> findByType(String type);

    @Query("SELECT r.IsAvailable FROM Room r WHERE r.IdRoom = :idRoom")
    boolean RoomAvailable(@Param("idRoom") int idRoom);
}
