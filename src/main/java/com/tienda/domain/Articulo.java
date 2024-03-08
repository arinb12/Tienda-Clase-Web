package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "articulo")

public class Articulo implements Serializable { //serializacion porque se va almacenar ciertos datos en el disco

    private static final long serialVersionUID = 1L; //para poder hacer el ciclo de la sumatoria de la articulo.

    @Id //id articulo es la llave de la tabla articulo. 
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Los valores generados que estrategia usan, identico a la BD 
    @Column(name = "id_articulo") //decir cual es el nombre en la base de datos. Se hace la asociación 
    private long idArticulo;
    private String descripcion;
    private String rutaImagen;
    private boolean stock;

    public Articulo() {
    }

    public Articulo(String descripcion, boolean stock) {
        this.descripcion = descripcion;
        this.stock = stock;
    }

}