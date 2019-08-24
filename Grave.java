package com.muids;

import java.util.Scanner;

public class Grave {

    public static void atGrave(Hero hero, Scanner sc) {

        System.out.println(); //new event
        System.out.println("You entered into the graveyard... surrounded by mysterious fog.");

        while (true) {

            if (graveTurn(sc)) {
                System.out.println("You got a torn cloth. Torn cloth was put into inventory, show it to the guards!");
                hero.addItemToBag("Cloth");
                break;
            } else {
                System.out.println("You wound up at the entrance of the graveyard! You lost some health by the mysterious fog!");

                if (hero.getHp() >= 10) {
                    hero.changeHp(-10);
                    if (!Main.getBoolean(sc, "Retry?")) {break;}
                } else {
                    hero.setHp(1);
                    System.out.println("You are out of HP!");
                    break;
                }

                System.out.println();

            }

        }

        System.out.println("You walked back to the temple.");
        System.out.println(); //event ends

    }

    public static boolean graveTurn(Scanner sc) {

        boolean turn;

        turn = Main.getBoolean(sc, "You came across a crossroad. Do you turn to your right?");
        if (turn) {return false;}
        turn = Main.getBoolean(sc, "Next, do you turn to your left?");
        if (turn) {return false;}
        turn = Main.getBoolean(sc, "Next, do you go straight?");
        if (!turn) {return false;}
        turn = Main.getBoolean(sc, "You came across some stairs, going up and down. Do you climb up the stairs?");
        if (turn) {return false;}
        turn = Main.getBoolean(sc, "You become fatigued. Forge on?");
        if (!turn) {return false;}
        turn = Main.getBoolean(sc, "You've come far. Do you turn around and detour?");
        if (turn) {return false;}
        turn = Main.getBoolean(sc, "After a long walk, you heard some mechanical noise behind you. Turn back and detour?");
        if (!turn) {return false;}
        turn = Main.getBoolean(sc, "You found a tombstone, do you inspect it?");
        if (!turn) {return false;}
        turn = Main.getBoolean(sc, "Inside lies an empty coffin, do you continue your inspection?");
        if (!turn) {return false;}
        turn = Main.getBoolean(sc, "You found a torn piece of cloth, do you take it?");

        return turn;

    }

}
