package com.MSanchez.SistemaHoteleriaServidor;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryBooking;
import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryRoom;
import com.MSanchez.SistemaHoteleriaServidor.Models.Result;
import com.MSanchez.SistemaHoteleriaServidor.Models.Room;
import com.MSanchez.SistemaHoteleriaServidor.Services.ServiceRoom;

@ExtendWith(MockitoExtension.class)
public class RoomTest {

    @Mock
    private IRepositoryRoom iRepositoryRoom;

    @Mock 
    private IRepositoryBooking iRepositoryBooking;

    @InjectMocks
    private ServiceRoom serviceRoom;

    @Test
    public void GetAll() {

        //Creamos la room simulada
        Room roomMock = new Room();

        roomMock.setIdRoom(1);
        roomMock.setRoomNumber("202A");
        roomMock.setType("Individual");
        roomMock.setPricePerNight(300);
        roomMock.setIsAvailable(true);
        roomMock.setMaxGuests(1);
        roomMock.setDescription("Prueba mock 1");

        Room roomMock2 = new Room();

        roomMock2.setIdRoom(2);
        roomMock2.setRoomNumber("294");
        roomMock2.setType("Suite");
        roomMock2.setPricePerNight(2500);
        roomMock2.setIsAvailable(true);
        roomMock2.setMaxGuests(6);
        roomMock2.setDescription("Prueba mock 2");

        Room roomMock3 = new Room();

        roomMock3.setIdRoom(3);
        roomMock3.setRoomNumber("132B");
        roomMock3.setType("Familiar");
        roomMock3.setPricePerNight(1400);
        roomMock3.setIsAvailable(true);
        roomMock3.setMaxGuests(4);
        roomMock3.setDescription("Prueba mock 3");

        List<Room> roomsMock = Arrays.asList(roomMock, roomMock, roomMock3);

        PageRequest pageable = PageRequest.of(0,10);

        Page<Room> pageMock = new PageImpl<>(roomsMock);

        when(iRepositoryRoom.findAll(pageable)).thenReturn(pageMock);

        Result result = serviceRoom.GetAll(null, pageable);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNotNull(result.object);

    }

    @Test
    public void Add() {

        //Creamos la room simulada
        Room roomMock = new Room();

        roomMock.setIdRoom(65);
        roomMock.setRoomNumber("321");
        roomMock.setType("Doble");
        roomMock.setPricePerNight(300);
        roomMock.setIsAvailable(true);
        roomMock.setMaxGuests(2);
        roomMock.setDescription("Prueba mock add");

        when(iRepositoryRoom.save(roomMock)).thenReturn(roomMock);

        Result result = serviceRoom.Add(roomMock);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNotNull(result.object);

        // Verificar que save fue llamado una vez
        verify(iRepositoryRoom, times(1)).save(roomMock);
    }

    @Test
    public void Update() {

        //Creamos la room simulada
        Room roomMock = new Room();

        roomMock.setIdRoom(89);
        roomMock.setRoomNumber("321");
        roomMock.setType("Suite");
        roomMock.setPricePerNight(9000);
        roomMock.setIsAvailable(true);
        roomMock.setMaxGuests(10);
        roomMock.setDescription("Prueba mock add jakusi");

        // Objeto con nuevos datos (el que quiero actualizar)
        Room roomMockUpdate = new Room();
        roomMockUpdate.setType("Individual");

        int IdRoom = 89;

        when(iRepositoryRoom.findById(IdRoom)).thenReturn(Optional.of(roomMock));
        when(iRepositoryRoom.save(roomMock)).thenReturn(roomMock);

        Result result = serviceRoom.Update(roomMockUpdate, IdRoom);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNotNull(result.object);

        //Validar que si se actualizo
        Assertions.assertEquals("Individual", roomMock.getType());

        // Verificar que save fue llamado una vez
        verify(iRepositoryRoom, times(1)).save(roomMock);
    }

    @Test
    public void Delete() {
        //Creamos la room simulada
        Room roomMock = new Room();

        roomMock.setIdRoom(213);
        roomMock.setRoomNumber("332G");
        roomMock.setType("Suite");
        roomMock.setPricePerNight(7600);
        roomMock.setIsAvailable(true);
        roomMock.setMaxGuests(6);
        roomMock.setDescription("Suite con vista al mar");

        int IdRoom = 213;

        when(iRepositoryRoom.findById(IdRoom)).thenReturn(Optional.of(roomMock));

        Result result = serviceRoom.Delete(IdRoom);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.object);
    }

}
