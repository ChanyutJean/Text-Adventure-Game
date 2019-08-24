package com.muids;

import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Hero {

    public static final int BAG_LENGTH = 10;
    public static final int INITIAL_GOLD = 300;
    public static final int MIN_HP = 200;
    public static final int MAX_HP = 300;
    public static final int MIN_SKILL = 10;
    public static final int MAX_SKILL = 15;

    private int maxHp;
    private int hp;
    private String job;
    private String name;
    private int str;
    private int dex;
    private int con;
    private int gold;
    private String location;
    private int quest;
    private int talk = 0;
    private int arena = 0;
    private boolean fountain = false;
    private boolean restart = false;
    private boolean gameIsRunning = true;
    private boolean[] lostTo = new boolean[] {false, false, false}; //guard, priest, tycoon
    private String[] bag = new String[BAG_LENGTH];

    public static void sleep(int ms) {
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public static void slowPrint(String str) {

        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
            sleep(10);
        }

    }

    public void setMaxHp(int value) {maxHp = value;}
    public void setHp(int value) {hp = value;}
    public void setStr(int value) {str = value;}
    public void setDex(int value) {dex = value;}
    public void setCon(int value) {con = value;}
    public void setGold(int value) {gold = value;}
    public void setLocation(String value) {location = value;}
    public void setQuest(int value) {quest = value;}
    public void setTalk() {if (talk < 10) {talk++;} else {talk = 1;}}
    public void setArena() {arena++;}
    public void setFountain() {fountain = true;}
    public void setRestart() {restart = true;}
    public void setGameIsRunning() {gameIsRunning = false;}
    public void setLostTo(int i) {lostTo[i] = true;}



    public void changeHp(int value) {
        hp = hp + value;
        System.out.println("Your HP changed by " + value + ".");
    }

    public void changeGold(int value) {
        gold += value;
        System.out.println("Your gold changed by " + value + ".");
    }

    public int getMaxHp() {return maxHp;}
    public int getHp() {return hp;}
    public String getName() {return name;}
    public String getJob() {return job;}
    public int getStr() {return str;}
    public int getDex() {return dex;}
    public int getCon() {return con;}
    public int getGold() {return gold;}
    public String getLocation() {return location;}
    public int getQuest() {return quest;}
    public int getTalk() {return talk;}
    public int getArena() {return arena;}
    public boolean getFountain() {return fountain;}
    public boolean getRestart() {return restart;}
    public boolean getGameIsRunning() {return gameIsRunning;}
    public boolean getLostTo(int i) {return lostTo[i];}

    public void addItemToBag(String value) {
        for (int i = 0; i < BAG_LENGTH; i++) {
            if (bag[i] == null) {
                bag[i] = value;
                break;
            }
        }
    }

    public boolean isInBag(String value) {
        for (int i = 0; i < BAG_LENGTH; i++) {
            if (value.equals(bag[i])) {
                return true;
            }
        }
        return false;
    }

    public void printBag() {
        if (bag[0] == null) {
            slowPrint("None"); //never used
        }
        for (String i : bag) {
            if (!(i == null)) {
                slowPrint(i + " ");
            }
        }
    }

    public void setHeroStat(Scanner sc) {

        System.out.print("Choose your hero name: ");

        name = sc.next();

        String heroJob = "";

        while (!(heroJob.equals("Warrior") || heroJob.equals("Mage") || heroJob.equals("Ranger"))) {

            System.out.print("Choose your hero job (Warrior, Mage, Ranger): ");

            heroJob = sc.next();
        }

        job = heroJob;

        System.out.println();

    }

    public void assignStat(Random r) {

        str = (r.nextInt(Hero.MAX_SKILL - Hero.MIN_SKILL + 1) + Hero.MIN_SKILL);
        dex = (r.nextInt(Hero.MAX_SKILL - Hero.MIN_SKILL + 1) + Hero.MIN_SKILL);
        con = (r.nextInt(Hero.MAX_SKILL - Hero.MIN_SKILL + 1) + Hero.MIN_SKILL);
        hp = (r.nextInt(Hero.MAX_HP - Hero.MIN_HP + 1) + Hero.MIN_HP);
        maxHp = hp;

    }

    public void showStat() {

        slowPrint("Your hero's stat is:");
        System.out.println();
        slowPrint("Name: " + name);
        System.out.println();
        slowPrint("Job: " + job);
        System.out.println();
        slowPrint("HP: " + hp + "/" + maxHp);
        System.out.println();
        slowPrint("Strength: " + str);
        System.out.println();
        slowPrint("Dexterity: " + dex);
        System.out.println();
        slowPrint("Constitution: " + con);
        System.out.println();
        slowPrint("Gold: " + INITIAL_GOLD);
        System.out.println();

    }

    public void showNewStat() {

        showStat();

        slowPrint("Quest: ");
        System.out.println();

        switch (quest) {

            case 0:
                slowPrint("None at the moment.");
                System.out.println();
                break;
            case 1:
                slowPrint("Catch the Robbers!");
                System.out.println();
                break;
            case 2:
                slowPrint("Apprehend the Tycoon!");
                System.out.println();
                break;
            case 3:
                slowPrint("Conquer the Arena!");
                System.out.println();
                break;

        }

        slowPrint("Inventory: ");
        System.out.println();
        printBag();

        System.out.println();

    }

}
