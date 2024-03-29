package game.racers;
import utilities.Fate;
import utilities.Mishap;
import game.arenas.Arena;
import utilities.EnumContainer.Color;
import utilities.EnumContainer;
import utilities.Point;
import utilities.Observable;
import utilities.designPatterns.*;

import java.util.Random;

public abstract class Racer extends Observable implements Runnable,Cloneable  {
    private static int serial = 1;
    private int serial_number;
    private String name;
    private Arena arena;
    private Point currentLocation;
    private Point finish;
    private double maxSpeed;
    private double acceleration;
    private double currentSpeed;
    private double failureProbability;
    private Color color;
    private Mishap mishap;

    //HW3 variables
    private racerState state;
    private double breakDownLocation;
    private Random rnd = new Random();


    /**
     * @return
     */
    public static int getSerial(){return serial;}

    /**q        Q
     * @return
     */
    public int getSerial_number() {
        return serial_number;
    }

    /**
     * @param serial_number
     * @return
     */
    public boolean setSerial_number(int serial_number) {
        this.serial_number = serial_number;
        return true;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     * @return
     */
    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    /**
     * @return
     */
    public Arena getArena() {
        return arena;
    }


    /**
     * @param arena
     * @return
     */
    public boolean setArena(Arena arena) {
        this.arena = arena;
        return true;
    }

    /**
     * @return
     */
    public Point getCurrentLocation() {
        return currentLocation;
    }

    /**
     * @param currentLocation
     * @return
     */
    public boolean setCurrentLocation(Point currentLocation) {
        this.currentLocation = currentLocation;
        return true;
    }

    /**
     * @return
     */
    public Point getFinish() {
        return finish;
    }

    /**
     * @param finish
     * @return
     */
    public boolean setFinish(Point finish) {
        this.finish = finish;
        return true;
    }

    /**
     * @return
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * @param maxSpeed
     * @return
     */
    public boolean setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
        return true;
    }

    /**
     * @return
     */
    public double getAcceleration() {
        return acceleration;
    }

    /**
     * @param acceleration
     * @return
     */
    public boolean setAcceleration(double acceleration) {
        this.acceleration = acceleration;
        return true;
    }

    /**
     * @return
     */
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * @param currentSpeed
     * @return
     */
    public boolean setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
        return true;
    }

    /**
     * @return
     */
    public double getFailureProbability() {
        return failureProbability;
    }

    /**
     * @param failureProbability
     * @return
     */
    public boolean setFailureProbability(double failureProbability) {
        this.failureProbability = failureProbability;
        return true;
    }

    /**
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color
     * @return
     */
    public boolean setColor(Color color) {
        this.color = color;
        return true;
    }

    /**
     * @return
     */
    public Mishap getMishap() {
        return this.mishap;
    }

    /**
     * @param mishap
     * @return
     */
    public boolean setMishap(Mishap mishap) {
            this.mishap=new Mishap(mishap.isFixable(), mishap.getTurnsToFix(), mishap.getReductionFactor());
            return true;
    }

    public racerState getState() {
        return state;
    }

    public void setState(racerState state) {
        this.state = state;
    }

    public double getBreakDownLocation() {
        return breakDownLocation;
    }

    public void setBreakDownLocation(double breakDownLocation) {
        this.breakDownLocation = breakDownLocation;
    }

    /**
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     */
//Racer builder.
    public Racer(String name, double maxSpeed , double acceleration, EnumContainer.Color color){
        setSerial_number(getSerial());
        this.setName(name);
        this.setMaxSpeed(maxSpeed);
        this.setAcceleration(acceleration);
        this.setColor(color);
        this.setState(new activeState());
        this.serial++;
    }

    /**
     * @param arena
     * @param start
     * @param finish
     */
    public void initRace(Arena arena, Point start, Point finish){
        this.setArena(arena);
        this.setCurrentLocation(start);
        this.setFinish(finish);
    }

    /**
     * @param friction
     * @return
     */
    //A function that is responsible for the user's movement,
    // among other things, checks for faults
    // and calculates and updates the user's speed and location.
    public Point move(double friction){

        double reductionFactor = 1;
        Point current =  null;

        if (this.getState().getRacerState() == "Disqualified")
            return currentLocation;
        else{
            if (mishap==null){
                if(Fate.breakDown()){
                    setMishap(Fate.generateMishap());
                    System.out.println(this.name+" Has a new mishap!"+mishap.toString());
                }
            }

            if (mishap != null){
                if(mishap.isFixable()){
                    reductionFactor = this.mishap.getReductionFactor();
                    System.out.println("reductionFactor "+ reductionFactor);
                    this.mishap.nextTurn();
                    //Broken
                    if (this.getState().getRacerState() != "Broken") {
                        setState(new brokenState());
                        notifyObservers();
                    }
                } else{
                    //Disqualified
                    reductionFactor = this.mishap.getReductionFactor();
                    if (this.getState().getRacerState() != "Disqualified") {
                        setState(new disqualifiedState());
                        notifyObservers();
                    }
                    return currentLocation;
                }
            }

            if(currentSpeed<this.maxSpeed){
                setCurrentSpeed(this.currentSpeed += getAcceleration() * reductionFactor * friction);
            }
            if(getCurrentSpeed()>getMaxSpeed()){
                this.setCurrentSpeed(this.getMaxSpeed());
            }

            if (this.mishap != null && this.mishap.isFixable() && this.mishap.getTurnsToFix() == 0){
                //Active
                this.mishap = null;
                setState(new activeState());
                notifyObservers();
            }

            if (this.currentLocation.getX()+this.currentSpeed > arena.getLength()) {
                current = new Point(arena.getLength(), this.getCurrentLocation().getY());
            }else {
                current = new Point(this.currentLocation.getX() + this.currentSpeed, this.getCurrentLocation().getY());
            }
        }

        setCurrentLocation(current);

        return this.currentLocation;
    }

    public abstract String describeSpecific();
    public abstract String className();

    /**
     * @return
     */
    //A function that describes all the user's details including his unique details.
    public String describeRacer(){
        if (describeSpecific()!=null){
            return "["+className()+"]"+" name: "+this.getName()+", serial number: "+this.getSerial_number()+
                    ", max speed: "+this.getMaxSpeed()+", acceleration: " +this.getAcceleration()+
                    " , color: "+this.getColor()+" , "+describeSpecific();
        }
        return "["+className()+"]"+" name: "+this.getName()+", serial number: "+this.getSerial_number()+
                ", max speed: "+this.getMaxSpeed()+", acceleration: " +this.getAcceleration()+
                " , color: "+this.getColor();
    }

    /**
     *
     */
    public void introduce(){
        System.out.println(describeRacer());
    }

    /**
     * @return
     */
    public boolean hasMishap(){
        if (getMishap()==null || mishap.isFixable()==true && mishap.getTurnsToFix()==0)
            return true;
        else return false;
    }


//This method contains the code that will be executed when the thread is started.
    public void run(){
        while (this.currentLocation.getX()<this.getArena().getLength()) {
            try {
                if (this.getState().getRacerState() != "Disqualified") {
                    System.out.println("run() -> " + this.getName());
                    this.move(this.getArena().getFRICTION());
                    Thread.sleep(100);
                }
            }
            catch (InterruptedException Ex) {
                Ex.printStackTrace();
            }
        }
        if (this.getState().getRacerState().equals("Disqualified"))
            this.unregisterObserver(arena);
        if(this.getState().getRacerState().equals("Broken")) {
            this.getArena().getActiveRacers().add(this);
            this.getArena().getBrokenRacers().remove(this);
        }
        setState(new completedState());
        notifyObservers();
        this.unregisterObserver(arena);

    }

    public abstract Racer clone();

    public void upgrade(Color color){
        this.setColor(color);
    }

}
