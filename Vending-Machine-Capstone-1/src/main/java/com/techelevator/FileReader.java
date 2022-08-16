package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
	
	private File inputFile;
	private String readFile;
	private List<String> outputList = new ArrayList<String>();
	

	public File setInputFile() {
		inputFile = new File("vendingmachine.csv");
		return inputFile;

	}
	
	public List<String> createVendingMachineList(File inputFile) {
		
		try(Scanner readFile = new Scanner(inputFile)) {
			while(readFile.hasNextLine()) {
				outputList.add(readFile.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return outputList;
		
	}	
		
}

