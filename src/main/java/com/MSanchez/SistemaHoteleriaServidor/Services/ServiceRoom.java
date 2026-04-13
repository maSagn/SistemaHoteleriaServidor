package com.MSanchez.SistemaHoteleriaServidor.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryBooking;
import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryRoom;
import com.MSanchez.SistemaHoteleriaServidor.Models.Result;
import com.MSanchez.SistemaHoteleriaServidor.Models.Room;

@Service
public class ServiceRoom {

    @Autowired
    private IRepositoryRoom iRepositoryRoom;

    @Autowired
    private IRepositoryBooking iRepositoryBooking;

    public Result GetAll(Room room, Pageable pageable) {
        Result result = new Result();

        try {
            if (room == null) {
                Page<Room> page = iRepositoryRoom.findAll(pageable);

                result.object = page.getContent();
                result.totalRecords = page.getTotalElements();
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result GetByRoomsAvailable(String type) {
        Result result = new Result();

        try {
            List<Room> rooms = iRepositoryRoom.findByType(type);
            result.object = rooms;
            result.correct = true;
            
        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result GetById(int IdRoom) {
        Result result = new Result();

        try {
            Optional<Room> room = iRepositoryRoom.findById(IdRoom);

            if (room.isPresent()) {
                result.object = room;
                result.correct = true;
            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result Add(Room room) {
        Result result = new Result();

        try {
            room.setIsAvailable(true);
            Room savedRoom = iRepositoryRoom.save(room);
            result.object = savedRoom;
            result.correct = true;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result Update(Room room, int IdRoom) {
        Result result = new Result();

        try {
            Optional<Room> roomFind = iRepositoryRoom.findById(IdRoom);

            if (roomFind.isPresent()) {
                Room roomExistente = roomFind.get();

                if (room.getRoomNumber() != null) {
                    roomExistente.setRoomNumber(room.getRoomNumber());
                }

                if (room.getType() != null) {
                    roomExistente.setType(room.getType());
                }

                if (room.getPricePerNight() != 0) {
                    roomExistente.setPricePerNight(room.getPricePerNight());
                }

                // if (room.isIsAvailable()) {
                //     roomExistente.setIsAvailable(room.isIsAvailable());
                // }
                roomExistente.setIsAvailable(true);

                if (room.getMaxGuests() != 0) {
                    roomExistente.setMaxGuests(room.getMaxGuests());
                }

                if (room.getDescription() != null) {
                    roomExistente.setDescription(room.getDescription());
                }

                Room savedRoom = iRepositoryRoom.save(roomExistente);
                result.object = savedRoom;
                result.correct = true;

            }

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
        }

        return result;
    }

    public Result Delete(int IdRoom) {
        Result result = new Result();

        try {
            Optional<Room> roomFind = iRepositoryRoom.findById(IdRoom);

            if (roomFind.isPresent()) {

                long ActiveRoom = iRepositoryBooking.countRecordsByIdRoom(IdRoom);

                if (ActiveRoom > 0) {
                    result.errorMessage = "No se puede eliminar la habitacion porque tiene reservas activas.";
                    result.correct = false;
                    return result;
                    
                }
                iRepositoryRoom.deleteById(IdRoom);
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
