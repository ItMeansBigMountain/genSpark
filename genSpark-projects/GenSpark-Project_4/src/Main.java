import HUMANOID_FAMILY.*;

import java.util.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        //USER INPUT INIT
        Scanner USER_INPUT = new Scanner(System.in);
        String choice = "";
        boolean valid = false;


        //INIT GAME SCREEN
        HashMap<Integer, String> game_key = new HashMap();
        game_key.put(-18, "18");                 // -18  "_Number"
        game_key.put(-17, "17");                 // -17  "_Number"
        game_key.put(-16, "16");                 // -16  "_Number"
        game_key.put(-15, "15");                 // -15  "_Number"
        game_key.put(-14, "14");                 // -14  "_Number"
        game_key.put(-13, "13");                 // -13  "_Number"
        game_key.put(-12, "12");                 // -12  "_Number"
        game_key.put(-11, "11");                 // -11  "_Number"
        game_key.put(-10, "10");                 // -10  "_Number"
        game_key.put(-9, "9");                   //  -1  "_Number"
        game_key.put(-8, "8");                   //  -1  "_Number"
        game_key.put(-7, "7");                   //  -1  "_Number"
        game_key.put(-6, "6");                   //  -1  "_Number"
        game_key.put(-5, "5");                   //  -1  "_Number"
        game_key.put(-4, "4");                   //  -1  "_Number"
        game_key.put(-3, "3");                   //  -1  "_Number"
        game_key.put(-2, "2");                   //  -1  "_Number"
        game_key.put(-1, "1");                   //  -1  "_Number"

        game_key.put(0, "\uD83C\uDF32");         //   0  "SCENERY TREE"
        game_key.put(1, "\uD83E\uDD37\u200D");   //   1  "HUMAN"
        game_key.put(2, "\uD83D\uDC7A");         //   2  "GOBLIN"
        game_key.put(3, "\uD83D\uDC7D");         //   3  "MARTIAN"
        game_key.put(4, "\uD83E\uDD22");         //   4  "ZOMBIE"

        game_key.put(5, "\uD83C\uDF82");         //   5  "CAKE"
        game_key.put(6, "\uD83C\uDF7A");         //   6  "BEER"
        game_key.put(7, "\uD83C\uDF0C");         //   7  "SOLAR POTATO"
        game_key.put(8, "\uD83E\uDDB4");         //   8  "BONE"

        game_key.put(9, "");                     //   9  "OPEN AREA"
        game_key.put(10, "◼");                    //   7  "White Tile"
        game_key.put(11, "⬜");                    //   8  "Black Tile"


        int[][] game_board = {
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



        //GAME RULES 2 DIMENSIONAL ARRAY
        System.out.println("\n\n\nGAME RULES");
        System.out.println("-\t Add input of coordinates you want to move ... \n");
        System.out.println("Each round you roll dice to see how far up/down & side/side you can move.\n ");
        System.out.println("use '.' full stop to separate FROM . TO\n");
        System.out.println("EXAMPLES: FROM . TO ");
        System.out.println("EXAMPLES: 6,21 . 5,21 ");


        //RANDOM NUMBERS INIT
        Random random = new Random();
        int[] dice;


        List<Object> player_one_mobs = new ArrayList<Object>();
        player_one_mobs.add(new Zombie(random, new int[]{21, 6}));
        player_one_mobs.add(new Goblin(random, new int[]{21, 7}));
        player_one_mobs.add(new Martian(random, new int[]{21, 8}));
        player_one_mobs.add(new Human(random, new int[]{21, 9}));
        player_one_mobs.add(new Human(random, new int[]{21, 10}));
        player_one_mobs.add(new Martian(random, new int[]{21, 11}));
        player_one_mobs.add(new Goblin(random, new int[]{21, 12}));
        player_one_mobs.add(new Zombie(random, new int[]{21, 13}));


        List<Object> player_two_mobs = new ArrayList<Object>();
        player_two_mobs.add(new Zombie(random, new int[]{2, 6}));
        player_two_mobs.add(new Goblin(random, new int[]{2, 7}));
        player_two_mobs.add(new Martian(random, new int[]{2, 8}));
        player_two_mobs.add(new Human(random, new int[]{2, 9}));
        player_two_mobs.add(new Human(random, new int[]{2, 10}));
        player_two_mobs.add(new Martian(random, new int[]{2, 11}));
        player_two_mobs.add(new Goblin(random, new int[]{2, 12}));
        player_two_mobs.add(new Zombie(random, new int[]{2, 13}));


        //RANDOM POWERUP LOCATIONS
        int _x;
        int _y;
        for (int p = 0; p < 4; p++) {
            _x = random.ints(1, 18).findFirst().getAsInt();
            _y = random.ints(10, 12).findFirst().getAsInt();
            game_board[_y][_x] = random.ints(5, 8).findFirst().getAsInt();
        }


        //CONSOLE APP
        Stack<String> message_box = new Stack<>();
        ArrayList<Object> validity;
        boolean player1_turn = false;
        boolean running = true;
        int round = 1;
        int display_int = 0;
        GameFunctionality g = new GameFunctionality();
        while (running) {

            //SWITCH PLAYERS
            player1_turn = !player1_turn;
            List<Object> current_mobs = (player1_turn ? player_one_mobs : player_two_mobs);
            List<Object> enemy_mobs = (player1_turn ? player_two_mobs : player_one_mobs);

            //DICE ROLL
            //dice = new int[]{random.ints(1, 6).findFirst().getAsInt(), random.ints(1, 6).findAny().getAsInt()};
            dice = new int[]{99,99};
            message_box.push("DICE ROLL VERTICAL: " + dice[0]);
            message_box.push("DICE ROLL HORIZONTAL: " + dice[1]);


            // DISPLAY GAME
            g.draw_game(game_board, dice, display_int, round, player1_turn, current_mobs, game_key,message_box);



            // USER INPUTS VALIDATION
            int[][] user_coordinates = null;
            valid = false;
            while (!valid) {
                System.out.print(">\t");
                choice = USER_INPUT.nextLine();
                user_coordinates = g.validateCharacterSelect(choice, dice);

                if (user_coordinates != null) {
                    validity = g.coordinate(player1_turn, message_box, user_coordinates, game_board, current_mobs, enemy_mobs);
                    valid = (boolean) validity.get(0);
                    game_board = (int[][]) validity.get(1);
                    current_mobs = (List) validity.get(2);
                    enemy_mobs = (List) validity.get(3);
                    message_box = (Stack<String>) validity.get(4);
                } else {
                    valid = false;
                }
            }


            //REASSIGN TROOPS STATUS
            if (player1_turn) {
                player_one_mobs = current_mobs;
                player_two_mobs = enemy_mobs;
            } else {
                player_one_mobs = enemy_mobs;
                player_two_mobs = current_mobs;
            }

            round++;
        }


    }
}
