package com.MSanchez.SistemaHoteleriaServidor.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryBooking;
import com.MSanchez.SistemaHoteleriaServidor.Models.Booking;
import com.MSanchez.SistemaHoteleriaServidor.Models.Result;

@Service
public class ServiceBooking {

    @Autowired
    private IRepositoryBooking iRepositoryBooking;

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
           // List<Booking> bookings = iRepositoryBooking.
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result. ex = ex;
        }

        return result;
    }
}
