package Experimental;

import HUMANOID_FAMILY.*;

import java.io.Serializable;
import java.util.*;
import java.util.List;

public class Factions_Main {
    public static void main(String[] args) {

        //USER INPUT
        Scanner USER_INPUT = new Scanner(System.in);
        String choice = "";
        boolean valid = false;


        //INIT GAME SCREEN
        HashMap<Integer, String> game_key = new HashMap();
        game_key.put(-18, "18");                 // -18  "OPEN AREA"
        game_key.put(-17, "17");                 // -17  "OPEN AREA"
        game_key.put(-16, "16");                 // -16  "OPEN AREA"
        game_key.put(-15, "15");                 // -15  "OPEN AREA"
        game_key.put(-14, "14");                 // -14  "OPEN AREA"
        game_key.put(-13, "13");                 // -13  "OPEN AREA"
        game_key.put(-12, "12");                 // -12  "OPEN AREA"
        game_key.put(-11, "11");                 // -11  "OPEN AREA"
        game_key.put(-10, "10");                 // -10  "OPEN AREA"
        game_key.put(-9, "9");                   //  -1  "OPEN AREA"
        game_key.put(-8, "8");                   //  -1  "OPEN AREA"
        game_key.put(-7, "7");                   //  -1  "OPEN AREA"
        game_key.put(-6, "6");                   //  -1  "OPEN AREA"
        game_key.put(-5, "5");                   //  -1  "OPEN AREA"
        game_key.put(-4, "4");                   //  -1  "OPEN AREA"
        game_key.put(-3, "3");                   //  -1  "OPEN AREA"
        game_key.put(-2, "2");                   //  -1  "OPEN AREA"
        game_key.put(-1, "1");                   //  -1  "OPEN AREA"
        game_key.put(0, "\uD83C\uDF32");         //   0  "SCENERY"
        game_key.put(1, "\uD83E\uDD37\u200D");   //   1  "HUMAN"
        game_key.put(2, "\uD83D\uDC7A");         //   2  "GOBLIN"
        game_key.put(3, "\uD83D\uDC7D");         //   3  "MARTIAN"
        game_key.put(4, "\uD83E\uDD22");         //   4  "ZOMBIE"
        game_key.put(5, "\uD83C\uDF0C");         //   5  "SOLAR POTATO"
        game_key.put(7, "◼");                    //   7  "White Tile"
        game_key.put(8, "⬜");                    //   8  "Black Tile"
        game_key.put(9, "");                     //   9  "OPEN AREA"








        int[][] game_board = {
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 9, 9, 9, 9, 9, 2, 2, 2, 2, 2, 2, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 3, 9, 9, 9, 9, 9, 5, 5, 5, 5, 5, 9, 9, 9, 9, 9, 9, 4, 0},
                {0, 3, 9, 9, 9, 9, 9, 5, 9, 9, 9, 5, 9, 9, 9, 9, 9, 9, 4, 0},
                {0, 3, 9, 9, 9, 9, 9, 5, 9, 9, 9, 5, 9, 9, 9, 9, 9, 9, 4, 0},
                {0, 3, 9, 9, 9, 9, 9, 5, 9, 9, 9, 5, 9, 9, 9, 9, 9, 9, 4, 0},
                {0, 3, 9, 9, 9, 9, 9, 5, 5, 5, 5, 5, 9, 9, 9, 9, 9, 9, 4, 0},
                {0, 3, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 4, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 1, 1, 1, 1, 1, 1, 9, 9, 9, 9, 9, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
        };

//        int[][] game_board = {
//                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 7, 8, 7, 8, 7, 8, 2, 2, 2, 2, 2, 2, 7, 8, 7, 8, 7, 8, 0},
//                {0, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 0},
//                {0, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 0},
//                {0, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 0},
//                {0, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 0},
//                {0, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 0},
//                {0, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 0},
//                {0, 3, 9, 9, 9, 9, 9, 5, 5, 5, 5, 5, 9, 9, 9, 9, 9, 9, 4, 0},
//                {0, 3, 9, 9, 9, 9, 9, 5, 9, 9, 9, 5, 9, 9, 9, 9, 9, 9, 4, 0},
//                {0, 3, 9, 9, 9, 9, 9, 5, 9, 9, 9, 5, 9, 9, 9, 9, 9, 9, 4, 0},
//                {0, 3, 9, 9, 9, 9, 9, 5, 9, 9, 9, 5, 9, 9, 9, 9, 9, 9, 4, 0},
//                {0, 3, 9, 9, 9, 9, 9, 5, 5, 5, 5, 5, 9, 9, 9, 9, 9, 9, 4, 0},
//                {0, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 0},
//                {0, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 0},
//                {0, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 0},
//                {0, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 0},
//                {0, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 0},
//                {0, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 0},
//                {0, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 0},
//                {0, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 0},
//                {0, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 0},
//                {0, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 8, 7, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
//        };














        //DRAW SCREEN INIT
        GameFunctionality g = new GameFunctionality();


        //GAME RULES 2 DIMENSIONAL ARRAY
        System.out.println("\n\n\nGAME RULES");
        System.out.println("-\t Add input of direction you want to move ... EXAMPLES: up , down , Left , right ");


        System.out.println("\n\n\tPlease Choose Faction...\n");
        System.out.println("1. Human");
        System.out.println("2. Goblin");
        System.out.println("3. Zombie");
        System.out.println("4. Martian");


        while (!valid) {
            System.out.print("\n> ");
            choice = USER_INPUT.nextLine();
            valid = g.validateCharacterSelect(choice);
        }


        List<Human> human_list = new ArrayList<>();
        List<Goblin> goblin_list = new ArrayList<>();
        List<Zombie> zombie_list = new ArrayList<>();
        List<Martian> martian_list = new ArrayList<>();


        int AMOUNT_OF_ENEMIES = 6;
        int[] human_goblin_loc = {7, 8, 9, 10, 11, 12};
        int[] martian_zombie_loc = {9, 10, 11, 12, 13, 14};
        int iterator = 0;
        int[] coordinates;
        Random random = new Random();


        for (int x = 0; x < AMOUNT_OF_ENEMIES; x++) {
            coordinates = new int[]{2, human_goblin_loc[iterator]};
            human_list.add(new Human(random, coordinates));

            coordinates = new int[]{21, human_goblin_loc[iterator]};
            goblin_list.add(new Goblin(random, coordinates));

            coordinates = new int[]{martian_zombie_loc[iterator], 1};
            martian_list.add(new Martian(random, coordinates));

            coordinates = new int[]{martian_zombie_loc[iterator], 18};
            zombie_list.add(new Zombie(random, coordinates));

            iterator++;
        }


        // TURN BASED LIST
        HashMap<Integer, List> game_mobs = new HashMap<>();
        game_mobs.put(1, human_list);
        game_mobs.put(2, goblin_list);
        game_mobs.put(3, zombie_list);
        game_mobs.put(4, martian_list);


        //CONSOLE APP
        int turn = 1;
        while (turn < 5) {

            boolean players_round;

            if (choice.equals("1") && turn == 1) {
                players_round = true;
            } else if (choice.equals("2") && turn == 2) {
                players_round = true;
            } else if (choice.equals("3") && turn == 3) {
                players_round = true;
            } else if (choice.equals("4") && turn == 4) {
                players_round = true;
            }


            // DISPLAY GAME
            for (int i = 0; i < game_board.length; i++) {
                System.out.println();
                System.out.print(String.valueOf(i) + "\t");
                //EACH ROW
                for (int y = 0; y < game_board[i].length; y++) {
                    System.out.print(game_key.get(game_board[i][y]) + "\t");
                }
                // APPEND TO SIDE BAR SCREEN
                List current_humainoid = game_mobs.get(turn);
                if (i == 0) System.out.print("TURN NUMBER: " + turn);
                if (i == 1) System.out.print("PLAYER: " + current_humainoid.get(0).getClass().getName());
                if (i == 2) System.out.print("TROOPS: " + current_humainoid.size());
                if (i == 3) System.out.print("STATS");
                if (i == 4) {
                    System.out.print("health".toUpperCase() + "\t\t\t");
                    for (int x = 0; x < current_humainoid.size(); x++) {
                        if (turn == 1) {
                            Human human = (Human) current_humainoid.get(x);
                            System.out.print(human.getHealth() + "\t\t|\t");
                        } else if (turn == 2) {
                            Goblin goblin = (Goblin) current_humainoid.get(x);
                            System.out.print(goblin.getHealth() + "\t\t|\t");
                        } else if (turn == 3) {
                            Zombie zombie = (Zombie) current_humainoid.get(x);
                            System.out.print(zombie.getHealth() + "\t\t|\t");
                        } else if (turn == 4) {
                            Martian martian = (Martian) current_humainoid.get(x);
                            System.out.print(martian.getHealth() + "\t\t|\t");
                        }

                    }

                }
                if (i == 5) {
                    System.out.print("strength".toUpperCase() + "\t\t");
                    for (int x = 0; x < current_humainoid.size(); x++) {
                        if (turn == 1) {
                            Human human = (Human) current_humainoid.get(x);
                            System.out.print(human.getStrength() + "\t\t|\t");
                        } else if (turn == 2) {
                            Goblin goblin = (Goblin) current_humainoid.get(x);
                            System.out.print(goblin.getStrength() + "\t\t|\t");
                        } else if (turn == 3) {
                            Zombie zombie = (Zombie) current_humainoid.get(x);
                            System.out.print(zombie.getStrength() + "\t\t|\t");
                        } else if (turn == 4) {
                            Martian martian = (Martian) current_humainoid.get(x);
                            System.out.print(martian.getStrength() + "\t\t|\t");
                        }

                    }

                }
                if (i == 6) {
                    System.out.print("combat lvl".toUpperCase() + "\t\t");
                    for (int x = 0; x < current_humainoid.size(); x++) {
                        if (turn == 1) {
                            Human human = (Human) current_humainoid.get(x);
                            System.out.print(human.getCombatLVL() + "\t\t|\t");
                        } else if (turn == 2) {
                            Goblin goblin = (Goblin) current_humainoid.get(x);
                            System.out.print(goblin.getCombatLVL() + "\t\t|\t");
                        } else if (turn == 3) {
                            Zombie zombie = (Zombie) current_humainoid.get(x);
                            System.out.print(zombie.getCombatLVL() + "\t\t|\t");
                        } else if (turn == 4) {
                            Martian martian = (Martian) current_humainoid.get(x);
                            System.out.print(martian.getCombatLVL() + "\t\t|\t");
                        }

                    }

                }
                if (i == 7) {
                    System.out.print("Compassion".toUpperCase() + "\t\t");
                    for (int x = 0; x < current_humainoid.size(); x++) {
                        if (turn == 1) {
                            Human human = (Human) current_humainoid.get(x);
                            System.out.print(human.getCompassion() + "\t\t|\t");
                        } else if (turn == 2) {
                            Goblin goblin = (Goblin) current_humainoid.get(x);
                            System.out.print(goblin.getCompassion() + "\t\t|\t");
                        } else if (turn == 3) {
                            Zombie zombie = (Zombie) current_humainoid.get(x);
                            System.out.print(zombie.getCompassion() + "\t\t|\t");
                        } else if (turn == 4) {
                            Martian martian = (Martian) current_humainoid.get(x);
                            System.out.print(martian.getCompassion() + "\t\t|\t");
                        }

                    }

                }
                if (i == 8) {
                    System.out.print("intelligence".toUpperCase() + "\t");
                    for (int x = 0; x < current_humainoid.size(); x++) {
                        if (turn == 1) {
                            Human human = (Human) current_humainoid.get(x);
                            System.out.print(human.getIntelligence() + "\t\t|\t");
                        } else if (turn == 2) {
                            Goblin goblin = (Goblin) current_humainoid.get(x);
                            System.out.print(goblin.getIntelligence() + "\t\t|\t");
                        } else if (turn == 3) {
                            Zombie zombie = (Zombie) current_humainoid.get(x);
                            System.out.print(zombie.getIntelligence() + "\t\t|\t");
                        } else if (turn == 4) {
                            Martian martian = (Martian) current_humainoid.get(x);
                            System.out.print(martian.getIntelligence() + "\t\t|\t");
                        }

                    }

                }
                if (i == 9) {
                    System.out.print("\t");
                    for (int x = 0; x < current_humainoid.size(); x++) {
                        System.out.print("\t\t\t[" + x + "]");
                    }
                }



                if (i == 20) System.out.print("Choose Player & Faction to Attack!");


            }


            //TODO
            // options to fight someone
            // go inside the ring and fight it out
            // stats displayed on the right side of the screen


            //USER CONTROLS
            System.out.print(">\t");
            choice = USER_INPUT.nextLine();
            g.coordinate(choice);


            turn++;
            players_round = false;
        }


        //MULTI THREADING BOILER
//        Scanner USER_INPUT = new Scanner(System.in);
//        Experimental.Multithreading_OBJECT game_screen = new Experimental.Multithreading_OBJECT();
//        Experimental.Multithreading_OBJECT input_screen = new Experimental.Multithreading_OBJECT();
//
//        game_screen.display_Screen_Thread(game_board , game_key );
//        input_screen.display_input_Thread(choice , g);


//        RUN ALONE FOR KEY LOGGER
//        Frame f = g.INIT_SCREEN();

//        RUN FOR ARRAY OF BUTTONS
//        Frame f = g.INIT_SCREEN();
//        for (int x = 0; x < game_board.length; x++) {
//            for (int y = 0; y < game_board[x].length; y++) {
//                f.add(new Button(game_key.get(game_board[x][y])));
//            }
//        }


    }
}
