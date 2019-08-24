package com.muids;

import java.util.Random;
import java.util.Scanner;

public class Tavern {

    public static void atTavern(Hero hero, Scanner sc, Random r) {

        printTavern(hero);

        boolean invalidInput = true;

        while (invalidInput) {

            int option = Main.getInt(sc);

            invalidInput = setTavern(option, hero, r);

        }

    }

    public static void printTavern(Hero hero) {

        System.out.println("You are now in the tavern");
        System.out.println("Your HP: " + hero.getHp() + "/" + hero.getMaxHp() + ". Your Gold: " + hero.getGold() + ".");
        System.out.println("(1) Buy a drink and talk");
        System.out.println("(0) Go back to the town square");
        System.out.print("What to do? ");

    }

    public static boolean setTavern(int option, Hero hero, Random r) {

        boolean invalidInput = false;

        switch(option) {

            case 1:
                if (hero.getQuest() == 2) {
                    randomTalk(true, hero);
                } else {
                    randomTalk(false, hero);
                }
                break;
            case 0:
                hero.setLocation("Town");
                break;
            default:
                invalidInput = true;
        }

        return invalidInput;

    }

    public static void randomTalk(boolean questRelated, Hero hero) {

        if (!(hero.getGold() == 0)) {
            hero.changeGold(-1);
            if (questRelated) {
                System.out.println("Know this rich family manor near here? The guard's strict, but just say Hello to them and they'll let you in.");
                System.out.println();
            } else {
                switch (hero.getTalk()) {
                    case 0:
                        System.out.println("Hi. I'm running a student's immensely complex game program. I'd give this guy a lot of bonus points, He did good.");
                        hero.setTalk();
                        break;
                    case 1:
                        System.out.println("Struggling with fighting the guard? First, just defend, then attack, then repeat, then you're done!");
                        hero.setTalk();
                        break;
                    case 2:
                        System.out.println("Do you know that your Str helps you attack and your Dex reduces damage taken?");
                        hero.setTalk();
                        break;
                    case 3:
                        System.out.println("You know, your " + hero.getJob() + " job doesn't do anything. That's kinda sad.");
                        hero.setTalk();
                        break;
                    case 4:
                        System.out.println("Have you ever been in a fight? Normally, your opponent will act on a set algorithm. You can always find that out and counter it!");
                        hero.setTalk();
                        break;
                    case 5:
                        System.out.println("Did you know, Con stat helps increase your money obtained! From where? Hm... somewhere here.");
                        hero.setTalk();
                        break;
                    case 6:
                        System.out.println();
                        hero.setTalk();
                        break;
                    case 7:
                        System.out.println();
                        hero.setTalk();
                        break;
                    case 8:
                        System.out.println();
                        hero.setTalk();
                        break;
                    case 9:
                        System.out.println("You really liked talking to me huh? Well, here's you prize.");
                        hero.changeGold(5 + hero.getCon());
                        hero.setTalk();
                        break;
                    case 10:
                        System.out.println("I'm really out of things to talk. Don't talk to me again, else I'll just repeat everything.");
                        hero.setTalk();
                        break;
                    default:
                        System.out.println("Dude. You're not supposed to see this text in game. What on earth did you do?");
                        hero.setTalk();
                        break;

                }

                System.out.println(); //ends talk
            }
        } else {
            System.out.println("Not enough money!");
        }

    }

}
