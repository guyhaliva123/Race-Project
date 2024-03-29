package game.racers.land;

import game.racers.Racer;
import utilities.EnumContainer.Breed;
import utilities.EnumContainer.Color;


public class Horse extends Racer implements LandRacer {
    static final String CLASS_NAME = "Horse";
    static final double DEFAULT_MAX_SPEED = 50;
    static final double DEFAULT_ACCELERATION = 3;
    static final Color DEFAULT_color = Color.BLACK;
    private Breed breed = Breed.THOROUGHBRED;

    /**
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     */
    public Horse(String name, double maxSpeed, double acceleration, Color color){
        super(name, maxSpeed, acceleration, color);
    }
    public Horse(){
        this(CLASS_NAME+" #"+getSerial(),DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
    }

    /**
     * @return
     */
    public Breed getBreed() {
        return breed;
    }

    /**
     * @param breed
     * @return
     */
    public boolean setBreed(Breed breed) {
        this.breed = breed;
        return true;
    }

    /**
     * @return
     */
    @Override
    public String describeSpecific() {
        return "Breed"+this.getBreed();
    }

    /**
     * @return
     */
    public String className(){
        return CLASS_NAME;
    }

    public Racer clone(){
        return new Horse(this.getName(),this.getMaxSpeed(),this.getAcceleration(),this.getColor());
    }
}
