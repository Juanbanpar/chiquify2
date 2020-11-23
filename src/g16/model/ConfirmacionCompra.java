package g16.model;

import java.io.Serializable;
import java.util.List;

public class ConfirmacionCompra implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String tarjeta;
    private double cantidad;
    private List<Item> productos;
    private String id;
    private String vendedor;
    
    

	public ConfirmacionCompra(String tarjeta, double cantidad, List<Item> productos, String id, String vendedor) {
		super();
		this.tarjeta = tarjeta;
		this.cantidad = cantidad;
		this.productos = productos;
		this.id = id;
		this.vendedor=vendedor;
	}

	public ConfirmacionCompra() {
		super();
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	
	public List<Item> getProductos() {
		return productos;
	}

	public void setProductos(List<Item> productos) {
		this.productos = productos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}