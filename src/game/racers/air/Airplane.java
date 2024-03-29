package game.racers.air;

import game.racers.Racer;
import game.racers.Wheeled;
import game.racers.land.Horse;
import utilities.EnumContainer.Color;

public class Airplane extends Racer implements AerialRacer {
    static final String CLASS_NAME = "Airplane";
    static final int DEFAULT_WHEELS = 3;
    static final double DEFAULT_MAX_SPEED = 885;
    static final double DEFAULT_ACCELERATION = 100;
    static final Color color = Color.BLACK;
    private Wheeled wheeled;

    /**
     * @return
     */
    public Wheeled getWheeled() {
        return wheeled;
    }

    /**
     * @param wheeled
     * @return
     */
    public boolean setWheeled(Wheeled wheeled) {
        this.wheeled = wheeled;
        return true;
    }

    /**
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @param numOfWheels
     */
    public Airplane(String name, double maxSpeed, double acceleration, Color color, int
            numOfWheels){
        super(name,maxSpeed,acceleration,color);
        this.wheeled = new Wheeled(numOfWheels);
    }
    public Airplane(){
        this(CLASS_NAME+" #"+getSerial(),DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,Color.BLACK,DEFAULT_WHEELS);
    }

    /**
     * @return
     */
    public String describeSpecific(){
        return wheeled.describeSpecific();
    }

    /**
     * @return
     */
    public String className(){
        return CLASS_NAME;
    }

    public Racer clone(){
        return new Airplane(this.getName(),this.getMaxSpeed(),this.getAcceleration(),this.getColor(),wheeled.getNumOfWheels());
    }
}
