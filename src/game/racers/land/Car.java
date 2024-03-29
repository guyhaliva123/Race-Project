package game.racers.land;

import game.racers.Racer;
import game.racers.Wheeled;
import utilities.EnumContainer.Color;
import utilities.EnumContainer.Engine;
public class Car extends Racer implements LandRacer{
    static final String CLASS_NAME = "Car";
    static final int DEFAULT_WHEELS = 4;
    static final double DEFAULT_MAX_SPEED = 400;
    static final double DEFAULT_ACCELERATION = 20;
    static final Color DEFAULT_color = Color.RED;
    private Wheeled wheeled;
    private Engine engine = Engine.FOURSTROKE;

    /**
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @param numOfWheels
     */
    public Car(String name, double maxSpeed, double acceleration, Color color, int
            numOfWheels){
        super(name, maxSpeed, acceleration, color);
        this.wheeled = new Wheeled(numOfWheels);
    }
    public Car(){
        this(CLASS_NAME+" #"+getSerial(),DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color,DEFAULT_WHEELS);
    }

    /**
     * @return
     */
    public Engine getEngine() {
        return engine;
    }

    /**
     * @param engine
     * @return
     */
    public boolean setEngine(Engine engine) {
        this.engine = engine;
        return true;
    }

    /**
     * @return
     */
    public String describeSpecific() {
        return wheeled.describeSpecific()+" Engine Type:"+this.getEngine();
    }

    /**
     * @return
     */
    public String className(){
        return CLASS_NAME;
    }

    public Racer clone(){
        return new Car(this.getName(),this.getMaxSpeed(),this.getAcceleration(),this.getColor(),wheeled.getNumOfWheels());
    }
}
