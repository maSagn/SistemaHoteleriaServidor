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

    @Query("SELECT r FROM Room r WHERE r.IsAvailable = true")
    List<Room> findAvailableRooms();

    @Query("SELECT r FROM Room r WHERE r.IsAvailable = TRUE AND r.type = :type")
    List<Room> findAvailableRoomsPerType(@Param("type") String type);

    @Query("SELECT r FROM Room r WHERE r.type = :type AND r.IsAvailable = :isAvailable")
    List<Room> findByTypeAndIsAvailable(
        @Param("type") String type,
        @Param("isAvailable") boolean isAvailable
    );

    @Query("SELECT r FROM Room r WHERE r.IsAvailable = :isAvailable")
    List<Room> findRoomsByIsAvailable(@Param("isAvailable") boolean isAvailable);
}
