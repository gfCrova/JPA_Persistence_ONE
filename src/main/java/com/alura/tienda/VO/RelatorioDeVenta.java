package com.alura.tienda.VO;

import java.time.LocalDate;

public class RelatorioDeVenta {

    private String nombreDelProducto;
    private Long cantidadDeProducto;
    private LocalDate fechaUltimaVenta;

    public RelatorioDeVenta(String nombreDelProducto, Long cantidadDeProducto, LocalDate fechaUltimaVenta) {
        this.nombreDelProducto = nombreDelProducto;
        this.cantidadDeProducto = cantidadDeProducto;
        this.fechaUltimaVenta = fechaUltimaVenta;
    }

    public String getNombreDelProducto() {
        return nombreDelProducto;
    }

    public void setNombreDelProducto(String nombreDelProducto) {
        this.nombreDelProducto = nombreDelProducto;
    }

    public Long getCantidadDeProducto() {
        return cantidadDeProducto;
    }

    public void setCantidadDeProducto(Long cantidadDeProducto) {
        this.cantidadDeProducto = cantidadDeProducto;
    }

    public LocalDate getFechaUltimaVenta() {
        return fechaUltimaVenta;
    }

    public void setFechaUltimaVenta(LocalDate fechaUltimaVenta) {
        this.fechaUltimaVenta = fechaUltimaVenta;
    }

    @Override
    public String toString() {
        return "RelatorioDeVenta { \n" +
                "nombreDelProducto: '" + nombreDelProducto + '\'' + "\n" +
                "cantidadDeProducto: " + cantidadDeProducto + "\n" +
                "fechaUltimaVenta: " + fechaUltimaVenta + "\n" +
                '}';
    }
}
