package com.muids;

import java.util.Random;
import java.util.Scanner;

public class Arena {

    public static void atArena(Hero hero, Scanner sc, Random r, Combat c) {

        if(hero.isInBag("Master")) {

            System.out.println("You stepped into the arena...");

            boolean breakLoop;

            switch (hero.getArena()) {

                case 0:

                    breakLoop = fightMonster(hero, sc, r, c, 500, "Goblin", new boolean[]{true, true, true, false});
                    if (breakLoop) {
                        break;
                    }

                case 1:

                    breakLoop = fightMonster(hero, sc, r, c, 200, "Fairy", new boolean[]{false, false, true, true});
                    if (breakLoop) {
                        break;
                    }//this guy heals 3HP when he makes you defend

                case 2:

                    breakLoop = fightMonster(hero, sc, r, c, 800, "Demon", new boolean[]{false, false, true, true, false, false, true, false, true, false});
                    if (breakLoop) {
                        break;
                    }//if hp<200 then flip algorithm


                case 3:

                    System.out.println("...Congratulations! You've defeated the boss of the arena!");
                    System.out.println();
                    System.out.println("-----END-----");
                    hero.setGameIsRunning();

            }

        } else {

            System.out.print("You need a master sword. You turned back. ");

        }

    }

    public static boolean fightMonster(Hero hero, Scanner sc, Random r, Combat c, int hp, String name, boolean[] algorithm) {

        Monster mon = new Monster(hp, name, algorithm);

        c.fight(hero, sc, mon, r);

        boolean defeated = (mon.getHp() <= 0);
        boolean breakLoop = false;

        if (defeated) {
            hero.setArena();
        } else {
            breakLoop = true;
        }

        return breakLoop;

    }

}
