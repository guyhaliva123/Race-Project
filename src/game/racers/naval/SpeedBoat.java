package game.racers.naval;

import game.racers.Racer;
import utilities.EnumContainer;

public class SpeedBoat extends Racer implements NavalRacer{
    static final String CLASS_NAME = "SpeedBoat";
    static final double DEFAULT_MAX_SPEED = 170;
    static final double DEFAULT_ACCELERATION = 5;
    static final EnumContainer.Color DEFAULT_color = EnumContainer.Color.RED;
    private EnumContainer.BoatType type = EnumContainer.BoatType.SKULLING;
    private EnumContainer.Team team = EnumContainer.Team.DOUBLE;

    /**
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     */
    public SpeedBoat(String name, double maxSpeed, double acceleration, EnumContainer.Color color){
        super(name, maxSpeed, acceleration, color);
    }
    public SpeedBoat(){
        this(CLASS_NAME+" #"+getSerial(),DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
    }

    /**
     * @return
     */
    public EnumContainer.BoatType getType() {
        return type;
    }

    /**
     * @param type
     * @return
     */
    public boolean setType(EnumContainer.BoatType type) {
        this.type = type;
        return true;
    }

    /**
     * @return
     */
    public EnumContainer.Team getTeam() {
        return team;
    }

    /**
     * @param team
     * @return
     */
    public boolean setTeam(EnumContainer.Team team) {
        this.team = team;
        return true;
    }

    /**
     * @return
     */
    @Override
    public String describeSpecific() {
        return "Boat Type:"+this.getType()+", Team Type:"+this.getTeam();
    }

    /**
     * @return
     */
    @Override
    public String className() {
        return CLASS_NAME;
    }

    public Racer clone(){
        return new SpeedBoat(this.getName(),this.getMaxSpeed(),this.getAcceleration(),this.getColor());
    }
}
