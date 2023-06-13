package com.jbk.utility;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jbk.entity.Product;

public class ProductUtility {

	public static Product prepareProdcuData() {
		
		Scanner scanner =new Scanner(System.in);

		Product product = null;
//		Long productId = 0L;
//		Long supplierId = 0L;
//		Long categoryId = 0L;
//		Integer productQty = 0;
//		Double productPrice = 0.0;

		System.out.println("Enter the Product Id");
//		productId = Validate.checkLongNumber(productId);
		Long productId =scanner.nextLong();

		System.out.println("Enter the Product Name");
		String productName = scanner.next();

		System.out.println("Enter the Supplier Id");
//		supplierId = Validate.checkLongNumber(supplierId);
		Long supplierId=scanner.nextLong();

		System.out.println("Enter the Category Id");
//		categoryId = Validate.checkLongNumber(categoryId);
		Long categoryId =scanner.nextLong();

		System.out.println("Enter the Quantity");
//		productQty = Validate.checkIntegerNumber(productQty);
		Integer productQty =scanner.nextInt();
		

		System.out.println("Enter the Price");
//		productPrice = Validate.checkDoubleNumber(productPrice);
		Double productPrice =scanner.nextDouble();

		product = new Product(productId, productName, supplierId, categoryId, productQty, productPrice);
		
		boolean isValid = validate(product);
		
		if(isValid) {
			return product;
		}else {
			return null;
		}

		
	}
	
	public static   boolean validate(Product product) {
		
		boolean isValid=true;
		
		if(product.getProductId() <=0) {
			isValid=false;
			return isValid;
		}
		
		if(product.getProductName() == null) {
			isValid=false;
			return isValid;
	       
		}else {
			
			

	        String regex = ".*\\d.*";  
	        Pattern pattern = Pattern.compile(regex); 

	        Matcher matcherText = pattern.matcher(product.getProductName());
	        Boolean anyDIgit = matcherText.matches();
	        
	        if(anyDIgit) {
	        	isValid=false;
				return isValid;
	        }
		}
		
		if(product.getCategoryId() <=0) {
			isValid=false;
			return isValid;
		}
		
		if(product.getSupplierId() <=0) {
			isValid=false;
			return isValid;
		}
		
		if(product.getProductQTY() <=0) {
			isValid=false;
			return isValid;
		}
		
		if(product.getProductPrice() <=0) {
			isValid=false;
			return isValid;
		}
		
		return isValid;
		
	}

}
