package com.techelevator.transaction;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class Transaction extends Menu {
	
	private Double balance;
	private PrintWriter out;
	private Scanner in;
	
	public Transaction(InputStream input, OutputStream output) {
		super(input, output);
		this.balance = 0.0;
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
		
		
	}
	
	public Double getBalance() {
		return balance;
	}

}
