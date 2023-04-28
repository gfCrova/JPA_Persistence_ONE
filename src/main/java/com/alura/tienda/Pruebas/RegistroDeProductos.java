package com.alura.tienda.Pruebas;

import com.alura.tienda.DAO.CategoriaDAO;
import com.alura.tienda.DAO.ProductoDAO;
import com.alura.tienda.Entities.Categoria;
import com.alura.tienda.Entities.Producto;
import com.alura.tienda.Utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class RegistroDeProductos {

    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");
        Producto celular = new Producto("Samsung", "Celular Usado", new BigDecimal("1000"), celulares);

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDAO productoDAO = new ProductoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();

        em.persist(celulares);
        celulares.setNombre("SMARTPHONES");

        //productoDAO.guardar(celular);
        //categoriaDAO.guardar(celulares);

        em.flush(); // flush() -> sincronizar
        em.clear();

        celulares = em.merge(celulares); // merge(T) -> Traer los registros deseados con estado Managed.
        celulares.setNombre("CELULARES");  //  Al estar después del clear() se encuentra en estado Detached. A no ser que haya un merge() antes.

        em.flush();
        em.remove(celulares);
        em.flush();
    }
}
