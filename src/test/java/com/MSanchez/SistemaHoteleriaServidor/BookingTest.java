package com.MSanchez.SistemaHoteleriaServidor;

import static org.mockito.Mockito.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryBooking;
import com.MSanchez.SistemaHoteleriaServidor.DAO.IRepositoryRoom;
import com.MSanchez.SistemaHoteleriaServidor.Models.Booking;
import com.MSanchez.SistemaHoteleriaServidor.Models.Result;
import com.MSanchez.SistemaHoteleriaServidor.Models.Room;
import com.MSanchez.SistemaHoteleriaServidor.Models.Usuario;
import com.MSanchez.SistemaHoteleriaServidor.Services.ServiceBooking;

@ExtendWith(MockitoExtension.class)
public class BookingTest {

    @Mock
    private IRepositoryBooking iRepositoryBooking;

    @Mock
    private IRepositoryRoom iRepositoryRoom;

    @InjectMocks
    private ServiceBooking serviceBooking;

    @Test
    public void GetById() throws ParseException {

        //Creamos la booking simulada
        Booking bookingMock = new Booking();

        bookingMock.setIdBooking(1);
        bookingMock.setCheckIn(new SimpleDateFormat("yyyy-MM-dd").parse("2001-03-31"));
        bookingMock.setCheckOut(new SimpleDateFormat("yyyy-MM-dd").parse("2001-04-02"));
        bookingMock.setTotalPrice(2500);
        bookingMock.setStatus("Confirmed");
        bookingMock.setCreatedAt(new Date());
        bookingMock.Room = new Room();
        bookingMock.Room.setIdRoom(432);
        bookingMock.Usuario = new Usuario();
        bookingMock.Usuario.setIdUsuario(3);

        int IdBooking = 1;

        when(iRepositoryBooking.findById(IdBooking)).thenReturn(Optional.of(bookingMock));

        Result result = serviceBooking.GetById(IdBooking);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNotNull(result.object);
    }

    @Test
    public void Add() throws ParseException {

        //Creamos la room simulada
        Room roomMock = new Room();

        roomMock.setIdRoom(1);
        roomMock.setRoomNumber("202A");
        roomMock.setType("Individual");
        roomMock.setPricePerNight(300);
        roomMock.setIsAvailable(true);
        roomMock.setMaxGuests(1);
        roomMock.setDescription("Prueba mock 1");

        //Creamos la booking simulada
        Booking bookingMock = new Booking();

        bookingMock.setIdBooking(1);
        bookingMock.setCheckIn(new SimpleDateFormat("yyyy-MM-dd").parse("2001-03-31"));
        bookingMock.setCheckOut(new SimpleDateFormat("yyyy-MM-dd").parse("2001-04-02"));
        bookingMock.setTotalPrice(2500);
        bookingMock.setStatus("Confirmed");
        bookingMock.setCreatedAt(new Date());

        bookingMock.setRoom(roomMock);

        bookingMock.Usuario = new Usuario();
        bookingMock.Usuario.setIdUsuario(3);

        when(iRepositoryBooking.save(bookingMock)).thenReturn(bookingMock);

        when(iRepositoryRoom.RoomAvailable(1)).thenReturn(true);

        Result result = serviceBooking.Add(bookingMock);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNotNull(result.object);

        // Verificar que save fue llamado una vez
        verify(iRepositoryBooking, times(1)).save(bookingMock);
    }

    @Test
    public void CancelarReserva() throws ParseException {

        //Creamos la booking simulada
        Booking bookingMock = new Booking();

        bookingMock.setIdBooking(1);
        bookingMock.setCheckIn(new SimpleDateFormat("yyyy-MM-dd").parse("2001-03-31"));
        bookingMock.setCheckOut(new SimpleDateFormat("yyyy-MM-dd").parse("2001-04-02"));
        bookingMock.setTotalPrice(2500);
        bookingMock.setStatus("Confirmed");
        bookingMock.setCreatedAt(new Date());

        bookingMock.Room = new Room();
        bookingMock.Room.setIdRoom(21);

        bookingMock.Usuario = new Usuario();
        bookingMock.Usuario.setIdUsuario(3);

        int IdBooking = 1;

        when(iRepositoryBooking.findById(IdBooking)).thenReturn(Optional.of(bookingMock));
        when(iRepositoryBooking.save(bookingMock)).thenReturn(bookingMock);

        Result result = serviceBooking.Update(IdBooking);

        Assertions.assertTrue(result.correct);
        Assertions.assertNull(result.errorMessage);
        Assertions.assertNotNull(result.object);

        //Validar que si se actualizo
        Assertions.assertEquals("Cancelled", bookingMock.getStatus());

    }

}
