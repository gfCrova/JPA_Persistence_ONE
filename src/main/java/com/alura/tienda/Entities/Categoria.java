package com.alura.tienda.Entities;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @EmbeddedId
    private CategoriaId categoriaId;

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.categoriaId = new CategoriaId(nombre, "1234");
    }

    public String getNombre() {
        return categoriaId.getNombre();
    }

    public void setNombre(String nombre) {
        this.categoriaId.setNombre(nombre);
    }
}
