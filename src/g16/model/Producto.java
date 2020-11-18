package g16.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idproduct;

	private String categoria;

	private String descripcion;

	private String estado;

	private String imagen;

	private int precio;

	private String titulo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="comprador")
	private Usuario comprador;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="vendedor")
	private Usuario vendedor;

	public Producto() {
	}

	public int getIdproduct() {
		return this.idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getCategoria() {
		return this.categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getImagen() {
		return this.imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public int getPrecio() {
		return this.precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Usuario getComprador() {
		return this.comprador;
	}

	public void setUsuario1(Usuario comprador) {
		this.comprador = comprador;
	}

	public Usuario getVendedor() {
		return this.vendedor;
	}

	public void setUsuario2(Usuario vendedor) {
		this.vendedor = vendedor;
	}

}