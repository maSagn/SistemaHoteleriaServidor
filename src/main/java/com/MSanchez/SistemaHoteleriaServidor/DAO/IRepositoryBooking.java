package com.MSanchez.SistemaHoteleriaServidor.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MSanchez.SistemaHoteleriaServidor.Models.Booking;

public interface IRepositoryBooking extends JpaRepository<Booking, Integer> {
    @Query("SELECT b FROM Booking b WHERE b.Usuario.email = :guestEmail")
    List<Booking> findBookingsByUserEmail(String guestEmail);

    @Query("SELECT COUNT (b) FROM Booking b WHERE b.Room.IdRoom = :idRoom")
    Long countRecordsByIdRoom(@Param("idRoom") int idRoom);

    // Filtro
    @Query("SELECT b FROM Booking b WHERE b.Status = :status")
    List<Booking> findBookingsByStatus(@Param("status") String status);

    @Query("SELECT b FROM Booking b WHERE b.Status = :status AND b.Usuario.email = :email")
    List<Booking> findBookingsByStatusAndEmail(@Param("status") String status, @Param("email") String email);

    @Query("SELECT b FROM Booking b WHERE b.Usuario.email = :email")
    List<Booking> findbyUsuarioEmail(@Param("email") String email);

}
