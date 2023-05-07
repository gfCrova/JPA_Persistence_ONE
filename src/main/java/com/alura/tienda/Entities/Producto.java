package com.alura.tienda.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "productos")
@NamedQuery(name="Producto.consultaDePrecio", query="SELECT p.precio FROM Producto AS p WHERE p.nombre=:nombre")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private LocalDate fechaDeRegistro = LocalDate.now();
    @ManyToOne  // "Una" categoría tiene "Muchos" productos
    private Categoria categoria;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, BigDecimal precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public LocalDate getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(LocalDate fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }
}
