package Abismo;

/**
 * Created by DIURNO_2 on 10/01/2017.
 */

public class Producto {
    String nombre;
    double precio;
    double total;
    int cantidad;

    public Producto (String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = 0;
        this.total = 0;
    }

    private void calculaTotal() {
        this.total = this.precio*this.cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
        calculaTotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        calculaTotal();
    }

    public double getTotal() {
        return total;
    }
    
    @Override
    public String toString() {
        return nombre+": "+precio+", "+cantidad+" unidades.";
    }
}
