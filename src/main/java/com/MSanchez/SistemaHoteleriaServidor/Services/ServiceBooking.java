package com.MSanchez.SistemaHoteleriaServidor.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_Administrador"));

        try {
            if (isAdmin) {
                List<Booking> bookings = iRepositoryBooking.findAll();
                result.object = bookings;
                result.correct = true;
            } else {
                List<Booking> bookings = iRepositoryBooking.findbyUsuarioEmail(email);
                result.object = bookings;
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
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
            result.ex = ex;
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

            booking.setStatus("Confirmed");
            booking.setCreatedAt(new Date());

            Optional<Room> roomFind = iRepositoryRoom.findById(booking.Room.getIdRoom());

            if (roomFind.isPresent()) {
                Room roomExistente = roomFind.get();
                roomExistente.setIsAvailable(false);

                Room savedRoom = iRepositoryRoom.save(roomExistente);

            }

            Booking savedBooking = iRepositoryBooking.save(booking);
            result.object = savedBooking;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result Update(int IdBooking) {
        Result result = new Result();

        try {
            Optional<Booking> bookingFind = iRepositoryBooking.findById(IdBooking);

            if (bookingFind.isPresent()) {
                Booking bookingExistente = bookingFind.get();

                bookingExistente.setStatus("Cancelled");

                Optional<Room> roomFind = iRepositoryRoom.findById(bookingExistente.Room.getIdRoom());

                if (roomFind.isPresent()) {
                    Room roomExistente = roomFind.get();

                    roomExistente.setIsAvailable(true);

                    Room savedRoom = iRepositoryRoom.save(roomExistente);
                }

                Booking savedBooking = iRepositoryBooking.save(bookingExistente);
                result.object = savedBooking;
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result GetByEmail(String email) {
        Result result = new Result();

        try {
            List<Booking> bookings = iRepositoryBooking.findBookingsByUserEmail(email);

            result.object = bookings;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result GetByStatus(String status) {
        Result result = new Result();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_Administrador"));

        try {
            if (isAdmin) {
                List<Booking> bookings = iRepositoryBooking.findBookingsByStatus(status);
                result.object = bookings;
                result.correct = true;
            } else {
                List<Booking> bookings = iRepositoryBooking.findBookingsByStatusAndEmail(status, email);
                result.object = bookings;
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }
}
