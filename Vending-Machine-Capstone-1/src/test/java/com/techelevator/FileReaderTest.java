package com.techelevator;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.*;


public class FileReaderTest {
	
	private static final String VENDING_MACHINE_FILE = "vendingmachine.csv";
	private  FileReader target;
	
	@Before
	public void setup() throws FileNotFoundException {
	 target = new FileReader();
		
	}
	
	@Test
	public void input_file_exist() {
		Assert.assertTrue(target.setInputFile().exists());
	}
	
	@Test
	public void input_file_is_a_file() {
		Assert.assertTrue(target.setInputFile().isFile());
	}
	
	@Test
	public void outputList_has_content() {
		File file = target.setInputFile();
		List<String> ourList = target.createVendingMachineList(file);
		Assert.assertTrue("A1|Potato Crisps|3.05|Chip".equals(ourList.get(0)));
	}

}
