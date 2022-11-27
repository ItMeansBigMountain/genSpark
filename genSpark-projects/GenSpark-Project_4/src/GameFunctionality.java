import HUMANOID_FAMILY.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;


public class GameFunctionality implements KeyListener {


    //KEYBOARD LISTENER OVER-RIDE     FOR THE GUI
    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("The key Typed was: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isActionKey())
            System.exit(0);
        System.out.println("The key Pressed was: " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("The key Released was: " + e.getKeyChar());
    }

    public Frame INIT_SCREEN() {
        //Setting the Frame and Labels
        Frame f = new Frame("Humans vs. Goblins");
        f.setLayout(new GridLayout(10, 10));
        f.setSize(500, 500);
        f.setVisible(true);

        //Creating and adding the key listener
        GameFunctionality k = this;
        f.addKeyListener(k);
        return f;
    }


    //CONSOLE APPLICATION
    public int[][] validateCharacterSelect(String choice, int[] dice) {

        // " 21,6 . 16,6 "
        String[] str_arr = choice.split("[.]");

        //INVALID ARGUMENTS
        if (str_arr.length < 2) {
            System.out.println("Please enter a valid option!\n");
            return null;
        }


        //STRIP SPACES
        for (int x = 0; x < str_arr.length; x++) {
            str_arr[x] = str_arr[x].strip();
        }

        try {
            //ASSIGN INPUT DATA
            String[] from_coordinate_string = str_arr[0].split(",");
            String[] to_coordinate_string = str_arr[1].split(",");

            int[] from = {Integer.parseInt(from_coordinate_string[1]), Integer.parseInt(from_coordinate_string[0])};
            int[] to = {Integer.parseInt(to_coordinate_string[1]), Integer.parseInt(to_coordinate_string[0])};


            //CHECK IF "TO" COORDINATE WITHIN RANGE OF DICE ROLL
            if (to[1] < (from[1] - dice[1]) || to[1] > (dice[1] + from[1])) { //HORIZONTAL    8,12    5,5
                throw new IllegalArgumentException();
            }
            if (to[0] < (from[0] - dice[0]) || to[0] > (dice[0] + from[0])) { //VERTICAL
                throw new IllegalArgumentException();
            }

            return new int[][]{from, to};

        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Please enter coordinates\nHORIZONTAL: %o\nVERTICAL: %o", dice[1], dice[0]));
            return null;
        } catch (Exception e) {
            System.out.println("Please enter a valid option!\n");
            return null;
        }

    }



    public static List<Object> fight(
            HashMap<String, HashMap<String, Method>> selected_mob_functions,
            Stack<String> messages,
            List<Object> enemy_mobs,
            Object selected_mob,
            int[] to,
            boolean player1
    ) {
        List<Object> output = new ArrayList<>();
        Object winner = null;

        //FIND ENEMY_MOB WHO COLLIDES WITH SELECTED MOB
        //RUN RANDOM PROBABILITIES BASED ON THEIR STATISTICS TO DECIDE WHO WINS
        //WINNER KEEPS THE SPOT


        // FETCH OBJECT METHODS FROM TROOPS (current_mobs) LIST
        Object iteration_mob = null;
        int mob_index = 0;
        HashMap<String, HashMap<String, Method>> interactive_functions = null;
        HashMap<String, Method> setters = null;
        HashMap<String, Method> getters = null;
        int[] current_coordinates = null;
        boolean found = false;


        //PARSE EVERY ENEMY MOB
        for (int i = 0; i < enemy_mobs.size(); i++) {
            //CALL SETTERS & GETTERS OF INTERACTED OBJECT
            iteration_mob = enemy_mobs.get(i);
            mob_index = i;
            interactive_functions = Humanoid.setters_and_getters(iteration_mob);
            setters = interactive_functions.get("setters");
            getters = interactive_functions.get("getters");

            // FUNCTIONALITY ALGO
            try {
                // METHODS TO INVOKE UPON OTHER INTRACTABLE
                Method get_location = Humanoid.fetchMethod(getters, "getCoordinate()");

                // INVOKE GET PROCESS
                current_coordinates = (int[]) get_location.invoke(iteration_mob);

                //CHECK IF CURRENT COORDINATE == USER INPUT:TO
                if (current_coordinates[0] == to[0] && current_coordinates[1] == to[1]) {
                    found = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
                //NULL EXCEPTION IS WHEN OBJECT ISN'T INTRACTABLE
            }
        }


        // COORDINATE NOT FOUND
        if (!found) {
            messages.push(player1 ? "PLAYER ONE!!!" : "PLAYER TWO!!!" + "INVALID COORDINATE: Cannot attack mates!");
            output.add(messages);
            output.add(winner);
            return output;
        }


        //FIGHT decide winner
        //SELECTED PLAYER FUNCTIONS
        Method selected_player_get_cb_lvl = Humanoid.fetchMethod(selected_mob_functions.get("getters"), "getCombatLVL()");
        Method selected_player_get_health = Humanoid.fetchMethod(selected_mob_functions.get("getters"), "getHealth()");
        Method selected_player_get_attack = Humanoid.fetchMethod(selected_mob_functions.get("getters"), "getAttack()");
        Method selected_player_get_strength = Humanoid.fetchMethod(selected_mob_functions.get("getters"), "getStrength()");
        Method selected_player_get_defence = Humanoid.fetchMethod(selected_mob_functions.get("getters"), "getDefence()");
        Method selected_player_get_intelligence = Humanoid.fetchMethod(selected_mob_functions.get("getters"), "getIntelligence()");
        Method selected_player_get_compassion = Humanoid.fetchMethod(selected_mob_functions.get("getters"), "getCompassion()");
        Method selected_player_set_health = Humanoid.fetchMethod(selected_mob_functions.get("setters"), "setHealth(int)");


        // ENEMY METHODS
        Method get_cb_lvl = Humanoid.fetchMethod(getters, "getCombatLVL()");
        Method get_health = Humanoid.fetchMethod(getters, "getHealth()");
        Method get_attack = Humanoid.fetchMethod(getters, "getAttack()");
        Method get_strength = Humanoid.fetchMethod(getters, "getStrength()");
        Method get_defence = Humanoid.fetchMethod(getters, "getDefence()");
        Method get_intelligence = Humanoid.fetchMethod(getters, "getIntelligence()");
        Method get_compassion = Humanoid.fetchMethod(getters, "getCompassion()");
        Method set_health = Humanoid.fetchMethod(setters, "setHealth(int)");

        //simulate your attack/defence roll
        //simulate their attack/defence roll
        //do maths and iterations to subtract hp
        //find winner
        //push fight stats to message board
        String fight_round_results = "";
        String team = "";
        try {
            Random random = new Random();

            //INIT STAT METHODS
            int selected_cb = (int) selected_player_get_cb_lvl.invoke(selected_mob);
            int selected_hp = (int) selected_player_get_health.invoke(selected_mob);
            int selected_attack = (int) selected_player_get_attack.invoke(selected_mob);
            int selected_strength = (int) selected_player_get_strength.invoke(selected_mob);
            int selected_defence = (int) selected_player_get_defence.invoke(selected_mob);
            int selected_intelligence = (int) selected_player_get_intelligence.invoke(selected_mob);
            int selected_compassion = (int) selected_player_get_compassion.invoke(selected_mob);

            int enemy_cb = (int) get_cb_lvl.invoke(iteration_mob);
            int enemy_hp = (int) get_health.invoke(iteration_mob);
            int enemy_attack = (int) get_attack.invoke(iteration_mob);
            int enemy_strength = (int) get_strength.invoke(iteration_mob);
            int enemy_defence = (int) get_defence.invoke(iteration_mob);
            int enemy_intelligence = (int) get_intelligence.invoke(iteration_mob);
            int enemy_compassion = (int) get_compassion.invoke(iteration_mob);


            //INIT RANDOM RANGES FROM THESE STATS
            int your_random_atk;
            int your_random_def;
            int your_random_intel;
            int your_random_compassion;
            int your_random_str;
            double your_atk;
            double your_def;

            int their_random_atk;
            int their_random_def;
            int their_random_intel;
            int their_random_compassion;
            int their_random_str;
            double their_atk;
            double their_def;


            // FIGHT SIMULATION LOOP
            int round_number = 0;
            while ((int) selected_player_get_health.invoke(selected_mob) > 0 && (int) get_health.invoke(iteration_mob) > 0) {

                //RANDOM PLAYER STATS RNG
                your_random_atk = random.ints(1, selected_attack).findFirst().getAsInt();
                your_random_def = random.ints(1, selected_defence).findFirst().getAsInt();
                your_random_intel = random.ints(1, selected_intelligence).findFirst().getAsInt();
                your_random_compassion = random.ints(1, selected_compassion).findFirst().getAsInt();
                your_random_str = random.ints(1, selected_strength).findFirst().getAsInt();

                your_atk = (Math.floor(Math.ceil(your_random_atk * your_random_intel) - your_random_compassion) * (your_random_str + 64));
                your_def = (Math.floor(Math.ceil(your_random_def * your_random_intel) - your_random_compassion) * (your_random_str + 64));

                //RANDOM ENEMY STATS RNG
                their_random_atk = random.ints(1, enemy_attack).findFirst().getAsInt();
                their_random_def = random.ints(1, enemy_defence).findFirst().getAsInt();
                their_random_intel = random.ints(1, enemy_intelligence).findFirst().getAsInt();
                their_random_compassion = random.ints(1, enemy_compassion).findFirst().getAsInt();
                their_random_str = random.ints(1, enemy_strength).findFirst().getAsInt();

                their_atk = Math.floor(Math.ceil(their_random_atk * their_random_intel) - their_random_compassion) * (their_random_str + 64);
                their_def = Math.floor(Math.ceil(their_random_def * their_random_intel) - their_random_compassion) * (their_random_str + 64);


                //DISPLAY FIGHT HITS
                int hp;
                if (your_atk > their_def) {
                    hp = (int) get_health.invoke(iteration_mob);
                    set_health.invoke(iteration_mob, hp - 1);
                    fight_round_results += "A ";
                } else if (their_atk > your_def) {
                    hp = (int) selected_player_get_health.invoke(selected_mob);
                    selected_player_set_health.invoke(selected_mob, hp - 1);
                    fight_round_results += "D ";
                }


                //ROUND RESULTS
                System.out.println(fight_round_results.charAt(fight_round_results.length() - 2) == 'A' ? "ATTACK WINS" : "DEFENCE WINS");
                System.out.println("atk roll\t Attacker: " + your_atk + "\tvs.\t" + "Defenders: " + their_def);
                System.out.println("def roll\t Attacker: " + your_def + "\tvs.\t" + "Defenders: " + their_atk);
                System.out.println("------------");

                round_number++;
            }


            //RIGHT AFTER THE FIGHT

            if ((int) selected_player_get_health.invoke(selected_mob) > (int) get_health.invoke(iteration_mob)) {
                winner = selected_mob;
                team = "ATTACKER ";
            } else {
                winner = iteration_mob;
                team = "DEFENDER ";
            }


        } catch (Exception e) {
            System.out.println(e);
            System.out.println("There was a problem calculating a winner");
        }


        //MESSAGE BOX UPDATE (4 lines MAX ) & RETURN SUCCESSFUL
        messages.push("Winner:    " + team + winner);
        messages.push("ROUND RESULTS: " + fight_round_results);
        messages.push(player1 ? "Player one attacked Player two" : "Player two attacked Player one");

        output.add(messages);
        output.add(winner);
        output.add(team.charAt(0));
        output.add(interactive_functions);
        output.add(mob_index);
        return output;
    }




    public ArrayList<Object> coordinate(
            boolean player1_turn,
            Stack<String> messages,
            int[][] coordinates,
            int[][] gameBoard,
            List current_mobs,
            List enemy_mobs
    ) {
        ArrayList<Object> output = new ArrayList<Object>();

        //CHECK FOR OUT OF BOUNDS COORDINATES
        for (int i = 0; i < coordinates.length; i++) {
            if (coordinates[i][0] < 2 || coordinates[i][0] > 21) {
                System.out.println("Coordinates must stay within the map");
                output.add(false);
                output.add(gameBoard);
                output.add(current_mobs);
                output.add(enemy_mobs);
                output.add(messages);
                return output;
            } else if (coordinates[i][0] < 1 || coordinates[i][1] > 18) {
                System.out.println("Coordinates must stay within the map");
                output.add(false);
                output.add(gameBoard);
                output.add(current_mobs);
                output.add(enemy_mobs);
                output.add(messages);
                return output;
            }
        }


        //CREATE FROM & TO COORDINATES
        int[] from = coordinates[0];
        int[] to = coordinates[1];


        // FETCH OBJECT METHODS FROM TROOPS (current_mobs) LIST
        HashMap<String, HashMap<String, Method>> interactive_functions = null;
        HashMap<String, Method> setters = null;
        HashMap<String, Method> getters = null;
        HashMap<String, Method> functional_functions = null;


        //CHECK IF COORDINATES ARE IN PLAYERS TROOPS
        Boolean found = false;
        int[] current_coordinates = new int[2];
        Object selected_mob = null;
        int selected_mob_index = 0;
        for (int i = 0; i < current_mobs.size(); i++) {
            selected_mob_index = i;
            //CALL SETTERS & GETTERS OF INTERACTED OBJECT
            selected_mob = current_mobs.get(i);
            interactive_functions = Humanoid.setters_and_getters(selected_mob);
            setters = interactive_functions.get("setters");
            getters = interactive_functions.get("getters");
            functional_functions = interactive_functions.get("functional");

            // FUNCTIONALITY ALGO
            try {
                // METHODS TO INVOKE UPON OTHER INTRACTABLE
                Method get_location = Humanoid.fetchMethod(getters, "getCoordinate()");

                // INVOKE GET PROCESS
                current_coordinates = (int[]) get_location.invoke(selected_mob);

                //CHECK IF CURRENT COORDINATE == USER INPUT:FROM
                if (current_coordinates[0] == from[0] && current_coordinates[1] == from[1]) {
                    found = true;
                    break;
                }
            } catch (Exception e) {
                System.out.println(e);
                //NULL EXCEPTION IS WHEN OBJECT ISN'T INTRACTABLE
            }
        }


        // COORDINATE NOT FOUND
        if (!found) {
            System.out.println("INVALID PLAYER COORDINATE");
            output.add(false);
            output.add(gameBoard);
            output.add(current_mobs);
            output.add(enemy_mobs);
            output.add(messages);
            return output;
        }


        //PLACE OR FIGHT PLAYERS
        List<Object> fight_outcome;
        Method set_location = Humanoid.fetchMethod(setters, "setCoordinate(int[])");
        Method get_location = Humanoid.fetchMethod(getters, "getCoordinate()");
        Method intended_powerup = Humanoid.fetchMethod(functional_functions, "intended_powerup()");
        Method un_intended_powerup = Humanoid.fetchMethod(functional_functions, "un_intended_powerup()");
        try {
            //COLLIDING WITH OTHER PLAYER OR POWER UP  WHEN MOVING

            //CHECK IF POWER-UP
            boolean powerup = false;
            boolean SUPER_powerup = false;
            if (gameBoard[to[0]][to[1]] == 5) {
                powerup = true;
                if (selected_mob instanceof Human) {
                    intended_powerup.invoke(selected_mob);
                    SUPER_powerup = true;
                } else {
                    un_intended_powerup.invoke(selected_mob);
                }

            } else if (gameBoard[to[0]][to[1]] == 6) {
                powerup = true;
                if (selected_mob instanceof Goblin) {
                    intended_powerup.invoke(selected_mob);
                    SUPER_powerup = true;
                } else {
                    un_intended_powerup.invoke(selected_mob);
                }
            } else if (gameBoard[to[0]][to[1]] == 7) {
                powerup = true;
                if (selected_mob instanceof Martian) {
                    SUPER_powerup = true;
                    intended_powerup.invoke(selected_mob);
                } else {
                    un_intended_powerup.invoke(selected_mob);
                }
            } else if (gameBoard[to[0]][to[1]] == 8) {
                powerup = true;
                if (selected_mob instanceof Zombie) {
                    SUPER_powerup = true;
                    intended_powerup.invoke(selected_mob);
                } else {
                    un_intended_powerup.invoke(selected_mob);
                }
            } else if (!(gameBoard[to[0]][to[1]] == 9)) {

                //FIGHT!
                fight_outcome = fight(interactive_functions, messages, enemy_mobs, selected_mob, to, player1_turn);
                messages = (Stack<String>) fight_outcome.get(0);
                Object winner = (Object) fight_outcome.get(1);

                //FRIENDLY FIRE
                if (winner == null) {
                    System.out.println("YOU CANNOT ATTACK MATES.");
                    output.add(false);
                    output.add(gameBoard);
                    output.add(current_mobs);
                    output.add(enemy_mobs);
                    output.add(messages);
                    return output;

                }
                //VALID FIGHT RESULTS
                else {
                    //REMOVE LOSER FROM EITHER current_mobs OR enemy_mobs
                    Character team = (Character) fight_outcome.get(2);
                    HashMap<String, HashMap<String, Method>> enemy_functions = (HashMap<String, HashMap<String, Method>>) fight_outcome.get(3);
                    int enemy_mob_index = (int) fight_outcome.get(4);

                    //SELECTED OBJECT WINS, remove winner from enemy_mobs
                    if (team == 'A') {
                        enemy_mobs.remove(enemy_mob_index);
                        //SWAPPING PLAYER ON GAME BOARD!
                        set_location.invoke(selected_mob, to);
                        int temp = gameBoard[from[0]][from[1]];
                        gameBoard[from[0]][from[1]] = 9;
                        gameBoard[to[0]][to[1]] = temp;
                    }

                    //ENEMY OBJECT WINS, remove winner from current_mobs
                    else {
                        //SWAPPING PLAYER ON GAME BOARD!
                        current_mobs.remove(selected_mob_index);
                        gameBoard[from[0]][from[1]] = 9;

                    }

                    //DEBUG
                    // System.out.println(current_mobs); // update if enemy wins
                    // System.out.println(enemy_mobs); // update if you win

                    // System.out.println("\n");
                    // System.out.println(winner);
                    // System.out.println(team);

                    // System.out.println("Attacker index: " + selected_mob_index);
                    // System.out.println("Defender index: " + enemy_mob_index);

                    // System.out.println(interactive_functions);
                    // System.out.println(enemy_functions);


                }


            } else {
                //SWAPPING EMPTY-SPACE AND PLAYER
                set_location.invoke(selected_mob, to);
                int temp = gameBoard[from[0]][from[1]];
                gameBoard[from[0]][from[1]] = gameBoard[to[0]][to[1]];
                gameBoard[to[0]][to[1]] = temp;
            }


            if (powerup) {
                //SWAPPING EMPTY-SPACE AND PLAYER
                set_location.invoke(selected_mob, to);
                int temp = gameBoard[from[0]][from[1]];
                gameBoard[from[0]][from[1]] = gameBoard[to[0]][to[1]];
                gameBoard[to[0]][to[1]] = temp;
                messages.push(SUPER_powerup ? "SUPER POWER UP COLLECTED!" : "POWER UP COLLECTED");
            }

        } catch (Exception e) {
            System.out.println(e);

        }


        //SUCCESSFUL RETURN
        output.add(true);
        output.add(gameBoard);
        output.add(current_mobs);
        output.add(enemy_mobs);
        output.add(messages);
        return output;
    }





    public void draw_game(
            int[][] game_board,
            int[] dice,
            int display_int,
            int round,
            boolean player1_turn,
            List<Object> current_mobs,
            HashMap<Integer, String> game_key,
            Stack<String> message_box
    ) {
        for (int i = 0; i < game_board.length; i++) {
            System.out.println();
            System.out.print(String.valueOf(i) + "\t");
            //EACH ROW
            for (int y = 0; y < game_board[i].length; y++) {
                System.out.print(game_key.get(game_board[i][y]) + "\t");
            }
            // APPEND TO SIDE BAR SCREEN
            if (i == 0) System.out.print("TURN: " + round);
            if (i == 1) System.out.print("PLAYER: " + (player1_turn ? "One" : "Two"));
            if (i == 2) System.out.print("TROOPS: " + current_mobs.size());
            if (i == 4) System.out.print("STATS");
            if (i == 5) {
                System.out.print("hp".toUpperCase() + "\t\t\t\t\t");
                for (int x = 0; x < current_mobs.size(); x++) {
                    if (current_mobs.get(x) instanceof Human) {
                        Human human = (Human) current_mobs.get(x);
                        System.out.print(human.getHealth() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Goblin) {
                        Goblin goblin = (Goblin) current_mobs.get(x);
                        System.out.print(goblin.getHealth() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Zombie) {
                        Zombie zombie = (Zombie) current_mobs.get(x);
                        System.out.print(zombie.getHealth() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Martian) {
                        Martian martian = (Martian) current_mobs.get(x);
                        System.out.print(martian.getHealth() + "\t|\t");
                    }

                }

            }
            if (i == 6) {
                System.out.print("atk".toUpperCase() + "\t\t\t\t\t");
                for (int x = 0; x < current_mobs.size(); x++) {
                    if (current_mobs.get(x) instanceof Human) {
                        Human human = (Human) current_mobs.get(x);
                        System.out.print(human.getAttack() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Goblin) {
                        Goblin goblin = (Goblin) current_mobs.get(x);
                        System.out.print(goblin.getAttack() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Zombie) {
                        Zombie zombie = (Zombie) current_mobs.get(x);
                        System.out.print(zombie.getAttack() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Martian) {
                        Martian martian = (Martian) current_mobs.get(x);
                        System.out.print(martian.getAttack() + "\t|\t");
                    }

                }

            }
            if (i == 7) {
                System.out.print("str".toUpperCase() + "\t\t\t\t\t");
                for (int x = 0; x < current_mobs.size(); x++) {
                    if (current_mobs.get(x) instanceof Human) {
                        Human human = (Human) current_mobs.get(x);
                        System.out.print(human.getStrength() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Goblin) {
                        Goblin goblin = (Goblin) current_mobs.get(x);
                        System.out.print(goblin.getStrength() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Zombie) {
                        Zombie zombie = (Zombie) current_mobs.get(x);
                        System.out.print(zombie.getStrength() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Martian) {
                        Martian martian = (Martian) current_mobs.get(x);
                        System.out.print(martian.getStrength() + "\t|\t");
                    }

                }

            }
            if (i == 8) {
                System.out.print("def".toUpperCase() + "\t\t\t\t\t");
                for (int x = 0; x < current_mobs.size(); x++) {
                    if (current_mobs.get(x) instanceof Human) {
                        Human human = (Human) current_mobs.get(x);
                        System.out.print(human.getDefence() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Goblin) {
                        Goblin goblin = (Goblin) current_mobs.get(x);
                        System.out.print(goblin.getDefence() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Zombie) {
                        Zombie zombie = (Zombie) current_mobs.get(x);
                        System.out.print(zombie.getDefence() + "\t|\t");
                    } else if (current_mobs.get(x) instanceof Martian) {
                        Martian martian = (Martian) current_mobs.get(x);
                        System.out.print(martian.getDefence() + "\t|\t");
                    }
                }

            }
            if (i == 9) {
                System.out.print("\t\t\t\t\t ");
                for (int x = 0; x < current_mobs.size(); x++) {
                    int len = String.valueOf(current_mobs.get(x)).length();
                    String first_letter = String.valueOf(String.valueOf(current_mobs.get(x)).charAt(0)).toLowerCase();
                    String lvl = String.valueOf(current_mobs.get(x)).split(":")[1];
                    System.out.print(first_letter + lvl + "\t\t");
                }
            }
            if (i == 10) {
                System.out.print("vertex".toUpperCase() + "\t\t\t  ");
                for (int x = 0; x < current_mobs.size(); x++) {
                    if (current_mobs.get(x) instanceof Human) {
                        Human human = (Human) current_mobs.get(x);
                        System.out.print("[" + human.getCoordinate()[1] + "," + human.getCoordinate()[0] + "]  ");
                    } else if (current_mobs.get(x) instanceof Goblin) {
                        Goblin goblin = (Goblin) current_mobs.get(x);
                        System.out.print("[" + goblin.getCoordinate()[1] + "," + goblin.getCoordinate()[0] + "]  ");
                    } else if (current_mobs.get(x) instanceof Zombie) {
                        Zombie zombie = (Zombie) current_mobs.get(x);
                        System.out.print("[" + zombie.getCoordinate()[1] + "," + zombie.getCoordinate()[0] + "]  ");
                    } else if (current_mobs.get(x) instanceof Martian) {
                        Martian martian = (Martian) current_mobs.get(x);
                        System.out.print("[" + martian.getCoordinate()[1] + "," + martian.getCoordinate()[0] + "]  ");
                    }

                }
            }
            if (i == 11) {
                System.out.print("horizontal min".toUpperCase() + "\t\t ");
                for (int x = 0; x < current_mobs.size(); x++) {
                    if (current_mobs.get(x) instanceof Human) {
                        Human human = (Human) current_mobs.get(x);
                        display_int = human.getCoordinate()[1] - dice[1];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Goblin) {
                        Goblin goblin = (Goblin) current_mobs.get(x);
                        display_int = goblin.getCoordinate()[1] - dice[1];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Zombie) {
                        Zombie zombie = (Zombie) current_mobs.get(x);
                        display_int = zombie.getCoordinate()[1] - dice[1];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Martian) {
                        Martian martian = (Martian) current_mobs.get(x);
                        display_int = martian.getCoordinate()[1] - dice[1];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    }
                }
            }
            if (i == 12) {
                System.out.print("horizontal max".toUpperCase() + "\t\t ");
                for (int x = 0; x < current_mobs.size(); x++) {
                    if (current_mobs.get(x) instanceof Human) {
                        Human human = (Human) current_mobs.get(x);
                        display_int = human.getCoordinate()[1] + dice[1];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Goblin) {
                        Goblin goblin = (Goblin) current_mobs.get(x);
                        display_int = goblin.getCoordinate()[1] + dice[1];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Zombie) {
                        Zombie zombie = (Zombie) current_mobs.get(x);
                        display_int = zombie.getCoordinate()[1] + dice[1];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Martian) {
                        Martian martian = (Martian) current_mobs.get(x);
                        display_int = martian.getCoordinate()[1] + dice[1];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    }
                }
            }
            if (i == 13) {
                System.out.print("vertical min".toUpperCase() + "\t\t ");
                for (int x = 0; x < current_mobs.size(); x++) {
                    if (current_mobs.get(x) instanceof Human) {
                        Human human = (Human) current_mobs.get(x);
                        display_int = human.getCoordinate()[0] - dice[0];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Goblin) {
                        Goblin goblin = (Goblin) current_mobs.get(x);
                        display_int = goblin.getCoordinate()[0] - dice[0];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Zombie) {
                        Zombie zombie = (Zombie) current_mobs.get(x);
                        display_int = zombie.getCoordinate()[0] - dice[0];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Martian) {
                        Martian martian = (Martian) current_mobs.get(x);
                        display_int = martian.getCoordinate()[0] - dice[0];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    }
                }
            }
            if (i == 14) {
                System.out.print("vertical max".toUpperCase() + "\t\t ");
                for (int x = 0; x < current_mobs.size(); x++) {
                    if (current_mobs.get(x) instanceof Human) {
                        Human human = (Human) current_mobs.get(x);
                        display_int = human.getCoordinate()[0] + dice[0];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Goblin) {
                        Goblin goblin = (Goblin) current_mobs.get(x);
                        display_int = goblin.getCoordinate()[0] + dice[0];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Zombie) {
                        Zombie zombie = (Zombie) current_mobs.get(x);
                        display_int = zombie.getCoordinate()[0] + dice[0];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    } else if (current_mobs.get(x) instanceof Martian) {
                        Martian martian = (Martian) current_mobs.get(x);
                        display_int = martian.getCoordinate()[0] + dice[0];
                        display_int = display_int > 21 ? 21 : display_int;
                        display_int = display_int < 2 ? 2 : display_int;
                        System.out.print(display_int + "\t\t");
                    }
                }
            }

            //MESSAGE BOX
            if (i == 16) {
                if (!message_box.isEmpty()) System.out.print(message_box.pop());
            }
            if (i == 17) {
                if (!message_box.isEmpty()) System.out.print(message_box.pop());
            }
            if (i == 18) {
                if (!message_box.isEmpty()) System.out.print(message_box.pop());
            }
            if (i == 19) {
                if (!message_box.isEmpty()) System.out.print(message_box.pop());
            }
            if (i == 20) {
                if (!message_box.isEmpty()) System.out.print(message_box.pop());
            }
            if (i == 21) {
                if (!message_box.isEmpty()) System.out.print(message_box.pop());
            }

            if (i == 22) System.out.print("Choose Player & Location");
        }
    }


}
