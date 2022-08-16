package com.techelevator;
import java.io.File;

import org.junit.*;

import com.techelevator.item.Chip;
import com.techelevator.item.Item;

public class VendingMachineTest {

	private VendingMachine target;
	private FileReader file;
	private Item item;

	@Before
	public void setup() {
		target = new VendingMachine();
		item = new Item("A1");
	}
	
	@Test
	public void slot_returns_the_correct_name_from_map() {
		Item ourName = target.makeVendingMachine().get("A1");
		Assert.assertTrue("Potato Crisps".equals(ourName.getName()));
	}
	
	@Test
	public void slot_returns_the_correct_price_from_map() {
		Item ourPrice = target.makeVendingMachine().get("A1");
		Assert.assertEquals(Double.valueOf(3.05), ourPrice.getPrice());
	}
	

}
