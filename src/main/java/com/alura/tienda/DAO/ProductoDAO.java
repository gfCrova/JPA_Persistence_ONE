package com.alura.tienda.DAO;

import com.alura.tienda.Entities.Producto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductoDAO{

    private EntityManager em;

    public ProductoDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto) {
        this.em.persist(producto);
    }

    public Producto consultarPorId(Long id) {
        return em.find(Producto.class, id);
    }

    public List<Producto> consultarTodos() {
        String jpql = "SELECT P FROM producto AS P";
        return em.createQuery(jpql, Producto.class).getResultList();
    }
}
