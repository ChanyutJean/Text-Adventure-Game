package com.muids;

import java.util.Random;
import java.util.Scanner;

public class Temple {

    public static void atTemple(Hero hero, Scanner sc, Random r, Combat c) {

        printTemple(hero);

        boolean invalidInput = true;

        while(invalidInput) {

            int option = Main.getInt(sc);

            invalidInput = setTemple(option, hero, sc, r, c);

        }

    }

    public static void printTemple(Hero hero) {

        System.out.println("You are now in the temple!");
        System.out.println("Your HP: " + hero.getHp() + "/" + hero.getMaxHp() + ". Your Gold: " + hero.getGold() + ".");
        System.out.println("(1) Heal for 10 gold");
        if (hero.getQuest() == 1 && !hero.isInBag("Cloth")) {System.out.println("(2) Inspect graveyard");}
        System.out.println("(0) Go back to the town square");

        System.out.print("What to do? ");

    }

    public static boolean setTemple(int option, Hero hero, Scanner sc, Random r, Combat c) {

        boolean invalidInput = false;

        switch (option) {

            case 1:
                if (hero.getGold() >= 10) {
                    hero.changeGold(-10);
                    System.out.println("HP replenished.");
                    System.out.println();
                    hero.setHp(hero.getMaxHp());
                } else {
                    System.out.println("Not enough money!");
                    System.out.println();
                }
                break;
            case 2:
                if (hero.getQuest() == 1 && !hero.isInBag("Cloth")) {
                    if (!hero.isInBag("Repel")) {

                        boolean defeated = priestFight(hero, sc, r, c);

                        if(defeated) {
                            System.out.println("Hmm! I guess you can go in the grave with this power of yours. Here, take this ghost repellent.");
                            System.out.println("Repellent was added to the inventory!");
                            hero.addItemToBag("Repel");
                            System.out.println();
                        } else {
                            hero.setLostTo(1);
                            break;
                        }

                    }
                    hero.setLocation("Grave");
                } else {
                    System.out.print("Enter a valid number! ");
                    invalidInput = true;
                }
                break;
            case 0:
                hero.setLocation("Town");
                System.out.println();
                break;
            default:
                System.out.print("Enter a valid number! ");
                invalidInput = true;
        }

        return invalidInput;

    }

    public static boolean priestFight(Hero hero, Scanner sc, Random r, Combat c) {

        if (!hero.getLostTo(1)) {
            System.out.println();
            System.out.println("Wait! You shouldn't go there, it's dangerous!");
            System.out.println("...Ah. So you are the one catching the robber.");
            System.out.println("Still, it's very dangerous, and I won't let you past unless you prove yourself that you are capable!");
        } else {
            System.out.println();
            System.out.println("Like I said, it's very dangerous, and I won't let you past unless you prove yourself that you are capable!");
        }

        Monster Priest = new Monster(300, "Priest", new boolean[]{true, true, false});

        c.fight(hero, sc, Priest, r);

        return (Priest.getHp() <= 0);

    }

}
