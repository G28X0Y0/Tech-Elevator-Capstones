package com.techelevator.transaction;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FinishTransaction extends Transaction {
	
	private Double balance;
	private Double valueOfQuarter = 0.25;
	private Double valueOfDime = 0.10;
	private Double valueOfNickel = 0.05;
	private int numberOfQuarters;
	private int numberOfDimes;
	private int numberOfNickels;
	private List<String> consumeNoises;

	public FinishTransaction(InputStream input, OutputStream output) {
		super(input, output);
		this.balance = 0.0;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void makeChange(Purchase purchase) {
		balance = purchase.getBalance();
		Double beginningBalance = purchase.getBalance();
		consumeNoises = purchase.getConsumeNoises();
		try (FileWriter fw = new FileWriter("log.txt", true);
				BufferedWriter bw = new BufferedWriter(fw); 
				PrintWriter out = new PrintWriter(bw)) {
			
			Date dt = new Date();
			SimpleDateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a ");
		while (balance >= valueOfQuarter) {
			numberOfQuarters++;
			balance -= valueOfQuarter;
		}
		while (balance >= valueOfDime) {
			numberOfDimes++;
			balance -= valueOfDime;
		}
		while (balance >= valueOfNickel) {
			numberOfNickels++;
			balance -= valueOfNickel;
		}
		out.printf("%-22s %-23s $%4.2f          $%4.2f\n", f.format(dt), "GIVE CHANGE: ", beginningBalance, balance);
		System.out.println("Your change is " + numberOfQuarters + " Quarter(s) " + numberOfDimes + " Dime(s) " + numberOfNickels + " Nickel(s).");
		for (String noise : consumeNoises) {
			System.out.println(noise);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

}
