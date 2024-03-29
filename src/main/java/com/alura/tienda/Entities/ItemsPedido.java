package com.alura.tienda.Entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="items_pedido")
public class ItemsPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private int cantidad;
    private BigDecimal precioUnitario;


    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;


    public ItemsPedido() {
    }

    public ItemsPedido(int cantidad, Producto producto, Pedido pedido) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.pedido = pedido;
        this.precioUnitario = producto.getPrecio();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }


    public BigDecimal getValor() {
        return this.precioUnitario.multiply(new BigDecimal(this.cantidad));
    }
}
