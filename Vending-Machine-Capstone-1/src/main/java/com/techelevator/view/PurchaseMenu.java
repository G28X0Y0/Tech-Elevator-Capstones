package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class PurchaseMenu extends Menu{
	
	private PrintWriter out;
	private Scanner in;

	public PurchaseMenu(InputStream input, OutputStream output) {
		super(input, output);
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
		// TODO Auto-generated constructor stub
	}

}
