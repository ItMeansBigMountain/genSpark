package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class Goblin extends TimerTask {

    //GAME STATS ATTRIBUTES
    private int health = 10;

    private int speed = 10;

    private int attack = 10;
    private int strength = 10;
    private int defence = 10;
    private int combatLVL = 10;
    private int compassion = 10;
    private int intelligence = 10;
    private ArrayList<Object> inventory = new ArrayList<>();

    //GUI ATTRIBUTES
    private int posx;
    private int posy;
    private Human human;


    //GETTERS
    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public int getCombatLVL() {
        return this.combatLVL;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDefence() {
        return this.defence;
    }

    public int getIntelligence() {
        return this.intelligence;
    }

    public int getHealth() {
        return this.health;
    }

    public int getCompassion() {
        return this.compassion;
    }

    //SETTERS
    public void setCombatLVL(int combatLVL) {
        this.combatLVL = combatLVL;
    }

    public void setAttack(int attack) {
        this.strength = attack;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDefence(int defence) {
        this.strength = defence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setCompassion(int compassion) {
        this.compassion = compassion;
    }

    public void setPosx(int pos) {
        this.posx = pos;
    }

    public void setPosy(int pos) {
        this.posy = pos;
    }




    //CUSTOM DEV FUNCTIONS
    public Class whoIsTheParent() {
        return getClass().getSuperclass();
    }

    @Override
    public String toString() {
        return this.getClass().getName().split("[.]")[1] + ":" + this.getCombatLVL();
    }


    //CONSTRUCTORS
    public Goblin(Human human, Random random) {
        this.posx = 25 * new Random().nextInt(20);
        this.posy = 25 * new Random().nextInt(20);
        this.human = human;
        this.speed = random.ints(1, this.human.speed -1 ).findFirst().getAsInt();
        this.health = 10;
        this.combatLVL = (this.defence + this.attack + this.strength + this.intelligence + this.compassion) / 5;
        this.attack = random.ints(5, 7 + 50).findFirst().getAsInt();
        this.strength = random.ints(5, 7 + 50).findFirst().getAsInt();
        this.defence = random.ints(2, 3 + 50).findFirst().getAsInt();
        this.intelligence = random.ints(2, 3 + 50).findFirst().getAsInt();
        this.compassion = random.ints(2, 3 + 50).findFirst().getAsInt();
        this.inventory = new ArrayList<>();
    }





    public void follow_human() {
//        System.out.println(this.human.body.get(0).getPosx() + " " + this.human.body.get(0).getPosy() );
        int distance_X = this.human.body.get(0).getPosx() - this.getPosx();
        int distance_Y = this.human.body.get(0).getPosy() - this.getPosy();
        this.setPosx( this.getPosx() + distance_X / speed);
        this.setPosy( this.getPosy() + distance_Y / speed);
//        System.out.println(this.getPosx() + " " + this.getPosy());
    }









    //TIMER SCHEDULER on_RUN METHOD
    @Override
    public void run() {
            this.human.setTarget( new Goblin(this.human , new Random() )   );
    }


}