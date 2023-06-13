package com.jbk.utility;

import java.util.Scanner;

public class Validate {
	
	static boolean check;
	

	public static  Long checkLongNumber(Long number) {

		
		do {

			try {
				Scanner sc = new Scanner(System.in);
				number = sc.nextLong();

				if (number < 1) {
					System.out.println("You cannot enter negative Number or Zero");
					System.out.println("Enter the Number");
					number = sc.nextLong();
				}
				check = false;
			} catch (Exception e) {
				System.out.println("Enter Only Number");
				check = true;
			}

		} while (check);

		return number;
	}
	

	public static Integer checkIntegerNumber(Integer number) {
		

		do {

			try {
				Scanner sc = new Scanner(System.in);
				number = sc.nextInt();

				if (number < 1) {
					System.out.println("You cannot enter negative Number or Zero");
					System.out.println("Enter the Number");
					number = sc.nextInt();
				}
				check = false;
			} catch (Exception e) {
				System.out.println("Enter Only Number");
				check = true;
			}

		} while (check);

		return number;
	}
	
	public static Double checkDoubleNumber(Double price) {
		

		do {

			try {
				Scanner sc = new Scanner(System.in);
				price = sc.nextDouble();

				if (price < 1) {
					System.out.println("You cannot enter negative Number or Zero");
					System.out.println("Enter the Number");
					price = sc.nextDouble();
				}
				check = false;
			} catch (Exception e) {
				System.out.println("Enter Only Number");
				check = true;
			}

		} while (check);

		return price;
	}

}
