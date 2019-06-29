/**
 * 
 */
package com.svit.java.p1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Code is for study and personal use purpose, not for commercial use.
 * 
 * @author sv-it.org
 *
 */



/*
 * custom exception 
 */
class ItemException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	ItemException(String s){
		message = s;
	}
	
	public String toString() {
		return "ItemExecption[" + message + "]";
	}


}

/*
 * Tax class
 */
class Tax{
	public static final double SALE_TAX = 0.10;
	public static final double IMPORT_TAX = 0.05;
	
	private double saleTax = 0.0;
	private double importTax = 0.0;
	
	/*
	 * calculate item sale tax and import tax
	 */

	public void calculateItemTax(boolean isTaxable, boolean isImported, double price){
		if (isTaxable){
			saleTax = price*SALE_TAX;
		}	
		
		if (isImported){
			importTax = price*IMPORT_TAX;	
		}
	}
	
	/*
	 * calculate total item tax
	 */
	public double calculateItemTaxRate(){
		//Use ceil(double/0.05 )*0.05 to round the sales tax up to the nearest 0.05
		//return Math.ceil((this.saleTax + this.importTax)/0.05 )*0.05;
		return this.saleTax + this.importTax;
	}	

}

/*
 * define an Item interface
 */
interface Item{
	public static final int TYPE_BFM = 1; //books, food and medical products
	public static final int TYPE_OTHER_GOODS = 2;//all goods, except boos, food, and medical products
	public static final int TYPE_BFM_IMPORT = 3;//(imported)books, food and medical products
	public static final int TYPE_OTHER_GOODS_IMPORT = 4; //(imported)all goods, except boos, food, and medical products
	
	public double getGoodsTotalTax();
	public double getGoodsTotal() throws ItemException;
	
	public void setImported(boolean b);
	public String getDescription();
}

/*
 * define abstract class
 */
abstract class Goods implements Item{
	private String description;
	private int quantity;
	private double price;
	private Tax tax = new Tax();
	
	public Goods(String description, int quantity, double price){
		this.description = description;
		this.quantity = quantity;
		this.price = price;	
	}
	
	protected abstract boolean isTaxable();
	protected abstract boolean isImported();
	
	public double getGoodsTotalTax(){
		tax.calculateItemTax(isTaxable(), isImported(), price);

		
		return quantity * this.tax.calculateItemTaxRate();
	}
	
	public double getGoodsTotal() throws ItemException{
		if(tax==null)
			throw new ItemException("Tax should be calculated first!");
		
		return quantity * (this.tax.calculateItemTaxRate() + price);

	}
	
	public String getDescription(){
		return description;	
	}
	
	public String toString(){
		return (quantity + " " + description + ": ");	
	}
}

/*
 * BFM(book, food, medical)
 */
class BFM extends Goods{
	private boolean isTaxable = false; //BFM HAS NO local tax
	private boolean isImported = false; //default to false
	
	public BFM(String description, int quantity, double price){
		super(description, quantity, price);	
	}	
	
	public boolean isTaxable(){
		return isTaxable;	
	}
	
	public boolean isImported(){
		return isImported;
	}
	
	public void setImported(boolean b){
		isImported = b;	
	}
}

/*
 * All other goods except BFM(book, food, medical)
 */
class OtherGoods extends Goods{
	private boolean isTaxable = true; //OtherGoods HAS local tax
	private boolean isImported = false; //default to false
	
	public OtherGoods(String description, int quantity, double price){
		super(description, quantity, price);	
	}	
	
	public boolean isTaxable(){
		return isTaxable;	
	}
	
	public boolean isImported(){
		return isImported;	
	}
	
	public void setImported(boolean imp){
		isImported = imp;	
	}
}

/*
 * ItemFactory interface
 */
interface ItemsFactory{
	public Item getItem(int itemType, String description, int quantity, double price) throws ItemException;	
}

/*
 * ItemsFactoryImp implements ItemFactory
 * 
 */

class ItemsFactoryImp implements ItemsFactory{
	
	public Item getItem(int itemType, String description, int quantity, double price) throws ItemException{
		Item item = null;
		
		//apply factory design pattern

		if(itemType == Item.TYPE_BFM){
			item = new BFM(description, quantity, price);	
		} else if(itemType == Item.TYPE_BFM_IMPORT){
			item = new BFM(description, quantity, price);
			item.setImported(true);
		}else if(itemType == Item.TYPE_OTHER_GOODS){
			item = new OtherGoods(description, quantity, price);	
		} else if(itemType == Item.TYPE_OTHER_GOODS_IMPORT){
			item = new OtherGoods(description, quantity, price);
			item.setImported(true);	
		} else
			throw new ItemException("itemType : " + itemType + " is invalid.");
			
		return item;
	}
}

/*
 * Build shopping cart
 */
interface ShoppingCartBuilder{
	public void buildShoppingCart(int itemType, String description, int quantity, double price) throws ItemException;
	public double calculateCartTotalTax() throws ItemException;
	public double calculateCartGrandTotal() throws ItemException;
	public void printExtendedTaxedPrice() throws ItemException;
	public Iterator<Item> getIterator();
	public void clearCart();
}

class ShoppingCartBuilderImp implements ShoppingCartBuilder{
	private List<Item> items = null;
	
	private void addItem(Item item){
		if (items == null)
			items = new ArrayList<Item>();
		
		items.add(item);
	}
	

	

	public void buildShoppingCart(int itemType, String description, int quantity, double price) throws ItemException{
		ItemsFactory itemsFactory = new ItemsFactoryImp();
		Item item = itemsFactory.getItem(itemType, description, quantity, price);
		this.addItem(item);
	}
	
	public double calculateCartTotalTax() throws ItemException{
		double totalTax = 0.0;
		
		if (items == null)
			throw new ItemException("Shopping cart is empty");
		
		Iterator<Item> itemItr = items.iterator();
		
		while(itemItr.hasNext()){
			Item item = (Item)itemItr.next();
			totalTax += item.getGoodsTotalTax();	
		}
		
		return totalTax;
	}
	
	public double calculateCartGrandTotal() throws ItemException{
		double total = 0.0;
		
		if (items == null)
			throw new ItemException("Shopping cart is empty");	
		
		Iterator<Item> itemItr = items.iterator();
		
		while(itemItr.hasNext()){
			Item item = (Item)itemItr.next();
			total += item.getGoodsTotal();	
		}	
		
		return total;
	}
	
	
	public void printExtendedTaxedPrice() throws ItemException{
		if (items == null)
			throw new ItemException("Shopping cart is empty");
			
		Iterator<Item> itemItr = items.iterator();
		
		while(itemItr.hasNext()){
			Item item = (Item)itemItr.next();

			//output formatted value
			NumberFormat f = new DecimalFormat("0.00");
			System.out.println(item + " $" + f.format(item.getGoodsTotal()));
		}
	}
	
	public Iterator<Item> getIterator(){
		return items.iterator();
	}
	
	public void clearCart(){
		if (items != null)
			items.clear();	
	}
	
	
    public String toString() {
		NumberFormat f = new DecimalFormat("0.00");
		double totalTax = 0.0;
		double total = 0.0;
		try {
			totalTax = this.calculateCartTotalTax();
			total = this.calculateCartGrandTotal();
		} catch (ItemException e) {
			e.printStackTrace();
		}
    	
    	return "\nSales Taxes: " + f.format(totalTax) + "\n" + "Grand Total:" + f.format(total);
    }

}
/*
 * main function to simulate SALES TAXES OO solution
 */
public class SalesTaxesDemoMe{
	public static void main(String[] args) throws ItemException{
		//output formatted value
		NumberFormat f = new DecimalFormat("0.00");
					
		//Build shopping cart
		ShoppingCartBuilder builder = new ShoppingCartBuilderImp();
		
		//input 1:
		builder.buildShoppingCart(Item.TYPE_BFM, "book", 1, 12.49);
		builder.buildShoppingCart(Item.TYPE_BFM, "chocolate bar", 1, 0.85);
		builder.buildShoppingCart(Item.TYPE_OTHER_GOODS, "music CD", 1, 14.99);
		
		//print result of input 1
		System.out.println("\nShopping Cart input 1: ");
		double totalTax = builder.calculateCartTotalTax();
		builder.printExtendedTaxedPrice();
		System.out.println("Sales Taxes: " + f.format(totalTax));
		System.out.println("Grand Total:" + f.format(builder.calculateCartGrandTotal()));
		
		//Duplicate print
		System.out.println(builder); 	// to replace above two print statement
										//Sales Taxes: 1.50
										// Total:29.83
			
		builder.clearCart();
		
		//input 2
		builder.buildShoppingCart(Item.TYPE_BFM_IMPORT, "imported box of chocolates", 1, 10.00);
		builder.buildShoppingCart(Item.TYPE_OTHER_GOODS_IMPORT, "imported bottle of perfume", 1, 20.00);
		
		System.out.println("\nShopping Cart input 2: ");
		totalTax = builder.calculateCartTotalTax();
		builder.printExtendedTaxedPrice();
		System.out.println("Sales Taxes: " + f.format(totalTax));
		System.out.println("Total:" + f.format(builder.calculateCartGrandTotal()));
		
		builder.clearCart();
		
		//input 3
		builder.buildShoppingCart(Item.TYPE_BFM, "packet of headache pills", 1, 9.75);
		builder.buildShoppingCart(Item.TYPE_BFM_IMPORT, "box of imported chocolates", 1, 11.25);
		builder.buildShoppingCart(Item.TYPE_OTHER_GOODS, "bottle of perfume", 1, 18.99);
		builder.buildShoppingCart(Item.TYPE_OTHER_GOODS_IMPORT, "imported bottle of perfume", 1, 27.99);
		
		System.out.println("\nShopping Cart input 3: ");
		totalTax = builder.calculateCartTotalTax();
		builder.printExtendedTaxedPrice();		
		System.out.println("Sales Taxes: " + f.format(totalTax));
		System.out.println("Total:" + f.format(builder.calculateCartGrandTotal()));

	}

}
