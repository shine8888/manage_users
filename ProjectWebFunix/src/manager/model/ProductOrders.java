/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [ProductOrders.java], [Jun 12, 2020] [Kiều Văn Quang]
 */
package manager.model;

/**
 * @author Kiều Văn Quang
 *
 */
public class ProductOrders {
	private int orderId;
	private int productId;
	private int amountProduct;
	private String nameProduct;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getAmountProduct() {
		return amountProduct;
	}
	public void setAmountProduct(int amountProduct) {
		this.amountProduct = amountProduct;
	}
	public String getNameProduct() {
		return nameProduct;
	}
	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}
	
	
}
