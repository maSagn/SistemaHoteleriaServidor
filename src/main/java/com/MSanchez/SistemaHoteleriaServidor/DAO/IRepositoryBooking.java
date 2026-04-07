package com.MSanchez.SistemaHoteleriaServidor.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MSanchez.SistemaHoteleriaServidor.Models.Booking;

public interface IRepositoryBooking extends JpaRepository<Booking, Integer> {

}
