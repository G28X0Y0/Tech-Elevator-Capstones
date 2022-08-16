package com.techelevator.transaction;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FeedMoney extends Transaction {

	private Double balance;
	private Scanner in;
	private String choice;
	private Double oneDollar = 1.0;
	private Double twoDollars = 2.0;
	private Double fiveDollars = 5.0;
	private Double tenDollars = 10.0;
	
	public FeedMoney(InputStream input, OutputStream output) {
		super(input, output);
		this.balance = 0.0;
		this.in = new Scanner(input);
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void feedMoney(String choice) {
		try (FileWriter fw = new FileWriter("log.txt", true);
				BufferedWriter bw = new BufferedWriter(fw); 
				PrintWriter out = new PrintWriter(bw)) {
			
			Date dt = new Date();
			SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a ");
			
		
		if (choice.equals("Feed $1")) {
			balance += oneDollar;
			out.printf("%-22s %-23s $%4.2f          $%4.2f\n", f.format(dt), "FEED MONEY: ", oneDollar, balance);
			System.out.println("Your Balance is $" + balance);
		} else if (choice.equals("Feed $2")) {
			balance += twoDollars;
			out.printf("%-22s %-23s $%4.2f          $%4.2f\n", f.format(dt), "FEED MONEY: ", twoDollars, balance);
			System.out.println("Your Balance is $" + balance);
		} else if (choice.equals("Feed $5")) {
			balance += fiveDollars;
			out.printf("%-22s %-23s $%4.2f          $%4.2f\n", f.format(dt), "FEED MONEY: ", fiveDollars, balance);
			System.out.println("Your Balance is $" + balance);
		} else if (choice.equals("Feed $10")) {
			balance += tenDollars;
			out.printf("%-22s %-23s $%4.2f          $%4.2f\n", f.format(dt), "FEED MONEY: ", tenDollars, balance);
			System.out.println("Your Balance is $" + balance);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Double updateBalance(Double purchase) {
		balance -= purchase;
		return balance;
	}
	
	

}
