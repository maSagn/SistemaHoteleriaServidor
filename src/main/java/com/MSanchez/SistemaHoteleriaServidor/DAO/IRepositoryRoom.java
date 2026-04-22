package com.MSanchez.SistemaHoteleriaServidor.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MSanchez.SistemaHoteleriaServidor.Models.Room;

public interface IRepositoryRoom extends JpaRepository<Room, Integer> {
    //Filtro Room
    Page<Room> findByType(String type, Pageable pageable);

    @Query("SELECT r.IsAvailable FROM Room r WHERE r.IdRoom = :idRoom")
    boolean RoomAvailable(@Param("idRoom") int idRoom);

    @Query("SELECT r FROM Room r WHERE r.IsAvailable = true")
    List<Room> findAvailableRooms();

    @Query("SELECT r FROM Room r WHERE r.IsAvailable = TRUE AND r.type = :type")
    List<Room> findAvailableRoomsPerType(@Param("type") String type);

    // Filtro Room
    @Query("SELECT r FROM Room r WHERE r.type = :type AND r.IsAvailable = :isAvailable")
    Page<Room> findByTypeAndIsAvailable(
        @Param("type") String type,
        @Param("isAvailable") boolean isAvailable,
        Pageable pageable
    );

    // Filtro Room
    @Query("SELECT r FROM Room r WHERE r.IsAvailable = :isAvailable")
    Page<Room> findRoomsByIsAvailable(@Param("isAvailable") boolean isAvailable, Pageable pageable);
}
