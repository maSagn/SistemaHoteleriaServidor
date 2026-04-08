package com.MSanchez.SistemaHoteleriaServidor.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.MSanchez.SistemaHoteleriaServidor.Models.Booking;

public interface IRepositoryBooking extends JpaRepository<Booking, Integer> {
    List<Booking> findByGuestEmail(String guestEmail);

    @Query("SELECT COUNT (b) FROM Booking b WHERE b.Room.IdRoom = :idRoom")
    Long countRecordsByIdRoom(@Param("idRoom") int idRoom);

}
