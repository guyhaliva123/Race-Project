package game.racers.land;

import game.racers.Racer;
import game.racers.Wheeled;
import game.racers.naval.SpeedBoat;
import utilities.EnumContainer;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.BicycleType;

public class Bicycle extends Racer implements LandRacer {
    static final String CLASS_NAME = "Bicycle";
    static final int DEFAULT_WHEELS = 2;
    static final double DEFAULT_MAX_SPEED = 270;
    static final double DEFAULT_ACCELERATION = 10;
    static final Color DEFAULT_color = Color.GREEN;
    private BicycleType type = BicycleType.MOUNTAIN;
    private Wheeled wheeled;

    /**
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @param numOfWheels
     */
    public Bicycle(String name, double maxSpeed, double acceleration, Color color, int
            numOfWheels){
        super(name, maxSpeed, acceleration, color);
        this.wheeled = new Wheeled(numOfWheels);
    }
    public Bicycle(){
        this(CLASS_NAME+" #"+getSerial(),DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color,DEFAULT_WHEELS);
    }

    /**
     * @return
     */
    public BicycleType getType() {
        return type;
    }

    /**
     * @param type
     * @return
     */
    public boolean setType(BicycleType type) {
        this.type = type;
        return true;
    }

    /**
     * @return
     */
    public String describeSpecific() {
        return wheeled.describeSpecific()+" Bicycle Type:"+this.type;
    }
    public String className(){
        return CLASS_NAME;
    }

    public Racer clone(){
        return new Bicycle(this.getName(),this.getMaxSpeed(),this.getAcceleration(),this.getColor(),wheeled.getNumOfWheels());
    }
}
