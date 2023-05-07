package com.alura.tienda.DAO;

import com.alura.tienda.Entities.Pedido;
import com.alura.tienda.Entities.Producto;
import com.alura.tienda.VO.RelatorioDeVenta;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDAO {

    private EntityManager em;

    public PedidoDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public void remover(Pedido pedido) {
        pedido = this.em.merge(pedido);
        this.em.remove(pedido);
    }

    public Pedido consultarPorId(Long id) {
        return em.find(Pedido.class, id);
    }

    public List<Pedido> consultarTodos() {
        String jpql = "SELECT p FROM Producto AS p";
        return em.createQuery(jpql, Pedido.class).getResultList();
    }

    // Función de Agregación SUM()
    public BigDecimal valorTotalDeVenta() {
        String jpql = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(jpql, BigDecimal.class).getSingleResult();
    }

    public List<RelatorioDeVenta> relatorioDeVentasVO() {
        String jpql = "SELECT new com.alura.tienda.VO.RelatorioDeVenta(producto.nombre," +
                "SUM(item.cantidad)," +
                "MAX(pedido.fecha)) " +
                "FROM Pedido pedido " +
                "JOIN pedido.items item " +
                "JOIN item.producto producto " +
                "GROUP BY producto.nombre " +
                "ORDER BY item.cantidad DESC";
        return em.createQuery(jpql, RelatorioDeVenta.class).getResultList();
    }
}
