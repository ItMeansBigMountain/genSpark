package HUMANOID_FAMILY;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Zombie extends Humanoid {
    // STATIC METHODS CAN BE REFERRED ANYWHERE ex: String.valueOf()
    // INSTANCE METHODS ARE REFERRED TO PER INSTANCE ex: "abc".length()

    private int combatLVL = 8;
    private int strength = 8;
    private int attack = 8;
    private int defence = 8;
    private int intelligence = 8;
    private int health = 8;
    private int compassion = 8;

    private int[] coordinates = new int[2];


    private ArrayList<Object> inventory = new ArrayList<>();


    // Default dispatched __INIT__() with no arguments
    public Zombie() {

    }

    public Zombie(Random random, int[] coordinates) {
        this.health = 10;
        this.combatLVL = (this.defence + this.attack + this.strength + this.intelligence + this.compassion) / 5;
        this.attack = random.ints(5, 10 + 50).findFirst().getAsInt();
        this.strength = random.ints(2, 10 + 50).findFirst().getAsInt();
        this.defence = random.ints(2, 3 + 50).findFirst().getAsInt();
        this.intelligence = random.ints(2, 3 + 50).findFirst().getAsInt();
        this.compassion = random.ints(2, 3 + 50).findFirst().getAsInt();
        this.coordinates = coordinates;
        this.inventory = new ArrayList<>();
    }


    // overloaded dispatched __INIT__() with 5 (int) arguments
    public Zombie(int attack, int strength, int defence, int intelligence, int health, int compassion, int[] coordinates) {
        this.attack = attack;
        this.strength = strength;
        this.defence = defence;
        this.combatLVL = (this.defence + this.attack + this.strength + this.intelligence + this.compassion) / 5;
        this.intelligence = intelligence;
        this.health = health;
        this.compassion = compassion;
        this.coordinates = coordinates;
        this.inventory = new ArrayList<>();

    }

    //GETTERS
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

    public int[] getCoordinate() {
        return this.coordinates;
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

    public void setCoordinate(int[] new_location) {
        this.coordinates = new_location;
    }


    public void intended_powerup() {
        this.attack += 12;
        this.strength += 12;
        this.defence += 12;
        this.combatLVL += 12;
        this.intelligence += 12;
        this.health += 12;
        this.compassion += 12;
    }

    public void un_intended_powerup() {
        this.attack += 5;
        this.strength += 5;
        this.defence += 5;
        this.combatLVL += 5;
        this.intelligence += 5;
        this.health += 5;
        this.compassion += 5;
    }


    public Class whoIsTheParent() {
        return getClass().getSuperclass();
    }


    @Override
    public String toString() {
        return this.getClass().getName().split("[.]")[1] + ":" + this.getCombatLVL();
    }


    //ATTACK
    public String attack(Object other) {


        //CALL SETTERS & GETTERS OF INTERACTED OBJECT
        HashMap<String, Method> setters;
        HashMap<String, Method> getters;

        HashMap<String, HashMap<String, Method>> interactive_functions = Humanoid.setters_and_getters(other);

        setters = interactive_functions.get("setters");
        getters = interactive_functions.get("getters");


        // SETTER & GETTER ATTACK ALGO
        try {
            //  METHODS TO INVOKE UPON OTHER INTRACTABLE
            Method action = Humanoid.fetchMethod(setters, "set_health(int)");
            Method get_health = Humanoid.fetchMethod(getters, "get_health()");

            //DAMAGE PLAYER
            int current_hp = (int) get_health.invoke(other) - this.getStrength();
            action.invoke(other, current_hp);


            if ((int) get_health.invoke(other) >= 4) {
                return "The zombie bites the human for " + String.valueOf(this.getStrength()) + " damage, the human survives but will be turning soon...";
            } else {
                return "The zombie bites the human for " + String.valueOf(this.getStrength()) + " damage, the human has died...";
            }


        } catch (Exception e) {
            System.out.println(e);
            //NULL EXCEPTION IS WHEN OBJECT ISN'T INTRACTABLE
        }
        return null;
    }


}
