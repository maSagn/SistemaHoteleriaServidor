package com.MSanchez.SistemaHoteleriaServidor.Models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbooking")
    private int IdBooking;

    @Column(name = "guestname")
    private String GuestName;

    @Column(name = "guestemail")
    private String guestEmail;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "checkin")
    private Date CheckIn;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @Column(name = "checkout")
    private Date CheckOut;

    @Column(name = "totalprice")
    private double TotalPrice;

    @Column(name = "status")
    private String Status;
    
    @Column(name = "createdat")
    private Date CreatedAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idroom", nullable = false)
    public Room Room;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idusuario", nullable = false)
    public Usuario Usuario;

    public int getIdBooking() {
        return IdBooking;
    }

    public void setIdBooking(int idBooking) {
        IdBooking = idBooking;
    }

    public String getGuestName() {
        return GuestName;
    }

    public void setGuestName(String guestName) {
        GuestName = guestName;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public Date getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(Date checkIn) {
        CheckIn = checkIn;
    }

    public Date getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(Date checkOut) {
        CheckOut = checkOut;
    }

    public double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public Room getRoom() {
        return Room;
    }

    public void setRoom(Room room) {
        Room = room;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario usuario) {
        Usuario = usuario;
    }
}
