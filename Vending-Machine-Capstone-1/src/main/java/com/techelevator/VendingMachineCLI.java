package com.techelevator;

import com.techelevator.transaction.FeedMoney;
import com.techelevator.transaction.FinishTransaction;
import com.techelevator.transaction.Purchase;
import com.techelevator.view.Menu;
import com.techelevator.view.PurchaseMenu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_PURCHASE = "Purchase";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_PURCHASE, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private static final String FEED_ONE_DOLLAR = "Feed $1";
	private static final String FEED_TWO_DOLLARS = "Feed $2";
	private static final String FEED_FIVE_DOLLARS = "Feed $5";
	private static final String FEED_TEN_DOLLARS = "Feed $10";
	private static final String[] FEED_OPTIONS = { FEED_ONE_DOLLAR, FEED_TWO_DOLLARS, FEED_FIVE_DOLLARS,
			FEED_TEN_DOLLARS };

	private Menu menu;
	private PurchaseMenu purchaseMenu;
	private VendingMachine vendingMachine;
	private FeedMoney feedMoney;
	private Purchase purchase;
	private FinishTransaction finish;

	public VendingMachineCLI(Menu menu, PurchaseMenu purchaseMenu, FeedMoney feedMoney, Purchase purchase) {
		this.menu = menu;
		this.purchaseMenu = purchaseMenu;
		this.feedMoney = feedMoney;
		this.purchase = purchase;
	}

	public void run() {
		boolean runAgain = true;
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.makeVendingMachine();
		while (runAgain) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				vendingMachine.displayVendingMachine();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				boolean isDone = false;
				while (!isDone) {
					choice = (String) purchaseMenu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						choice = (String) feedMoney.getChoiceFromOptions(FEED_OPTIONS);
						if (choice.equals(FEED_ONE_DOLLAR)) {
							feedMoney.feedMoney(choice);
						} else if (choice.equals(FEED_TWO_DOLLARS)) {
							feedMoney.feedMoney(choice);
						} else if (choice.equals(FEED_FIVE_DOLLARS)) {
							feedMoney.feedMoney(choice);
						} else if (choice.equals(FEED_TEN_DOLLARS)) {
							feedMoney.feedMoney(choice);
						}
						runAgain = false;
					} else if (choice.equals(PURCHASE_MENU_OPTION_PURCHASE)) {
						vendingMachine.displayVendingMachine();
						purchase.selectItem(vendingMachine,feedMoney);
						runAgain = false;
					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						FinishTransaction finish = new FinishTransaction(System.in, System.out);
						finish.makeChange(purchase);
						isDone = true;
//							runAgain = false;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		PurchaseMenu purchaseMenu = new PurchaseMenu(System.in, System.out);
		FeedMoney feedMoney = new FeedMoney(System.in, System.out);
		Purchase purchase = new Purchase(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu, purchaseMenu, feedMoney, purchase);
		cli.run();

	}
}
