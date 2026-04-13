package com.MSanchez.SistemaHoteleriaServidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MSanchez.SistemaHoteleriaServidor.Models.Booking;
import com.MSanchez.SistemaHoteleriaServidor.Models.Result;
import com.MSanchez.SistemaHoteleriaServidor.Services.ServiceBooking;

@RestController
@RequestMapping("api/bookings")
public class BookingRestController {

    @Autowired
    private ServiceBooking serviceBooking;

    @GetMapping
    public ResponseEntity GetAll() {
        Result result = serviceBooking.GetAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{IdBooking}")
    public ResponseEntity GetById(@PathVariable int IdBooking) {
        Result result = serviceBooking.GetById(IdBooking);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity Add(@RequestBody Booking booking) {
        Result result = serviceBooking.Add(booking);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{IdBooking}/cancel")
    public ResponseEntity Update(@PathVariable int IdBooking) {
        Result result  = serviceBooking.Update(IdBooking);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity GetByGuestEmail(@PathVariable String email) {
        Result result = serviceBooking.GetByEmail(email);
        return ResponseEntity.ok(result);
    }

}
