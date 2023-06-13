package com.jbk;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;
import com.jbk.entity.ProductModel;
import com.jbk.utility.ProductUtility;
import com.jbk.utility.Validate;

public class ProductController {

	public static void main(String[] args) {

		ProductDao productDao = new ProductDao();
		Scanner scanner = new Scanner(System.in);
		char c;

		do {

			System.out.println("Press 1 for Save Product");
			System.out.println("Press 2 for Get Product By Id");
			System.out.println("Press 3 for Delete Product By Id");
			System.out.println("Press 4 for Update Product");
			System.out.println("Press 5 for Get All Product");
			System.out.println("Press 6 for Get All Products Between Two Indexes");
			System.out.println("Press 7 for Sort the Products");
			System.out.println("Press 8 for Get the Required Products with There Value");
			System.out.println("Press 9 for get the Products with  the price ");
			System.out.println("Press 10 for get the Products with Greater than and Equal to ur enter price ");
			System.out.println("Press 11 for get the Products with Less than to ur  price enter");
			System.out.println("Press 12 to get the Product between Two Price Range ");
			System.out.println("Press 13 to get the lowest Price");
			System.out.println("Press 14 to get Products Excepting Stationary Product");
			System.out.println("Press 15 to get Products by name And Price");
			System.out.println("Press 16 to get Products by name Or Price");
			System.out.println("Enter 17 to get Maximum Price Products");
			System.out.println("Enter 18 to get Minumum Price Products");
			System.out.println("Enter 19 to get the Total Number of Products");
			System.out.println("Enter 20 to get the Total Number of Products Quantity");
			System.out.println("Press 21 to get all  Products Name With their Price ");
			System.out.println("Press 22 to get Product By ProductId");
			System.out.println("Press 23 to get All Results");
			Integer choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				Product product = ProductUtility.prepareProdcuData();

				if (product != null) {

					String msg = productDao.addProduct(product);
					System.out.println(msg);
				} else {
					System.out.println("Product Data is Not Valid");
				}
				break;
			}

			case 2: {

				long productId = 0L;
				System.out.println("Enter the Product Id : ");
				productId = Validate.checkLongNumber(productId);
				Product product = productDao.getProducyById(productId);

				if (product != null) {
					System.out.println(product);
				} else {
					System.out.println("Product not Found with Id  " + productId);
				}

				break;
			}

			case 3: {

				System.out.println("Enter the Product Id : ");
				long productId = scanner.nextLong();

				String msg = productDao.deleteProductById(productId);
				System.out.println(msg);

				break;
			}

			case 4: {
				Product product = ProductUtility.prepareProdcuData();

				if (product != null) {

					String msg = productDao.updateProduct(product);
					System.out.println(msg);
				} else {
					System.out.println("Product Data is Invalid");
				}
				break;
			}

			case 5: {

				List<Product> list = productDao.getAllProducts();

				if (list.isEmpty() || list == null) {
					System.out.println("Product List is Empty");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}

			case 6: {

				int start = 0;
				int end = 0;
				List<Product> list = null;

				System.out.println("Enter the Starting Index in Number");
				start = scanner.nextInt();

				System.out.println("Enter the Ending Index in Number");
				end = scanner.nextInt();

				list = productDao.getProductBetweenAnyTwoIndex(start, end);

				if (list.isEmpty() || list == null) {
					System.out.println("Product List is Empty Between index " + start + " and " + end);
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}

				break;
			}

			case 7: {
				System.out.println("Enter the Type On which u want to sort : asc OR desc ");
				String orderType = scanner.next();

				System.out.println(
						"Enter the one Property as it is : productId/productName/supplierId/categoryId/productQTY/productPrice ");
				String propertyName = scanner.next();

				List<Product> list = productDao.sortProducts(orderType, propertyName);

				if (list.isEmpty() || list == null) {
					System.out.println("Products Not Found");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}

				break;

			}

			case 8: {

				System.out.println(
						"Enter the one Property as it is : productId/productName/supplierId/categoryId/productQTY/productPrice ");
				String propertyName = scanner.next();

				System.out.println("Enter the property Value on which u want to get the products");
				String value = scanner.next();

				List<Product> list = productDao.getProductsWithPropertyValue(propertyName, value);

				if (list.isEmpty() || list == null) {
					System.out.println("Products Not Found");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}

				break;

			}

			case 9: {

				System.out.println("Enter product price ");
				Double price = scanner.nextDouble();
				List<Product> list = productDao.getProductWithPrice(price);

				if (list.isEmpty() || list == null) {
					System.out.println("Products Not Found with price " + price);
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}

			case 10: {

				System.out.println("Enter product price ");
				Double price = scanner.nextDouble();
				List<Product> list = productDao.getProductWithPriceGreaterThan(price);

				if (list.isEmpty() || list == null) {
					System.out.println("Products Not Found with price greater than " + price);
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}

			case 11: {

				System.out.println("Enter product price ");
				Double price = scanner.nextDouble();
				List<Product> list = productDao.getProductWithPriceLessThan(price);

				if (list.isEmpty() || list == null) {
					System.out.println("Products Not Found with price greater than " + price);
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}
			
			case 12: {

				System.out.println("Enter lowest product price ");
				Double low = scanner.nextDouble();
				System.out.println("Enter lowest product price ");
				Double high = scanner.nextDouble();
				List<Product> list = productDao.getProductsBetweenPriceRange(low,high);

				if (list == null) {
					System.out.println("Products Not Found Between the Range "+low+" to "+high);
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}
			
			case 13: {

				System.out.println("Enter the price");
				Double productPrice = scanner.nextDouble();
				
				List<Product> list = productDao.getProdcutsWithLowerPrice(productPrice);

				if (list == null) {
					System.out.println("Products Not Found With the Range Less Than "+productPrice);
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}
			
			case 14: {
				
				

				System.out.println("Enter the Category Id 1 ");
				System.out.println("Press 1 for Stationary,Press 2 for Electronics, Press 3 for Electricals");
				Long categoryId1 = scanner.nextLong();
				System.out.println("Enter the Category Id 1 ");
				Long categoryId2 = scanner.nextLong();
				
				
				List<Product> list = productDao.getProductByCategoryId(categoryId1,categoryId2);

				if (list == null) {
					System.out.println("Products Not Found with the Category Id "+categoryId1+" & "+categoryId2);
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}
			
			case 15 :{
				System.out.println("Enter the product name");
				String name = scanner.next();
				System.out.println("Enter the product price");
				Double price = scanner.nextDouble();
				
				List<Product> list = productDao.getProductByNameAndPrice(name,price);
				
				if (list == null) {
					System.out.println("Products Not Found with the Category Id "+name+" & "+price);
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
				
			}
			
			case 16 :{
				System.out.println("Enter the product name");
				String name = scanner.next();
				System.out.println("Enter the product price");
				Double price = scanner.nextDouble();
				
				List<Product> list = productDao.getProductByNameOrPrice(name,price);
				
				if (list == null) {
					System.out.println("Products Not Found with the Category Id "+name+" & "+price);
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
				
			}
			
			case 17: {
				List<Product> list = productDao.getMaxPriceProducts();
				System.out.println(list);
				break;
			}
			
			case 18: {
				List<Product> list = productDao.getMinPriceProducts();
				System.out.println(list);
				break;
			}
			
			case 19: {
				List<Product> list = productDao.getTotalNoOfProducts();
				System.out.println(list);
				break;
			}
			
			case 20: {
				List<Product> list = productDao.getTotalPriceOfElectronicsProducts();
				System.out.println(list);
				break;
			}
			
			case 21: {
				List<ProductModel> list = productDao.queryExample();
				for (ProductModel productModel : list) {
					System.out.println(productModel);
				}
				
				break;
			}
			
			case 22: {
				System.out.println("Enter the Product Id : ");
				long productId = scanner.nextLong();
				Product product = productDao.nativeQueryEx(productId);
				
					System.out.println(product);
				
				
				break;
			}
			
			case 23 : {
				List<Product> list = productDao.getAllProducts2();
				for (Product product : list) {
					System.out.println(product);
				}
				
				break;
			}

			

			default:
				System.out.println("Enter the valid choice");
				break;
			}

			System.out.println("Do u want to continue y/n");
			c = scanner.next().charAt(0);

		} while (c == 'y' || c == 'Y');
	}
}
