package com.techelevator.item;
 
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.techelevator.FileReader;

public class Item {

	private String name;
	private Double price;
	private String slot;
	private String noise;
	private String itemCount;

	public Item(String slot) {
		this.itemCount = "5";
		this.slot = slot;
	}
//	private List<String> outputList;
//	private Map<String, Double> vendingItems;

	public String getName() {
		FileReader file = new FileReader();
		File inputFile = file.setInputFile();
		List<String> outputList = new ArrayList<String>();
		outputList = file.createVendingMachineList(inputFile);
		for (int i = 0; i < outputList.size(); i++) {
			String[] splitLine = outputList.get(i).split("\\|");
			if (splitLine[0].equals(slot)) {
				name = splitLine[1];
			}
		}
		return name;
	}

	public Double getPrice() {
		FileReader file = new FileReader();
		File inputFile = file.setInputFile();
		List<String> outputList = new ArrayList<String>();
		outputList = file.createVendingMachineList(inputFile);
		for (int i = 0; i < outputList.size(); i++) {
			String[] splitLine = outputList.get(i).split("\\|");
				if (splitLine[0].equals(slot)) {
					price = Double.valueOf(splitLine[2]);
			}
		}
		return price;
	}
	
	public String makeNoise() {
		return noise;
	}
	
	public String getItemCount() {
		return itemCount;
	}
	
	public void purchaseItem() {
		Integer currentCount = Integer.parseInt(itemCount) - 1;
		if (currentCount > 0) {
			itemCount = String.valueOf(currentCount);
		} else {
			itemCount = "SOLD OUT";
		}
	}

//	public List<String> getVendingMachineList(FileReader file) {
//		outputList = new ArrayList<String>();
//		File newFile = file.setInputFile();
//		outputList = file.createVendingMachineList(newFile);
//		return outputList;
//	}
//	
//	//make map containing (k)name & (v)price
//	public Map<String, Double> makeNameAndPriceMap() {
//		vendingItems = new HashMap<String,Double>();
//		for(int i=0; i < outputList.size(); i++) {
//			String[] splitLine = outputList.get(i).split("\\|");
//			for(int j = 0; j < splitLine.length; j++) {
//				if(j == 1) {
//					vendingItems.put(splitLine[j], Double.valueOf(splitLine[j+1]));
//				}
//			}
//		}
//		return vendingItems;
//	}

}
