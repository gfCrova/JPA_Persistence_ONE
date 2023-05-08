package com.alura.tienda.Entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CategoriaId implements Serializable {
    private String nombre;
    private String password;

    public CategoriaId() {
    }

    public CategoriaId(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoriaId that = (CategoriaId) o;

        if (!nombre.equals(that.nombre)) return false;
        return password.equals(that.password);
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
