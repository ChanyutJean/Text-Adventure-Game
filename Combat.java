package com.muids;

import java.util.Scanner;
import java.util.Random;

public class Combat {

    private boolean playerRuns = false;
    private boolean playerLoseTurn = false;
    private boolean monsterLoseTurn = false;
    private byte consecutiveShield = 0;

    public void fight(Hero hero, Scanner sc, Monster mon, Random r) {

        playerRuns = false;
        playerLoseTurn = false;
        monsterLoseTurn = false;
        consecutiveShield = 0;

        printInitCombat(mon);

        while (hero.getHp() > 0 && mon.getHp() > 0 && !playerRuns) {

            for (byte i = 0; i < mon.getAlgorithm().length && hero.getHp() > 0 && mon.getHp() > 0 && !playerRuns; i++) {

                if (!playerLoseTurn) {

                    printCombat(hero, mon);

                    int option = Main.getInt(sc);

                    playerAct(option, r, i, hero, mon);

                } else {

                    playerIdle(i ,mon, hero, r);

                    playerLoseTurn = false;
                }

                System.out.println();
            }
        }

        if (mon.getHp() <=0) {

            mon.printAlgorithm();

        } else {

            mon.resetHp();

            if (!playerRuns && !mon.getName().equals("Madman")) {
                playerLost(hero); //don't go to temple if opponent is madman
            }
            hero.setRestart(); //no more arena fight
        }

    }

    public static void printInitCombat(Monster mon) {

        System.out.println("You encountered a " + mon.getName() + "!");
        System.out.println("(1) Attack with Sword");
        System.out.println("(2) Block with Shield");
        System.out.println("(0) Run away");
        System.out.println();

    }

    public static void printCombat(Hero hero, Monster mon) {

        System.out.println("You have " + hero.getHp() + "HP and " + mon.getName() + " has " + mon.getHp() + "HP.");
        System.out.print("What do you do? (1, 2, 0): ");

    }

    public void playerAct(int option, Random r, byte i, Hero hero, Monster mon) {

        switch (option) {

            case 1:

                consecutiveShield = 0;

                playerLoseTurn = playerAttack(monsterLoseTurn, i, hero, mon, r);

                monsterLoseTurn = false;

                break;

            case 2:

                if (mon.getName() == "Fairy") {

                    System.out.println("Fairy heals itself while you defend!");
                    mon.changeHp(3);

                }

                consecutiveShield++;

                boolean playerDefends = determineDefend(consecutiveShield, r, hero);

                if (playerDefends) {
                
                    playerLoseTurn = false;
                    monsterLoseTurn = playerDefend(monsterLoseTurn, i, mon);

                }

                break;

            case 0:

                playerRuns = playerRun(r, hero, mon, i);

                break;

            default:

                System.out.println("You are at a loss of what to do!");
                hero.changeHp(-5);
        }

    } //items



    public static boolean playerAttack(boolean monsterLoseTurn, byte i, Hero hero, Monster mon, Random r) {

        boolean playerLoseTurn = false;

        if (!monsterLoseTurn) {

            boolean monsterStance = mon.getAlgorithm()[i];

            if (mon.getName() == "Demon" && mon.getHp() < 200) {

                monsterStance = !monsterStance;

            }

            if (monsterStance) {
                System.out.println("You attack! Your opponent attacks!");
                hero.changeHp(-r.nextInt(11) + hero.getDex() - 20); //Decrease 5-20 HP
                mon.changeHp(-r.nextInt(11) - hero.getStr() + 5); //Decrease 5-20 HP
            } else {
                System.out.println("You attack! Your opponent shields! You flinched!");
                playerLoseTurn = true;
            }

        } else {
            System.out.println("You attack while your opponent is flinched.");
            mon.changeHp(-r.nextInt(11) - hero.getStr() - 5); //Decrease 15-30 HP
        }

        return playerLoseTurn;
    }

    public static boolean playerDefend(boolean monsterLoseTurn, byte i, Monster mon) {

        if (!monsterLoseTurn) {

            boolean monsterStance = mon.getAlgorithm()[i];

            if (mon.getName() == "Demon" && mon.getHp() < 200) {

                monsterStance = !monsterStance;

            }

            if (monsterStance) {
                System.out.println("You shield! Your opponent attacks! Your opponent flinched!");
                monsterLoseTurn = true;
            } else {
                System.out.println("You shield! Your opponent shields! Nothing happened!");
            }

        } else {
            System.out.println("You shield while your opponent is flinched. Nothing happened.");
            monsterLoseTurn = false;
        }

        return monsterLoseTurn;

    }

    public static boolean determineDefend(byte consecutiveShield, Random r, Hero hero) {

        if (consecutiveShield < 12) {

            if (!(consecutiveShield > 5 && r.nextInt(100) < 110 - (10 * consecutiveShield))) {

                return true; //1st to 5th time 100% succeed, 6th time 50% succeed, 7th time 40% succeed, 8th time 30% succeed and so on.

            } else {
                System.out.println("You shielded too much consecutively, your opponent figures your shield out and pierces!");
                hero.changeHp(-r.nextInt(11) + hero.getDex() - 30); //Decrease 15-30 HP
            }

        } else {
            System.out.println("Your opponent pierces your shield for a fatal attack!");
            hero.setHp(1); //prevents continuous shielding even after consecutive warning
        }

        return false;

    }

    public static boolean playerRun(Random r, Hero hero, Monster mon, byte i) {

        boolean playerRuns = false;

        if (r.nextInt(100) > 49) {
            System.out.println("You ran away.");
            playerRuns = true;
        } else {
            System.out.println("Run attempt failed!");
            if (mon.getAlgorithm()[i]) {
                System.out.println("Your opponent attacks!");
                hero.changeHp(-r.nextInt(11) + hero.getDex() - 20); //Decrease 5-20 HP
            } else {
                System.out.println("Your opponent shields!");
            }
        }

        return playerRuns;

    }

    public static void playerIdle(int i, Monster mon, Hero hero, Random r) {

        if (mon.getAlgorithm()[i]) {
            System.out.println("You lost a turn! Your opponent attacks!");
            hero.changeHp(-r.nextInt(11) + hero.getDex() - 20); //Decrease 5-20 HP

        } else {
            System.out.println("You lost a turn! Your opponent shields!");
        }

    }

    public static void playerLost(Hero hero) {

        hero.setHp(hero.getMaxHp());
        hero.setGold(0);
        hero.setLocation("Temple");
        System.out.println("You lost to your opponent! With your last health, you ran back to heal at the temple.");
        System.out.println("Along the way back, you encountered a robber and lost all your gold along the way.");

    }

}
