package com.alura.tienda.Pruebas;

import com.alura.tienda.DAO.PedidoDAO;
import com.alura.tienda.Entities.Pedido;
import com.alura.tienda.Utils.JPAUtils;

import javax.persistence.EntityManager;
import java.io.FileNotFoundException;

public class PruebaDeDesempenho {
    public static void main(String[] args) throws FileNotFoundException {
//        LoadRecords.cargarRegistros();
        EntityManager em = JPAUtils.getEntityManager();
        PedidoDAO pedidoDao = new PedidoDAO(em);
        Pedido pedidoConCliente = pedidoDao.consultarPedidoConCliente(2l);
        em.close();

//        System.out.println(pedido.getFecha());
//        System.out.println(pedido.getItems().size());
        System.out.println(pedidoConCliente.getCliente().getNombre());
    }
}