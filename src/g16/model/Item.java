package g16.model;

public class Item {

	private Producto product;
	private int quantity;

	/*
	 * Esta clase contiene el constructor de un item del carrito, es decir, un producto con su cantidad
	 */
	
	public Item() {
	}

	public Item(Producto product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Producto getProduct() {
		return product;
	}

	public void setProduct(Producto product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
