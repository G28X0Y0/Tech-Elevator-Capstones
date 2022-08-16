package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.item.Item;


public class ItemTest {
	
	private Item target;
	private FileReader file;
	
	@Before
	public void setup() {
		file = new FileReader();
	//	target = new Item();
	}
	
//	@Test
//	public void returns_list_from_FileReader() {
//		Assert.assertTrue("A1|Potato Crisps|3.05|Chip".equals(target.getVendingMachineList(file).get(0)));
//	}
//	
//	@Test
//	public void list_size_is_correct() {
//		Assert.assertEquals(16,target.getVendingMachineList(file).size());
//	}
//	
//	@Test
//	public void returns_Map_of_name_and_price() {
//		target.getVendingMachineList(file);
//		Assert.assertEquals(Double.valueOf("0.75"), target.makeNameAndPriceMap().get("Triplemint"));
//	}
//	
	

}
