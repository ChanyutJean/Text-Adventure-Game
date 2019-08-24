package com.muids;

import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Hero hero = new Hero();

        Scanner sc = new Scanner(System.in);

        Random r = new Random();

        Combat c = new Combat();

        introduction(hero, sc, r);

        shopping(hero, sc);

        firstFight(hero, sc, r, c);

        while (hero.getGameIsRunning()) {runGame(hero, sc, r, c);}

    }

    //Copy and Paste all of this in the beginning to skip to arena

    // Jeanius123 Mage No 1 2 0 1 1 1 1 1 1 1 1 1 1 (to skip intro and go to temple to heal)
    // 0 0 HP 2 2 Yes (to return to town, skip fountain and guard talk)
    // 2 1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 2 1 (to defeat guard)
    // 0 1 1 2 (to go fight priest)
    // 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 (to defeat priest)
    // No No Yes No Yes No Yes Yes Yes Yes (to skip grave)
    // 0 2 2 0 4 Hello (to go to manor)
    // 2 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1
    // 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 2 2 1 (to defeat tycoon)
    // 0 2 2 0 5 3 4 (buy sword and enter arena)

    /* 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9
       9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 9 (to lose 350HP and die)*/

    /* 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
         1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 (to get money in tavern)*/

    //guard 200HP attack-defend
    //priest 300HP attack-attack-defend
    //tycoon 500HP defend-defend-attack


    public static void runGame(Hero hero, Scanner sc, Random r, Combat c) {

        switch (hero.getLocation()) {

            case "Town":
                Town.atTown(hero, sc);
                break;
            case "Temple":
                Temple.atTemple(hero, sc, r, c);
                break;
            case "Grave":
                Grave.atGrave(hero, sc);
                hero.setLocation("Temple");
                break;
            case "Castle":
                Castle.atCastle(hero, sc, r, c);
                break;
            case "Tavern":
                Tavern.atTavern(hero, sc, r);
                break;
            case "Manor":
                Manor.atManor(hero, sc, r, c);
                hero.setLocation("Town");
                break;
            case "Arena":
                Arena.atArena(hero, sc, r, c);
                hero.setLocation("Town");
                break;
            case "Shop":
                Shop.atShop(hero, sc);
                hero.setLocation("Town");
                break;
        }

    }



    public static void introduction(Hero hero, Scanner sc, Random r) {

        hero.setHeroStat(sc);

        boolean heroIsCreated;

        do {

            hero.assignStat(r);

            hero.showStat();

            heroIsCreated = !getBoolean(sc, "Do you want to reroll?");

        } while (!heroIsCreated);

        System.out.println();
    }

    public static void shopping(Hero hero, Scanner sc) {

        hero.setGold(Hero.INITIAL_GOLD);

        boolean[] shopStatus = new boolean[] {true, false, false, false}; //shopLoop, swordSold, shieldSold, masterSold

        while (shopStatus[0]) {

            Shop.printShop(shopStatus[1], shopStatus[2], shopStatus[3], hero);

            int itemToBuy = getInt(sc);

            boolean[] soldStatus = Shop.buyItem(shopStatus[1], shopStatus[2], shopStatus[3], itemToBuy, hero);

            System.arraycopy(soldStatus, 0, shopStatus, 0, 4);

            System.out.println();

        }

    }

    public static void firstFight(Hero hero, Scanner sc, Random r, Combat c) {

        System.out.println("After a step out of the shop...");

        Monster Madman = new Monster(50, "Madman", new boolean[] {true});

        boolean madManDefeated = false;

        while (!madManDefeated) {

            c.fight(hero, sc, Madman, r);

            madManDefeated = (Madman.getHp() <= 0);

            if(madManDefeated) {
                System.out.println("Nice first fight! Let's move on to the town square.");
                System.out.println();
            } else {
                System.out.println("You lost to your opponent! Let's try this one more time.");
                hero.setHp(hero.getMaxHp());
            }

        }

        hero.setLocation("Town");

    }



    public static int getInt(Scanner sc) {

        boolean inputIsInt = false;
        int option = 0;

        while (!inputIsInt) {

            try {
                option = sc.nextInt();
                inputIsInt = true;
            } catch (Exception e) {
                System.out.print("Enter an integer: ");
                sc.next();
            }

        }

        return option;
    }

    public static boolean getBoolean(Scanner sc, String prompt) {

        String accept = "";

        while (!(accept.equals("Yes") || accept.equals("No"))) {

            System.out.print(prompt + " (Yes or No): ");

            accept = sc.next();
        }

        return accept.equals("Yes");

    }

}
