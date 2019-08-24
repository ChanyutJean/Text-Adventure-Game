package com.muids;

import java.util.Random;
import java.util.Scanner;

public class Manor {

    public static void atManor(Hero hero, Scanner sc, Random r, Combat c) {

        if (!hero.getLostTo(2)) {

            System.out.println("You entered into the manor... and found the guard!");
            System.out.println("What do you want here? This place is very secluded, only people who know the password can enter!");
            System.out.print("What's the password? ");

            String password = sc.next();

            if (password.equals("Hello")) {

                System.out.println("Welcome! What do you want here? ...What, you want me to admit that I'm a grave robber? Never!");

            } else {

                System.out.println("Scram! Go to the tavern or something, you aren't worth coming in!");
                return;

            }
            System.out.println();

        } else {

            System.out.println("You again!");


        }

        Monster Tycoon = new Monster(500, "Tycoon", new boolean[]{false, false, true});

        c.fight(hero, sc, Tycoon, r);

        boolean defeated = (Tycoon.getHp() <= 0);

        if (defeated) {

            System.out.println("The tycoon lost and admits his guilt of grave robbery! Tycoon's guilty letter is added to the bag. Give it to the guards!");
            hero.addItemToBag("Letter");
            System.out.println();

        } else {

            System.out.println("It'll take you a million years until you can win me!");
            System.out.println();

        }

    }

}
