package com.alura.tienda.Pruebas;

import com.alura.tienda.DAO.CategoriaDAO;
import com.alura.tienda.DAO.ProductoDAO;
import com.alura.tienda.Entities.Categoria;
import com.alura.tienda.Entities.Producto;
import com.alura.tienda.Utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PruebaDeParametros {
    public static void main(String[] args) {
        cargaBancoDeDatos();

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDAO = new ProductoDAO(em);

        List<Producto> resultado = productoDAO.consultarPorParametros("FIFA", null, null);
        System.out.println(resultado.get(0).getDescripcion());
    }

    public static void cargaBancoDeDatos() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria videoJuegos = new Categoria("VIDEO_JUEGOS");
        Categoria electronicos = new Categoria("ELECTRONICOS");

        Producto celular = new Producto("X", "producto nuevo", new BigDecimal(10000), celulares);
        Producto videoJuego = new Producto("FIFA", "2000", new BigDecimal(10000), videoJuegos);
        Producto memoria = new Producto("memoria ram", "30 GB", new BigDecimal(10000), electronicos);

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDAO = new ProductoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();

        categoriaDAO.guardar(celulares);
        categoriaDAO.guardar(videoJuegos);
        categoriaDAO.guardar(electronicos);

        productoDAO.guardar(celular);
        productoDAO.guardar(videoJuego);
        productoDAO.guardar(memoria);

        em.getTransaction().commit();
        em.close();
    }
}
