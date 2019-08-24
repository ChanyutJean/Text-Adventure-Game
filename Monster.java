package com.muids;

public class Monster {

    private int maxHp;
    private int hp;
    private String name;
    private boolean[] algorithm;

    public Monster(int value1, String value2, boolean[] value3) {
        maxHp = value1;
        hp = value1;
        name = value2;
        algorithm = value3;
    }

    public void changeHp(int value) {
        hp = hp + value;
        System.out.println("Your opponent's HP changed by " + value + ".");
    }

    public int getHp() {return hp;}
    public String getName() {return name;}
    public boolean[] getAlgorithm() {return algorithm;}

    public void resetHp() {hp = maxHp;}

    public void printAlgorithm() {

        System.out.println("You defeated " + name + "!");
        System.out.print("Monster Action Algorithm: ");

        for (boolean i : algorithm) {
            if (i) {
                System.out.print("Attack ");
            } else {
                System.out.print("Defend ");
            }
        }

        System.out.println(); //ends algorithm line
        System.out.println(); //blank line

    }



}
