package game.racers.air;

import game.racers.Racer;
import utilities.EnumContainer.Color;

public class Helicopter extends Racer implements AerialRacer {
    static final String CLASS_NAME = "Helicopter";
    static final double DEFAULT_MAX_SPEED = 400;
    static final double DEFAULT_ACCELERATION = 50;
    static final Color DEFAULT_color = Color.BLUE;

    /**
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     */
    public Helicopter(String name, double maxSpeed, double acceleration, Color color){
        super(name, maxSpeed, acceleration, color);
    }
    public Helicopter(){
        this(CLASS_NAME+" #"+getSerial(),DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION, DEFAULT_color);
    }

    /**
     * @return
     */
    public String describeSpecific() {
        return null;
    }

    /**
     * @return
     */
    public String className(){
        return CLASS_NAME;
    }

    public Racer clone(){
        return new Helicopter(this.getName(),this.getMaxSpeed(),this.getAcceleration(),this.getColor());
    }
}
