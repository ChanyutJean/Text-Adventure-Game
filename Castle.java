package com.muids;

import java.util.Random;
import java.util.Scanner;

public class Castle {

    public static void atCastle(Hero hero, Scanner sc, Random r, Combat c) {

        printCastle(hero);

        boolean invalidInput = true;

        while(invalidInput) {

            int option = Main.getInt(sc);

            invalidInput = setCastle(option, hero, sc, r, c);

        }

    }

    public static void printCastle(Hero hero) {

        System.out.println("You are now in the castle!");
        System.out.println("Your HP: " + hero.getHp() + "/" + hero.getMaxHp() + ". Your Gold: " + hero.getGold() + ".");
        System.out.println("(1) Work for the king");
        if (hero.getQuest() == 0) {System.out.println("(2) Talk to the guards");}
        if (hero.getQuest() == 1 && hero.isInBag("Cloth")) {System.out.println("(2) Tell the guards about the cloth");}
        if (hero.getQuest() == 2 && hero.isInBag("Letter")) {System.out.println("(2) Give letter to the guards");}
        System.out.println("(0) Go back to the town square");

        System.out.print("What to do? ");

    }

    public static boolean setCastle(int option, Hero hero, Scanner sc, Random r, Combat c) {

        System.out.println();

        boolean invalidInput = false;

        switch (option) {

            case 1:
                castleWork(hero);
                break;
            case 2:
                if (hero.getQuest() == 0) {
                    guardTalk(hero, sc, r, c);
                } else if (hero.getQuest() == 1 && hero.isInBag("Cloth")) {
                    guardCloth(hero);
                } else if (hero.getQuest() == 2 && hero.isInBag("Letter")) {
                    guardLetter(hero);
                } else {
                    System.out.print("Enter a valid number! ");
                    invalidInput = true;
                }
                break;
            case 0:
                hero.setLocation("Town");
                break;
            default:
                System.out.print("Enter a valid number! ");
                invalidInput = true;

        }

        return invalidInput;

    }

    public static void castleWork(Hero hero) {

        hero.changeGold(1);
        System.out.println();

    }

    public static void guardTalk(Hero hero, Scanner sc, Random r, Combat c) {

        if (!hero.getLostTo(0)) {

            System.out.println("Hello there, " + hero.getName() + ". There has been a lot of grave robbing incidents lately.");
            System.out.println("I don't really have time for this, so can you find and catch the grave robbers?");

            boolean accept;

            for (int i = 0; i < 3; i++) {

                accept = Main.getBoolean(sc, "Accept?");

                if (accept) {

                    System.out.println("Nice! Now to make sure that you are capable of self-defense...");
                    System.out.println();
                    guardFight(hero, sc, r, c);
                    break;

                } else {

                    switch (i) {
                        case 0:
                            System.out.println("You will get big rewards from the king! We need somebody!");
                            break;
                        case 1:
                            System.out.println("Please!!!");
                            break;
                        case 2:
                            System.out.println("This is an order.");

                            while (!accept) {
                                accept = Main.getBoolean(sc, "Accept.");
                            }

                            System.out.println("Nice! Now to make sure that you are capable of self-defense...");
                            System.out.println();
                            guardFight(hero, sc, r, c);
                            break;
                    }

                }

            }

        } else {

            System.out.println("You've come again. Let's try this again!");
            guardFight(hero, sc, r, c);

        }
    }

    public static void guardFight(Hero hero, Scanner sc, Random r, Combat c){

        Monster Guard = new Monster(200, "Guard", new boolean[] {true, false});

        c.fight(hero, sc, Guard, r);

        boolean defeated = (Guard.getHp() <= 0);

        if (defeated) {
            System.out.println("Thank you! The graveyard should be around the temple.");
            System.out.println("Accepted Quest: Catch the Robbers!");
            System.out.println();
            hero.setQuest(1);
        } else {
            hero.setLostTo(0);
        }

    }

    public static void guardCloth(Hero hero) {

        System.out.println("The guards tell you that this cloth belongs to the 'drunk' tycoon who lives in the manor!");
        System.out.println("Accepted Quest: Apprehend the Tycoon!");
        hero.setQuest(2);
        System.out.println(); //event ends

    }

    public static void guardLetter(Hero hero) {

        System.out.println("Thank you! Now we don't have to worry about grave robberies again! Here's your reward from the king!");
        hero.changeGold(500);
        if (!hero.getRestart()) {
            System.out.println("With this much power defeating the tycoon, you might want to try fighting in the arena!");
            System.out.println("Accepted Quest: Conquer the Arena!");
            hero.setQuest(3);
        } else {
            System.out.println();
            System.out.println("Congratulations, you have finished the game!");
            System.out.println("Next time, try beating the game without losing to or running from the opponent in a fight for a surprise!");
            hero.setGameIsRunning();
        }
        System.out.println();

    }

}
