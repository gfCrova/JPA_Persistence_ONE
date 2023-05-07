package com.alura.tienda.Pruebas;

import com.alura.tienda.DAO.CategoriaDAO;
import com.alura.tienda.DAO.ClienteDAO;
import com.alura.tienda.DAO.PedidoDAO;
import com.alura.tienda.DAO.ProductoDAO;
import com.alura.tienda.Entities.*;
import com.alura.tienda.Utils.JPAUtils;
import com.alura.tienda.VO.RelatorioDeVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class RegistroDePedidos {

    public static void main(String[] args) {

        registrarProducto();

        EntityManager em = JPAUtils.getEntityManager();
        ProductoDAO productoDAO = new ProductoDAO(em);
        Producto producto = productoDAO.consultarPorId(1L);

        ClienteDAO clienteDAO = new ClienteDAO(em);
        PedidoDAO pedidoDAO = new PedidoDAO(em);

        Cliente cliente = new Cliente("Juan", "34567654");
        Pedido pedido = new Pedido(cliente);
        pedido.agregarItems(new ItemsPedido(5, producto, pedido));

        em.getTransaction().begin();

        clienteDAO.guardar(cliente);
        pedidoDAO.guardar(pedido);

        em.getTransaction().commit();

        BigDecimal valorTotal = pedidoDAO.valorTotalDeVenta();
        System.out.println("Valor Total: " + valorTotal);

        List<RelatorioDeVenta> relatorio = pedidoDAO.relatorioDeVentasVO();
        relatorio.forEach(System.out::println);
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
