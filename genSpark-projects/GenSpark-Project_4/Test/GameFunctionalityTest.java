import HUMANOID_FAMILY.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameFunctionalityTest {

    GameFunctionality g;

    Random random = new Random();

    String choice;

    int[] dice;

    Object selected_mob = new Human(random, new int[]{9, 21});

    List<Object> enemy_mobs;

    HashMap<String, HashMap<String, Method>> selected_mob_functions;

    Stack<String> messages;
    int[] to;


    int[][] coordinates;

    List current_mobs;


    boolean player1 = true;

    @BeforeAll
    static void DISCLAIMER() {
        System.out.println("************** DISCLAIMER ***************");
        System.out.println("\nFUNCTION TESTING ASSUMES ITS PLAYER 1'S TURN   \n`player1 = false` FOR PLAYER 2 \n");
        System.out.println("*****************************************");
    }


    @BeforeEach
    void init_testing_object() {
        g = new GameFunctionality();
        messages = new Stack<>();
    }


    @AfterEach
    void tearDown() {

    }


    //USER INPUT VALIDATION
    @Test
    void validateCharacterSelect_valid() {
        //returns 2D array with [FROM] & [TO] coordinates (y,x)

        //INIT
        choice = "6,21 . 6,2";
        dice = new int[]{99, 99};

        //TRUE OUTPUTS
        System.out.println("\nTESTING VALID INPUT --->  " + Arrays.toString(dice) + "\t" + choice);
        int[][] array_expected = new int[][]{{21, 6}, {2, 6}};
        int[][] array_actual = g.validateCharacterSelect(choice, dice);

        //CONVERT TO LIST FOR VALUE COMPARISON WITHOUT MEMORY LOCATION __REPR__
        List<List<String>> list_expected_temp = new ArrayList<List<String>>();
        List<List<String>> list_actual_temp = new ArrayList<List<String>>();
        for (int x = 0; x < array_actual.length; x++) {
            list_expected_temp.add(Collections.singletonList(Arrays.toString(array_expected[x])));
            list_actual_temp.add(Collections.singletonList(Arrays.toString(array_actual[x])));
        }

        //FINAL
        assertEquals(list_expected_temp, list_actual_temp);

    }

    @Test
    void validateCharacterSelect_invalid_input__onlyOne_Coordinate() {
        //returns 2D array with [FROM] & [TO] coordinates (y,x)

        //INIT
        choice = "6,21";
        dice = new int[]{99, 99};

        //TRUE OUTPUTS
        System.out.println("\nTESTING INVALID INPUT: SUBMITTING ONLY ONE COORDINATE --->  " + Arrays.toString(dice) + "\t" + choice);
        int[][] array_expected = null;
        int[][] array_actual = g.validateCharacterSelect(choice, dice);

        //FINAL
        assertEquals(array_expected, array_actual);

    }

    @Test
    void validateCharacterSelect_invalid_input__forgotComma() {
        //returns 2D array with [FROM] & [TO] coordinates (y,x)

        //INIT
        choice = "6,21 . 6.1";
        dice = new int[]{99, 99};

        //TRUE OUTPUTS
        System.out.println("\nTESTING INVALID INPUT: FORGOT COMMA --->  " + Arrays.toString(dice) + "\t" + choice);
        int[][] array_expected = null;
        int[][] array_actual = g.validateCharacterSelect(choice, dice);

        //FINAL
        assertEquals(array_expected, array_actual);

    }

    @Test
    void validateCharacterSelect_invalid_input__diceOutOfBounds() {
        //returns 2D array with [FROM] & [TO] coordinates (y,x)

        //INIT
        choice = "6,21 . 6,15";
        dice = new int[]{5, 5};

        //TRUE OUTPUTS
        System.out.println("\nTESTING INVALID INPUT: OUT OF DICE ROLL BOUNDS --->  " + Arrays.toString(dice) + "\t" + choice);
        int[][] array_expected = null;
        int[][] array_actual = g.validateCharacterSelect(choice, dice);

        //FINAL
        assertEquals(array_expected, array_actual);

    }


    // FIGHTING OTHER PLAYERS SIMULATION
    @Test
    void fight_valid_attack() {
        //INIT
        selected_mob_functions = Humanoid.setters_and_getters(selected_mob);

        enemy_mobs = new ArrayList<Object>();
        enemy_mobs.add(new Zombie(random, new int[]{2, 6}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 7})); //fighting this guy
        enemy_mobs.add(new Martian(random, new int[]{2, 8}));
        enemy_mobs.add(new Human(random, new int[]{2, 9}));
        enemy_mobs.add(new Human(random, new int[]{2, 10}));
        enemy_mobs.add(new Martian(random, new int[]{2, 11}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 12}));
        enemy_mobs.add(new Zombie(random, new int[]{2, 13}));

        to = new int[]{2, 7};


        System.out.println("\nTESTING VALID FIGHT: --->  " + selected_mob + "\tvs.\t" + enemy_mobs.get(1));
        //TESTING IF LENGTH OF OUTPUT ARRAY RETURNS VALID AMOUNT OF VARIABLES
        //Strings representative of variables used within the code since outcomes are random.
        ArrayList<Object> list_expected = new ArrayList<>();
        list_expected.add("messages");
        list_expected.add("winner");
        list_expected.add("team.charAt(0)");
        list_expected.add("interactive_functions");
        list_expected.add("mob_index");


        List<Object> list_actual = GameFunctionality.fight(selected_mob_functions, messages, enemy_mobs, selected_mob, to, player1);

        //FINAL
        assertEquals(list_expected.size(), list_actual.size());

    }

    @Test
    void fight_invalid_enemyNotFound() {
        //INIT
        selected_mob_functions = Humanoid.setters_and_getters(selected_mob);

        enemy_mobs = new ArrayList<Object>();
        enemy_mobs.add(new Zombie(random, new int[]{2, 6}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 7}));
        enemy_mobs.add(new Martian(random, new int[]{2, 8}));
        enemy_mobs.add(new Human(random, new int[]{2, 9}));
        enemy_mobs.add(new Human(random, new int[]{2, 10}));
        enemy_mobs.add(new Martian(random, new int[]{2, 11}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 12}));
        enemy_mobs.add(new Zombie(random, new int[]{2, 13}));

        to = new int[]{99, 99};


        System.out.println("\nTESTING VALID FIGHT: --->  " + selected_mob + "\tvs.\t" + enemy_mobs.get(1));
        System.out.println("validations prior to  GameFunctionality.fight() will ensure that there's an object on coordinate");
        System.out.println("thus the enemy object must be oneself or a team mate");

        //TESTING
        ArrayList<Object> list_expected = new ArrayList<>();
        list_expected.add(messages);
        list_expected.add(null);

        List<Object> list_actual = GameFunctionality.fight(selected_mob_functions, messages, enemy_mobs, selected_mob, to, player1);


        //FINAL
        assertEquals(list_expected, list_actual);

    }


    @Test
    void coordinate_valid_moveToWhiteSpace() {
        //INIT
        choice = "6,21 . 6,20";
        dice = new int[]{99, 99};
        int[][] user_coordinates = g.validateCharacterSelect(choice, dice);


        int[][] game_board_BEFORE = {
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
        };


        int[][] game_board_AFTER = {
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 4, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
        };


        current_mobs = new ArrayList<Object>();
        current_mobs.add(new Zombie(random, new int[]{21, 6}));
        current_mobs.add(new Goblin(random, new int[]{21, 7}));
        current_mobs.add(new Martian(random, new int[]{21, 8}));
        current_mobs.add(new Human(random, new int[]{21, 9}));
        current_mobs.add(new Human(random, new int[]{21, 10}));
        current_mobs.add(new Martian(random, new int[]{21, 11}));
        current_mobs.add(new Goblin(random, new int[]{21, 12}));
        current_mobs.add(new Zombie(random, new int[]{21, 13}));

        enemy_mobs = new ArrayList<Object>();
        enemy_mobs.add(new Zombie(random, new int[]{2, 6}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 7}));
        enemy_mobs.add(new Martian(random, new int[]{2, 8}));
        enemy_mobs.add(new Human(random, new int[]{2, 9}));
        enemy_mobs.add(new Human(random, new int[]{2, 10}));
        enemy_mobs.add(new Martian(random, new int[]{2, 11}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 12}));
        enemy_mobs.add(new Zombie(random, new int[]{2, 13}));


        //TESTING
        ArrayList<Object> list_expected = new ArrayList<>();
        list_expected.add(true);
        list_expected.add(game_board_AFTER);
        list_expected.add(current_mobs);
        list_expected.add(enemy_mobs);
        list_expected.add(messages);


        List<Object> list_actual = g.coordinate(player1, messages, user_coordinates, game_board_BEFORE, current_mobs, enemy_mobs);


        //CHANGING GAME BOARD INTO STRING FOR COMPARISON
        List<List<String>> list_expected_temp = new ArrayList<List<String>>();
        List<List<String>> list_actual_temp = new ArrayList<List<String>>();
        int[][]  actual_gameBoard =  (int[][]) list_actual.get(1);
        for (int x = 0; x < actual_gameBoard.length ; x++) {
            list_expected_temp.add(Collections.singletonList(Arrays.toString(game_board_AFTER[x])));
            list_actual_temp.add(Collections.singletonList(Arrays.toString(actual_gameBoard[x])));
        }



        list_expected.set(1,  list_expected_temp  );
        list_actual.set(1,  list_actual_temp );


        //FINAL
        System.out.println("\nTESTING VALID PIECE MOVE TO WHITESPACE (one space up): --->  " +   choice  );
        assertEquals(list_expected, list_actual);

    }


    @Test
    void coordinate_invalid_moveOutOfBounds() {
        //INIT
        choice = "6,21 . 6,99";
        dice = new int[]{99, 99};
        int[][] user_coordinates = g.validateCharacterSelect(choice, dice);


        int[][] game_board_BEFORE = {
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
        };


        int[][] game_board_AFTER = {
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
        };


        current_mobs = new ArrayList<Object>();
        current_mobs.add(new Zombie(random, new int[]{21, 6}));
        current_mobs.add(new Goblin(random, new int[]{21, 7}));
        current_mobs.add(new Martian(random, new int[]{21, 8}));
        current_mobs.add(new Human(random, new int[]{21, 9}));
        current_mobs.add(new Human(random, new int[]{21, 10}));
        current_mobs.add(new Martian(random, new int[]{21, 11}));
        current_mobs.add(new Goblin(random, new int[]{21, 12}));
        current_mobs.add(new Zombie(random, new int[]{21, 13}));

        enemy_mobs = new ArrayList<Object>();
        enemy_mobs.add(new Zombie(random, new int[]{2, 6}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 7}));
        enemy_mobs.add(new Martian(random, new int[]{2, 8}));
        enemy_mobs.add(new Human(random, new int[]{2, 9}));
        enemy_mobs.add(new Human(random, new int[]{2, 10}));
        enemy_mobs.add(new Martian(random, new int[]{2, 11}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 12}));
        enemy_mobs.add(new Zombie(random, new int[]{2, 13}));


        //TESTING
        ArrayList<Object> list_expected = new ArrayList<>();
        list_expected.add(false);
        list_expected.add(game_board_AFTER);
        list_expected.add(current_mobs);
        list_expected.add(enemy_mobs);
        list_expected.add(messages);



        List<Object> list_actual = g.coordinate(player1, messages, user_coordinates, game_board_BEFORE, current_mobs, enemy_mobs);


        //CHANGING GAME BOARD INTO STRING FOR COMPARISON
        List<List<String>> list_expected_temp = new ArrayList<List<String>>();
        List<List<String>> list_actual_temp = new ArrayList<List<String>>();
        int[][]  actual_gameBoard =  (int[][]) list_actual.get(1);
        for (int x = 0; x < actual_gameBoard.length ; x++) {
            list_expected_temp.add(Collections.singletonList(Arrays.toString(game_board_AFTER[x])));
            list_actual_temp.add(Collections.singletonList(Arrays.toString(actual_gameBoard[x])));
        }



        list_expected.set(1,  list_expected_temp  );
        list_actual.set(1,  list_actual_temp );


        //FINAL
        System.out.println("\nTESTING INVALID PIECE MOVE TO OUT OF BOUNDS: --->  " +   choice  );
        assertEquals(list_expected, list_actual);

    }




    @Test
    void coordinate_invalid_movingPlayer_NotFound() {
        //INIT
        choice = "2,21 . 6,20";
        dice = new int[]{99, 99};
        int[][] user_coordinates = g.validateCharacterSelect(choice, dice);


        int[][] game_board_BEFORE = {
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
        };


        int[][] game_board_AFTER = {
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
        };


        current_mobs = new ArrayList<Object>();
        current_mobs.add(new Zombie(random, new int[]{21, 6}));
        current_mobs.add(new Goblin(random, new int[]{21, 7}));
        current_mobs.add(new Martian(random, new int[]{21, 8}));
        current_mobs.add(new Human(random, new int[]{21, 9}));
        current_mobs.add(new Human(random, new int[]{21, 10}));
        current_mobs.add(new Martian(random, new int[]{21, 11}));
        current_mobs.add(new Goblin(random, new int[]{21, 12}));
        current_mobs.add(new Zombie(random, new int[]{21, 13}));

        enemy_mobs = new ArrayList<Object>();
        enemy_mobs.add(new Zombie(random, new int[]{2, 6}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 7}));
        enemy_mobs.add(new Martian(random, new int[]{2, 8}));
        enemy_mobs.add(new Human(random, new int[]{2, 9}));
        enemy_mobs.add(new Human(random, new int[]{2, 10}));
        enemy_mobs.add(new Martian(random, new int[]{2, 11}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 12}));
        enemy_mobs.add(new Zombie(random, new int[]{2, 13}));


        //TESTING
        ArrayList<Object> list_expected = new ArrayList<>();
        list_expected.add(false);
        list_expected.add(game_board_AFTER);
        list_expected.add(current_mobs);
        list_expected.add(enemy_mobs);
        list_expected.add(messages);



        List<Object> list_actual = g.coordinate(player1, messages, user_coordinates, game_board_BEFORE, current_mobs, enemy_mobs);


        //CHANGING GAME BOARD INTO STRING FOR COMPARISON
        List<List<String>> list_expected_temp = new ArrayList<List<String>>();
        List<List<String>> list_actual_temp = new ArrayList<List<String>>();
        int[][]  actual_gameBoard =  (int[][]) list_actual.get(1);
        for (int x = 0; x < actual_gameBoard.length ; x++) {
            list_expected_temp.add(Collections.singletonList(Arrays.toString(game_board_AFTER[x])));
            list_actual_temp.add(Collections.singletonList(Arrays.toString(actual_gameBoard[x])));
        }



        list_expected.set(1,  list_expected_temp  );
        list_actual.set(1,  list_actual_temp );


        //FINAL
        System.out.println("\nTESTING INVALID PIECE MOVE FROM UNKNOWN PLAYER COORDINATE: --->  " +   choice  );
        assertEquals(list_expected, list_actual);

    }






    @Test
    void coordinate_invalid_FRIENDLY_FIRE() {
        //INIT
        choice = "6,21 . 8,21";
        dice = new int[]{99, 99};
        int[][] user_coordinates = g.validateCharacterSelect(choice, dice);


        int[][] game_board_BEFORE = {
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
        };


        int[][] game_board_AFTER = {
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 0},
                {0, 9, 9, 9, 9, 9, 4, 2, 3, 1, 1, 3, 2, 4, 9, 9, 9, 9, 9, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, -1, -2, -3, -4, -5, -6, -7, -8, -9, -10, -11, -12, -13, -14, -15, -16, -17, -18, 0},
        };


        current_mobs = new ArrayList<Object>();
        current_mobs.add(new Zombie(random, new int[]{21, 6}));
        current_mobs.add(new Goblin(random, new int[]{21, 7}));
        current_mobs.add(new Martian(random, new int[]{21, 8}));
        current_mobs.add(new Human(random, new int[]{21, 9}));
        current_mobs.add(new Human(random, new int[]{21, 10}));
        current_mobs.add(new Martian(random, new int[]{21, 11}));
        current_mobs.add(new Goblin(random, new int[]{21, 12}));
        current_mobs.add(new Zombie(random, new int[]{21, 13}));

        enemy_mobs = new ArrayList<Object>();
        enemy_mobs.add(new Zombie(random, new int[]{2, 6}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 7}));
        enemy_mobs.add(new Martian(random, new int[]{2, 8}));
        enemy_mobs.add(new Human(random, new int[]{2, 9}));
        enemy_mobs.add(new Human(random, new int[]{2, 10}));
        enemy_mobs.add(new Martian(random, new int[]{2, 11}));
        enemy_mobs.add(new Goblin(random, new int[]{2, 12}));
        enemy_mobs.add(new Zombie(random, new int[]{2, 13}));


        //TESTING
        ArrayList<Object> list_expected = new ArrayList<>();
        list_expected.add(false);
        list_expected.add(game_board_AFTER);
        list_expected.add(current_mobs);
        list_expected.add(enemy_mobs);
        list_expected.add(messages);



        List<Object> list_actual = g.coordinate(player1, messages, user_coordinates, game_board_BEFORE, current_mobs, enemy_mobs);


        //CHANGING GAME BOARD INTO STRING FOR COMPARISON
        List<List<String>> list_expected_temp = new ArrayList<List<String>>();
        List<List<String>> list_actual_temp = new ArrayList<List<String>>();
        int[][]  actual_gameBoard =  (int[][]) list_actual.get(1);
        for (int x = 0; x < actual_gameBoard.length ; x++) {
            list_expected_temp.add(Collections.singletonList(Arrays.toString(game_board_AFTER[x])));
            list_actual_temp.add(Collections.singletonList(Arrays.toString(actual_gameBoard[x])));
        }



        list_expected.set(1,  list_expected_temp  );
        list_actual.set(1,  list_actual_temp );


        //FINAL
        System.out.println("\nTESTING INVALID PIECE ATTACKS TEAM MATE OR SELF: --->  " +   choice  );
        assertEquals(list_expected, list_actual);

    }








}