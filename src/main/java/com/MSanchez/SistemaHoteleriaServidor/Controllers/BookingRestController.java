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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Booking RestController", description = "Controlador enfocado a metodos de Booking (reservas)")
@RestController
@RequestMapping("api/bookings")
public class BookingRestController {

    @Autowired
    private ServiceBooking serviceBooking;

    @Operation(summary = "Obtener todas las reservas", description = "Endpoint que devuleve todas las reservas.")
    @GetMapping
    public ResponseEntity GetAll() {
        Result result = serviceBooking.GetAll();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtener una reserva", description = "Endpoint que devuleve una reserva por Id.")
    @GetMapping("/{IdBooking}")
    public ResponseEntity GetById(
            @Parameter(
                description = "ID único de la reserva.",
                example = "58",
                required = true
            )
            @PathVariable int IdBooking) {
        Result result = serviceBooking.GetById(IdBooking);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Agregar una reserva", description = "Endpoint para agregar una nueva reserva al sistema.", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = "application/json", examples = @ExampleObject(name = "Ejemplo de reserva", value = """
            {
              "checkIn": "2026-04-24T10:00:00Z",
              "checkOut": "2026-04-25T10:00:00Z",
              "totalPrice": 0,
              "usuario": {
                "idUsuario": 1
              },
              "room": {
                "idRoom": 10
              }
            }
            """))))
    @PostMapping
    public ResponseEntity Add(@RequestBody Booking booking) {
        Result result = serviceBooking.Add(booking);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Cancelar una reserva", description = "Endpoint que modifica el status de la reserva a cancelado")
    @PutMapping("/{IdBooking}/cancel")
    public ResponseEntity Update(
        @Parameter(
            description = "ID único de la reserva.",
            example = "36",
            required = true
        )
        @PathVariable int IdBooking) {
        Result result = serviceBooking.Update(IdBooking);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Filtrar reservas por email", description = "Endpoint que devuleve todas las reservas filtradas por email")
    @GetMapping("/email/{email}")
    public ResponseEntity GetByGuestEmail(
        @Parameter(
                description = "email único",
                example = "miguel.masg.2001@gmail.com",
                required = true
            )
        @PathVariable String email) {
        Result result = serviceBooking.GetByEmail(email);
        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }

    @Operation(summary = "Filtrar reservas por status", description = "Endpoint que devuleve todas las reservas filtradas por status")
    @GetMapping("/status/{status}")
    public ResponseEntity GetByStatus(
        @Parameter(
                description = "Tipo de status",
                schema = @Schema(
                    allowableValues = {"Confirmed", "Cancelled"}
                )
            )
        @PathVariable String status) {
        Result result = serviceBooking.GetByStatus(status);

        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }

}
