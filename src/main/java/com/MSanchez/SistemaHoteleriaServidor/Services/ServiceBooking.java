package com.MSanchez.SistemaHoteleriaServidor.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryBooking;
import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryRoom;
import com.MSanchez.SistemaHoteleriaServidor.Models.Booking;
import com.MSanchez.SistemaHoteleriaServidor.Models.Result;
import com.MSanchez.SistemaHoteleriaServidor.Models.Room;

@Service
public class ServiceBooking {

    @Autowired
    private IRepositoryBooking iRepositoryBooking;

    @Autowired
    private IRepositoryRoom iRepositoryRoom;

    public Result GetAll() {
        Result result = new Result();

        try {
            List<Booking> bookings = iRepositoryBooking.findAll();
            result.object = bookings;
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result. ex = ex;
        }

        return result;
    }

    public Result GetById(int IdBooking) {
        Result result = new Result();

        try {
            Optional<Booking> booking = iRepositoryBooking.findById(IdBooking);

            if (booking.isPresent()) {
                result.object = booking;
                result.correct = true;
            }
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result. ex = ex;
        }

        return result;
    }

    public Result Add(Booking booking) {
        Result result = new Result();

        try {
            boolean roomAvailable = iRepositoryRoom.RoomAvailable(booking.Room.getIdRoom());

            if (!roomAvailable) {
                result.errorMessage = "Esta habitacion no se encuentra disponible";
                result.correct = false;
                return result;
            }

            Booking savedBooking = iRepositoryBooking.save(booking);
            result.object = savedBooking;
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result. ex = ex;
        }

        return result;
    }

    public Result Update(Booking booking, int IdBooking) {
        Result result = new Result();

        try {
            Optional<Booking> bookingFind = iRepositoryBooking.findById(IdBooking);

            if (bookingFind.isPresent()) {
                Booking bookingExistente = bookingFind.get();

                // bookingExistente.setGuestName(booking.getGuestName());
                // bookingExistente.setGuestEmail(booking.getGuestEmail());
                // bookingExistente.setCheckIn(booking.getCheckIn());
                // bookingExistente.setCheckOut(booking.getCheckOut());
                // bookingExistente.setTotalPrice(booking.getTotalPrice());
                bookingExistente.setStatus(booking.getStatus());
                // bookingExistente.setCreatedAt(new Date());
                // bookingExistente.Room = new Room();
                // bookingExistente.Room.setIdRoom(booking.Room.getIdRoom());

                Booking savedBooking = iRepositoryBooking.save(bookingExistente);
                result.object = savedBooking;
                result.correct = true;
            }
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result. ex = ex;
        }

        return result;
    }

    public Result GetByEmail(String email) {
        Result result = new Result();

        try {
            List<Booking> bookings = iRepositoryBooking.findByGuestEmail(email);

            result.object = bookings;
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result. ex = ex;
        }

        return result;
    }
}
