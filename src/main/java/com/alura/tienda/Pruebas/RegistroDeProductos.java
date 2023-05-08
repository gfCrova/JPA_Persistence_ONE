package com.alura.tienda.Pruebas;

import com.alura.tienda.DAO.CategoriaDAO;
import com.alura.tienda.DAO.ProductoDAO;
import com.alura.tienda.Entities.Categoria;
import com.alura.tienda.Entities.CategoriaId;
import com.alura.tienda.Entities.Producto;
import com.alura.tienda.Utils.JPAUtils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDeProductos {

    public static void main(String[] args) {

        registrarProducto();

        EntityManager em = JPAUtils.getEntityManager();

        ProductoDAO productoDAO = new ProductoDAO(em);

        // Consultar Por sID
        Producto producto1 = productoDAO.consultarPorId(1L);
        System.out.println(producto1.getNombre());

        // Consultar TODOS
        List<Producto> productoList = productoDAO.consultarTodos();
        productoList.forEach(producto -> System.out.println(producto.getDescripcion()));

        // Consultar Por NOMBRE
        List<Producto> productoPorNombre = productoDAO.consultaPorNombre("Samsung S20");
        productoPorNombre.forEach(producto -> System.out.println(producto.getPrecio()));

        /* Consultar Por NOMBRE De CATEGORIA
        List<Producto> productoPorNombreCat = productoDAO.consultarPorNombreCategoria("TVs");
        productoPorNombreCat.forEach(producto -> System.out.println(producto.getNombre()));*/

        // Consultar Precio Por Nombre
        BigDecimal precioPorNombre = productoDAO.consultarPrecioPorNombreProducto("Noblex");
        System.out.println(precioPorNombre);

        Categoria find = em.find(Categoria.class, new CategoriaId("SMARTPHONES", "1234"));
        System.out.println(find.getNombre());
    }

    private static void registrarProducto() {
        Categoria celulares = new Categoria("CELULARES");
        Categoria televisores = new Categoria("TVs");
        Producto celular = new Producto("Samsung S20", "Usado 3gb RAM, 64gb Almacenamiento", new BigDecimal("40000"), celulares);
        Producto tv = new Producto("Noblex", "Nuevo 32' HD", new BigDecimal("55000"), televisores);


        EntityManager em = JPAUtils.getEntityManager();

        ProductoDAO productoDAO = new ProductoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        em.getTransaction().begin();

        em.persist(celulares);
        em.persist(televisores);
        celulares.setNombre("SMARTPHONES");

        productoDAO.guardar(celular);
        productoDAO.guardar(tv);
        categoriaDAO.guardar(celulares);
        categoriaDAO.guardar(televisores);

        em.getTransaction().commit();
        em.close();

        /*
        em.flush(); // flush() -> sincronizar
        em.clear();

        celulares = em.merge(celulares); // merge(T) -> Traer los registros deseados con estado Managed.
        celulares.setNombre("CELULARES");  //  Al estar después del clear() se encuentra en estado Detached. A no ser que haya un merge() antes.

        em.flush();
        em.remove(celulares);
        em.flush();
        */

    }
}
