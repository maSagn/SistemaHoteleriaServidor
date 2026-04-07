package com.MSanchez.SistemaHoteleriaServidor.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryRoom;
import com.MSanchez.SistemaHoteleriaServidor.Models.Result;
import com.MSanchez.SistemaHoteleriaServidor.Models.Room;

@Service
public class ServiceRoom {

    @Autowired
    private IRepositoryRoom iRepositoryRoom;

    public Result GetAll() {
        Result result = new Result();

        try {
            List<Room> rooms = iRepositoryRoom.findAll();
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

                if (room.isIsAvailable()) {
                    roomExistente.setIsAvailable(room.isIsAvailable());
                }

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
