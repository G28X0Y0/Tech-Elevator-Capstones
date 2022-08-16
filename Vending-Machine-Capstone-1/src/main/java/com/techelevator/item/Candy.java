package com.techelevator.item;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.FileReader;

public class Candy extends Item {
	
	private String noise;
	private String slot;
	

	public Candy(String slot) {
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
			if (splitLine[0].equals(slot) && splitLine[3].equals("Candy")) {
					noise = "Munch Munch, Yum!";
			}
		}
		return noise;
	}
	
	public String getSlot() {
		return slot;
	}
}
