package com.MSanchez.SistemaHoteleriaServidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MSanchez.SistemaHoteleriaServidor.Models.Result;
import com.MSanchez.SistemaHoteleriaServidor.Models.Room;
import com.MSanchez.SistemaHoteleriaServidor.Services.ServiceRoom;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Room RestController", description = "Controlador enfocado a metodos de Room (habitaciones)")
@RestController
@RequestMapping("api/rooms")
public class RoomRestController {

    @Autowired
    private ServiceRoom serviceRoom;

    @Operation(summary = "Obtener todas las habitaciones", description = "Endpoint que devuleve todas las habitaciones (paginadas).")
    @GetMapping
    public ResponseEntity GetAll(
            @Parameter(
                description = "Numero de página (empieza en 0)",
                example = "0"
            )
            @RequestParam(defaultValue = "0") int page,

            @Parameter(
                description = "Cantidad de registros por página",
                example = "10"
            )
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);

        Result result = serviceRoom.GetAll(null, pageable);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obtener todas las habitaciones por tipo", description = "Endpoint que devuleve todas las habitaciones por tipo (para el modal de seleccion de habitación).")
    @GetMapping("/available")
    public ResponseEntity GetAvailableRoomsPerType(
        @Parameter(
                description = "Tipo de habitación",
                schema = @Schema(
                    allowableValues = {"Individual", "Doble", "Suite", "Familiar"}
                )
            )
        @RequestParam(required = false) String type) {
        Result result = serviceRoom.GetAvailableRoomsPerType(type);

        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }

    @Operation(summary = "Obtener una habitación", description = "Endpoint que devuleve una habitacion por Id.")
    @GetMapping("/{IdRoom}")
    public ResponseEntity GetById(
        @Parameter(
            description = "ID único de la habitación.",
            example = "101",
            required = true
        )
        @PathVariable int IdRoom) {
        Result result = serviceRoom.GetById(IdRoom);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Agregar una habitación", description = "Endpoint para agregar una nueva habitación al sistema.")
    @PostMapping
    public ResponseEntity Add(@RequestBody Room room) {
        Result result = serviceRoom.Add(room);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Actualizar una habitacion", description = "Endpoint para actualizar una habitacion por Id.")
    @PatchMapping("/{IdRoom}")
    public ResponseEntity Update(
        @RequestBody Room room, 
        
        @Parameter(
            description = "ID único de la habitación.",
            example = "101",
            required = true
        )
        @PathVariable int IdRoom) {
        Result result = serviceRoom.Update(room, IdRoom);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Eliminar una habitacion", description = "Endpoint que elimina una habitacion por Id.")
    @DeleteMapping("/{IdRoom}")
    public ResponseEntity Delete(
        @Parameter(
            description = "ID único de la habitación.",
            example = "101",
            required = true
        )
        @PathVariable int IdRoom) {
        Result result = serviceRoom.Delete(IdRoom);

        if (result.correct) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result.errorMessage);
        }
    }

    @GetMapping("/availableRooms")
    public ResponseEntity GetRooms() {
        Result result = serviceRoom.GetAvailableRooms();
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Filtro de habitaciones", description = "Endpoint que devuleve todas las habitaciones filtradas por tipo y disponibilidad (paginadas).")
    @GetMapping("/filter")
    public ResponseEntity RoomFilter(
            @Parameter(
                description = "Tipo de habitación",
                schema = @Schema(
                    allowableValues = {"Individual", "Doble", "Suite", "Familiar"}
                )
            )
            @RequestParam(required = false) String tipo,

            @Parameter(
                description = "Indica si la habitación está disponible",
                example = "true"
            )
            @RequestParam(required = false) Boolean disponible,

            @Parameter(
                description = "Número de página (empieza en 0)",
                example = "0"
            )
            @RequestParam(defaultValue = "0") int page,

            @Parameter(
                description = "Cantidad de registros por página",
                example = "10"
            )
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Result result = serviceRoom.RoomFilter(tipo, disponible, pageable);
        return ResponseEntity.ok(result);
    }

}
