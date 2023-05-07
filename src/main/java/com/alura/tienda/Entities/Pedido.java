package com.alura.tienda.Entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private final LocalDate fecha = LocalDate.now();
    private BigDecimal valorTotal = new BigDecimal(0);

    @ManyToOne(fetch = FetchType.LAZY) // -> "Un" Cliente puede tener "Muchos" pedidos
    private Cliente cliente;

    /*@ManyToMany // ≥ Un pedido puede tener "Muchos" -> productos (lista), y estos a la vez pueden tener "Muchos" -> pedidos
    @JoinTable(name="items_pedido") // Nueva tabla de relación pedidos - productos */
    // private List<Producto> productos;

    @OneToMany(mappedBy="pedido", cascade = CascadeType.ALL)  // mappedBy -> Conectar a la tabla indicada
    private List<ItemsPedido> items = new ArrayList<>(); // Cuando es lista la relación es "Uno" a "Muchos"

    public Pedido() {
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarItems(ItemsPedido item) {
        item.setPedido(this);
        this.items.add(item);
        this.valorTotal = this.valorTotal.add(item.getValor());
    }
}
