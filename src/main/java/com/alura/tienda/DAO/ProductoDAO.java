package com.alura.tienda.DAO;

import com.alura.tienda.Entities.Producto;

import javax.persistence.EntityManager;

public class ProductoDAO{

    private EntityManager em;

    public ProductoDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto) {
        this.em.persist(producto);
    }
}
