package com.MSanchez.SistemaHoteleriaServidor.Models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Schema(description = "Entidad que repesenta una habitación")
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idroom")
    private int IdRoom;

    @Column(name = "roomnumber")
    private String RoomNumber;

    @Column(name = "type")
    private String type;

    @Column(name = "pricepernight")
    private double PricePerNight;

    @Column(name = "isavailable")
    private boolean IsAvailable;

    @Column(name = "maxguests")
    private int MaxGuests;

    @Column(name = "description")
    private String Description;
    
    @Schema(hidden = true)
    public int getIdRoom() {
        return IdRoom;
    }
    public void setIdRoom(int idRoom) {
        IdRoom = idRoom;
    }
    public String getRoomNumber() {
        return RoomNumber;
    }
    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getPricePerNight() {
        return PricePerNight;
    }
    public void setPricePerNight(double pricePerNight) {
        PricePerNight = pricePerNight;
    }
    @Schema(hidden = true)
    public boolean isIsAvailable() {
        return IsAvailable;
    }
    public void setIsAvailable(boolean isAvailable) {
        IsAvailable = isAvailable;
    }
    public int getMaxGuests() {
        return MaxGuests;
    }
    public void setMaxGuests(int maxGuests) {
        MaxGuests = maxGuests;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
}
