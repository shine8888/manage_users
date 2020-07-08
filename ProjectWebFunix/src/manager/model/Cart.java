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
}
