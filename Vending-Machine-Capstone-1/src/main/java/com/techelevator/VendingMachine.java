package com.techelevator;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.techelevator.item.*;

public class VendingMachine {
	
	private Map<String, Item> vendingMachine;
	private Set<String> orderedSet;
	
	public Map<String, Item> makeVendingMachine() {
		FileReader file = new FileReader();
		File inputFile = file.setInputFile();
		List <String> outputList = new ArrayList<String>();
		outputList = file.createVendingMachineList(inputFile);
		vendingMachine = new HashMap<String, Item>();
		for (int i = 0; i < outputList.size(); i++) {
			String[] splitLine = outputList.get(i).split("\\|");
			if (splitLine[3].equals("Chip")) {
				vendingMachine.put(splitLine[0], new Chip(splitLine[0]));
			} else if (splitLine[3].equals("Candy")) {
				vendingMachine.put(splitLine[0], new Candy(splitLine[0]));
			} else if (splitLine[3].equals("Drink")) {
				vendingMachine.put(splitLine[0], new Drink(splitLine[0]));
			} else if (splitLine[3].equals("Gum")) {
				vendingMachine.put(splitLine[0], new Gum(splitLine[0]));
			}
		}
		return vendingMachine;
	}
	
	public Map<String, Item> getVendingMachine() {
		return vendingMachine;
	}
	
	public void displayVendingMachine() {
		
		orderedSet = new TreeSet<String>();
	
		for (Map.Entry<String, Item> entry : vendingMachine.entrySet()) {
			orderedSet.add(entry.getKey() + " " + entry.getValue().getName() + " $" + String.valueOf(entry.getValue().getPrice()) + " " + entry.getValue().getItemCount());
		}
		
		for (String item : orderedSet) {
			System.out.println(item);
		}
	}

}
