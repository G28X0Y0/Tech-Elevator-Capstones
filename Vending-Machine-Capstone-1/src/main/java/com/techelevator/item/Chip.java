package com.techelevator.item;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techelevator.FileReader;

public class Chip extends Item {
	
	private String noise;
	private String slot;

	public Chip(String slot) {
		super(slot);
		this.slot = slot;
	}
	@Override
	public String makeNoise() {
		FileReader file = new FileReader();
		File inputFile = file.setInputFile();
		List <String> outputList = new ArrayList<String>();
		outputList = file.createVendingMachineList(inputFile);
		for(int i=0; i < outputList.size(); i++) {
			String[] splitLine = outputList.get(i).split("\\|");
			if (splitLine[0].equals(slot) && splitLine[3].equals("Chip")) {
					noise = "Crunch Crunch, Yum!";
			}
		}
		return noise;
	}
	
	public String getSlot() {
		return slot;
	}

//	private Map<String, Map<String, Double>> chipsMap;
//	
//	public Map<String, Map<String, Double>> makeMapOfSlotNameAndPrice(FileReader file) {
//		//make a map containing the (k)slot number (as string)
//		chipsMap = new HashMap<String, Map<String, Double>>();
//		for (int i = 0; i < getVendingMachineList(file).size(); i++) {
//			String[] splitLine = getVendingMachineList(file).get(i).split("\\|");
//			for (int j = 0; j < splitLine.length; j++) {
//				if (j == 0) {
//					chipsMap.put(splitLine[j], makeNameAndPriceMap());
//				}
//				
//			}
//		}
//		return chipsMap;
//	}
	
}
