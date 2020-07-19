/**
 * Copyright(C) [2020]  [Luvina Sotfware Company]
 * [Cart.java], [Jun 12, 2020] [Kiều Văn Quang]
 */
package manager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kiều Văn Quang
 *
 */
public class Cart {
	private List<Product> items;
	
	public Cart() {
		items = new ArrayList<>();
	}
	
	public void add(Product ci) {
		for (Product x: items) {
			if(ci.getId() == x.getId()) {
				x.setNumber(x.getNumber()+1);
				return;
			}
		}
		items.add(ci);
	}
	
	public void remove(int id) {
		for (Product x : items) {
			if(x.getId() == id) {
				items.remove(x);
				return;
			}
		}
	}
	
	public double getAmount() {
		double s = 0;
		for (Product x :  items) {
			s += x.getPrice() * x.getNumber();
		}
		return Math.round(s*100.0)/100.0;
	}
	
	public List<Product> getItems(){
		return items;
	}
	
	
	public void addAmount(int id, int amount) {
		Product p = new Product();
		p.setId(id);
		p.setNumber(amount);
		for (Product x: items) {
			if(p.getId() == x.getId()) {
				x.setNumber(amount);
				return;
			}
		}
		items.add(p);
	}
	

//	public static void main(String[] args) {
//		Cart c = new Cart();
//		Product p = new Product(1, "Ip", "Khong", 12, "goole.", "Lux", "Apple",1);
//		Product p2 = new Product(2, "Galyx", "Khong", 12, "goole.", "Lux", "SamSung",2);
//		Product p3 = new Product(3, "Nokia", "Khong", 12, "goole.", "Lux", "Nokia",3);
//		
//		c.add(p);
//		c.add(p2);
//		c.add(p3);
//		c.addAmount(1, 6);
//		System.out.println(c.getAmount());
//	}
}
