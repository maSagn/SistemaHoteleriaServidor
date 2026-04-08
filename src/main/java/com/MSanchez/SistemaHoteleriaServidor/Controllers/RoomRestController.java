package com.MSanchez.SistemaHoteleriaServidor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("api/rooms")
public class RoomRestController {

    @Autowired
    private ServiceRoom serviceRoom;

    @GetMapping
    public ResponseEntity GetAll() {
        Result result = serviceRoom.GetAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/available")
    public ResponseEntity GetRoomsAvailable(@RequestParam(required = false) String type) {
        Result result = serviceRoom.GetByRoomsAvailable(type);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{IdRoom}")
    public ResponseEntity GetById(@PathVariable int IdRoom) {
        Result result = serviceRoom.GetById(IdRoom);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity Add(@RequestBody Room room) {
        Result result = serviceRoom.Add(room);
        return ResponseEntity.ok(result);
    }
    
    @PatchMapping("/{IdRoom}")
    public ResponseEntity Update(@RequestBody Room room, @PathVariable int IdRoom) {
        Result result = serviceRoom.Update(room, IdRoom);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{IdRoom}")
    public ResponseEntity Delete(@PathVariable int IdRoom) {
        Result result = serviceRoom.Delete(IdRoom);
        return ResponseEntity.ok(result);
    }
}
