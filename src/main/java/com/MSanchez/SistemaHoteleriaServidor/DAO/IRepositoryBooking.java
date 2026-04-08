package com.MSanchez.SistemaHoteleriaServidor.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MSanchez.SistemaHoteleriaServidor.Models.Booking;

public interface IRepositoryBooking extends JpaRepository<Booking, Integer> {
    List<Booking> findByGuestEmail(String guestEmail);

}
