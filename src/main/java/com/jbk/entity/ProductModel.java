package com.jbk.entity;

public class ProductModel {

	private Object productName;
	private Object productQTY;
	

	public ProductModel() {
		super();
		
	}
	
	

	public ProductModel(Object productName, Object productQTY) {
		super();
		this.productName = productName;
		this.productQTY = productQTY;
	}



	public Object getProductName() {
		return productName;
	}



	public void setProductName(Object productName) {
		this.productName = productName;
	}



	public Object getProductQTY() {
		return productQTY;
	}



	public void setProductQTY(Object productQTY) {
		this.productQTY = productQTY;
	}



	@Override
	public String toString() {
		return "ProductModel [productName=" + productName + ", productQTY=" + productQTY + "]";
	}



	


	

}
