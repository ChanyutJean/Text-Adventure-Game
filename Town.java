package com.muids;

import java.util.Scanner;

public class Town {

    public static void atTown(Hero hero, Scanner sc) {

        printTown(hero);

        boolean invalidInput = true;

        while (invalidInput) {

            int option = Main.getInt(sc);

            invalidInput = setTown(option, hero, sc);

        }

        System.out.println();

    }

    public static void printTown(Hero hero) {

        System.out.println("You are now in the town square!");
        System.out.println("Your HP: " + hero.getHp() + "/" + hero.getMaxHp() + ". Your Gold: " + hero.getGold() + ".");
        System.out.println("(1) Visit the temple");
        System.out.println("(2) Go to the castle");
        System.out.println("(3) Spend time in the tavern");

        if (hero.getQuest() == 2 && !hero.isInBag("Letter")) {System.out.println("(4) Look around the manor");}
        if (hero.getQuest() == 3) {System.out.println("(4) Fight in the arena");}
        if (hero.getQuest() == 3 && !hero.isInBag("Master")) {System.out.println("(5) Buy something in the shop");}

        if (!hero.getFountain()) {
            System.out.println("(0) Wish in the fountain");
        } else {
            System.out.println("(0) Check status");
        }

        System.out.print("What to do? ");

    }

    public static boolean setTown(int option, Hero hero, Scanner sc) {

        boolean invalidInput = false;

        switch (option) {

            case 1:
                hero.setLocation("Temple");
                break;
            case 2:
                hero.setLocation("Castle");
                break;
            case 3:
                hero.setLocation("Tavern");
                break;
            case 4:
                if (hero.getQuest() == 2 && !hero.isInBag("Letter")) {
                    hero.setLocation("Manor");
                } else if (hero.getQuest() == 3) {
                    hero.setLocation("Arena");
                } else {
                    System.out.print("Enter a valid number! ");
                    invalidInput = true;
                }
                break;
            case 5:
                if (hero.getQuest() == 3 && !(hero.isInBag("Master"))) {
                    hero.setLocation("Shop");
                } else {
                    System.out.print("Enter a valid number! ");
                    invalidInput = true;
                }
                break;
            case 0:
                System.out.println(); //empty enter line
                if (!hero.getFountain()) {
                    fountain(hero, sc);
                } else {
                    hero.showNewStat();
                }
                break;
            default:
                System.out.print("Enter a valid number! ");
                invalidInput = true;

        }

        return invalidInput;

    }

    public static void fountain(Hero hero, Scanner sc) {

        String wish = "";

        while (!(wish.equals("HP") || wish.equals("Gold") || wish.equals("Str") || wish.equals("Dex") || wish.equals("Con"))) {
            System.out.print("What do you wish for? (HP, Gold, Str, Dex, Con): ");
            wish = sc.next();
        }

        switch (wish) {

            case "HP":
                System.out.println("Your max HP changed by 50.");
                hero.setMaxHp(hero.getMaxHp() + 50);
                break;
            case "Gold":
                hero.changeGold(500);
                break;
            case "Str":
                System.out.println("Your Strength changed by 3.");
                hero.setStr(hero.getStr() + 3);
                break;
            case "Dex":
                System.out.println("Your Dexterity changed by 3.");
                hero.setDex(hero.getDex() + 3);
                break;
            case "Con":
                System.out.println("Your Constitution changed by 3.");
                hero.setCon(hero.getCon() + 3);
                break;

        }

        hero.setFountain();

    }

}
