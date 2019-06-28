/**
 * 
 */
package com.svit.java.p1;


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

}

/*
 * Tax class
 */
class Tax{
	public static final double SALE_TAX = 0.10;
	public static final double IMPORT_TAX = 0.05;
	
	private double saleTax = 0.0;
	private double importTax = 0.0;
	

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
		//TODO
		return 0.0;//placeholder
	}
	
	public double getGoodsTotal() throws ItemException{
		//TODO
		return 0.0;//placeholder
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

			
		return item;
	}
}

/*
 * main function to simulate SALES TAXES OO solution
 */
public class SalesTaxesDemoMe{
	public static void main(String[] args) throws ItemException{


	}

}
