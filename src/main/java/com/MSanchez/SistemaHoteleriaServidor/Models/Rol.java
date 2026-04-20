package com.MSanchez.SistemaHoteleriaServidor.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol")
    private int IdRol;

    @Column(name = "nombre")
    private String Nombre;
    
    public int getIdRol() {
        return IdRol;
    }
    public void setIdRol(int idRol) {
        IdRol = idRol;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
