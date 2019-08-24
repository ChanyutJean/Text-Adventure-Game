package com.muids;

import java.util.Scanner;

public class Shop {

    public static void atShop(Hero hero, Scanner sc) {

        boolean getMaster = false;

        while (!getMaster) {

            Shop.printShop(true, true, hero.isInBag("Master"), hero); //already have sword and shield, but maybe not master sword

            int itemToBuy = Main.getInt(sc);

            boolean[] shopLoopArray = Shop.buyItem(true, true, false, itemToBuy, hero);

            getMaster = !shopLoopArray[0]; //true when user exits or buys master sword and method immediately returns

            System.out.println();

        }

    }

    public static void printShop(boolean swordSold, boolean shieldSold, boolean masterSold, Hero hero) {

        System.out.println("Welcome to our shop! Before you begin your adventure, consider buying items!");

        System.out.print("(1) Sword        ($  50)");
        if (swordSold) {System.out.print(" (SOLD)");}
        System.out.println();

        System.out.print("(2) Shield       ($  75)");
        if (shieldSold) {System.out.print(" (SOLD)");}
        System.out.println();

        System.out.print("(3) Master Sword ($ 500)");
        if (masterSold) {System.out.print(" (SOLD)");}
        System.out.println();

        System.out.println("(0) Leave the shop");
        System.out.print("You have $" + hero.getGold() + ". What would you like to buy? (1, 2, 3, 0): ");

    }

    public static boolean[] buyItem(boolean swordSold, boolean shieldSold, boolean masterSold, int itemToBuy, Hero hero) {

        boolean shopLoop = true;

        switch (itemToBuy) {

            case 1:
                if (hero.getGold() >= 50) {

                    if (!swordSold) {
                        hero.addItemToBag("Sword");
                        hero.changeGold(-50);
                        swordSold = true;
                    } else {
                        System.out.println("Already sold!");
                    }

                } else {
                    System.out.println("Not enough money!");
                }
                break;

            case 2:
                if (hero.getGold() >= 75) {

                    if (!shieldSold) {
                        hero.addItemToBag("Shield");
                        hero.changeGold(-75);
                        shieldSold = true;
                    } else {
                        System.out.println("Already sold!");
                    }

                } else {
                    System.out.println("Not enough money!");
                }
                break;

            case 3:
                if (hero.getGold() >= 500) {

                    if (!masterSold) {

                        System.out.println("Thank you for your purchase!");
                        hero.addItemToBag("Master");
                        hero.changeGold(-500);
                        System.out.println();
                        return new boolean[] {false, true, true, true}; //exits secondShopLoop instantly

                    } else {
                        System.out.println("Already sold!");
                    }

                } else {
                    System.out.println("Not enough money!");
                }
                break;

            case 0:
                if (hero.isInBag("Sword") && hero.isInBag("Shield")) {
                    if (!(hero.getQuest() == 3)) {
                        System.out.println("Your adventure begins here!"); //only at first time
                    }
                    shopLoop = false;
                } else {
                    System.out.println("Buy items before you go!");
                }
                break;

            default:
                System.out.println("Enter a valid number!");
        }

        return new boolean[] {shopLoop, swordSold, shieldSold, masterSold};
    }







}
