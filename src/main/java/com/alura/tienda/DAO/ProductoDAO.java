package com.alura.tienda.DAO;

import com.alura.tienda.Entities.Producto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductoDAO {

    private EntityManager em;

    public ProductoDAO(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto) {
        this.em.persist(producto);
    }

    public void remover(Producto producto) {
        producto = this.em.merge(producto);
        this.em.remove(producto);
    }

    public Producto consultarPorId(Long id) {
        return em.find(Producto.class, id);
    }

    public List<Producto> consultarTodos() {
        String jpql = "SELECT p FROM Producto AS p";
        return em.createQuery(jpql, Producto.class).getResultList();
    }

    public List<Producto> consultaPorNombre(String nombre) {
        String jpql = "SELECT p FROM Producto AS p WHERE p.nombre=:nombre";
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> consultarPorNombreCategoria(String nombre) {
        String jpql = "SELECT p FROM Producto AS p WHERE p.categoria.nombre=:nombre";
        return em.createQuery(jpql, Producto.class).setParameter("nombre", nombre).getResultList();
    }

    public BigDecimal consultarPrecioPorNombreProducto(String nombre) {
        return em.createNamedQuery("Producto.consultaDePrecio", BigDecimal.class).setParameter("nombre", nombre).getSingleResult();
    }

    // Parámetros
    public List<Producto> consultarPorParametros(String nombre, BigDecimal precio, LocalDate fecha) {
        StringBuilder jpql = new StringBuilder("SELECT p FROM Producto p WHERE 1=1 ");
        if (nombre != null && !nombre.trim().isEmpty()) {
            jpql.append("AND p.nombre = : nombre");
        }
        if (precio != null && !precio.equals(new BigDecimal(0))) {
            jpql.append("AND p.precio = : precio");
        }
        if (fecha != null) {
            jpql.append("AND p.fechaDeRegistro = : fecha");
        }

        TypedQuery<Producto> consulta = em.createQuery(jpql.toString(), Producto.class);

        if (nombre != null && !nombre.trim().isEmpty()) {
            consulta.setParameter("nombre", nombre);
        }
        if (precio != null && !precio.equals(new BigDecimal(0))) {
            consulta.setParameter("precio", precio);
        }
        if (fecha != null) {
            consulta.setParameter("fechaDeRegistro", fecha);
        }

        return consulta.getResultList();
    }

    // Criteria API
    public List<Producto> consultarPorParametrosConAPICriteria(String nombre, BigDecimal precio, LocalDate fecha) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Producto> query = builder.createQuery(Producto.class);
        Root<Producto> from = query.from(Producto.class);

        Predicate filtro = builder.and();
        if (nombre != null && !nombre.trim().isEmpty()) {
            filtro=builder.and(filtro, builder.equal(from.get("nombre"), nombre));
        }
        if (precio != null && !precio.equals(new BigDecimal(0))) {
            filtro=builder.and(filtro, builder.equal(from.get("precio"), precio));
        }
        if (fecha != null) {
            filtro=builder.and(filtro, builder.equal(from.get("fechaDeRegistro"), fecha));
        }

        query = query.where(filtro);
        return em.createQuery(query).getResultList();
    }
}
