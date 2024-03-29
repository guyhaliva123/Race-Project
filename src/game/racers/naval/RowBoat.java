package game.racers.naval;

import game.racers.Racer;
import utilities.EnumContainer.BoatType;
import utilities.EnumContainer.Team;
import utilities.EnumContainer.Color;

public class RowBoat extends Racer implements NavalRacer {
    static final String CLASS_NAME = "RowBoat";
    static final double DEFAULT_MAX_SPEED = 75;
    static final double DEFAULT_ACCELERATION = 10;
    static final Color DEFAULT_color = Color.RED;
    private BoatType type = BoatType.SKULLING;
    private Team team = Team.DOUBLE;

    /**
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     */
    public RowBoat(String name, double maxSpeed, double acceleration, Color color){
        super(name, maxSpeed, acceleration, color);
    }
    public RowBoat(){
        this(CLASS_NAME+" #"+getSerial(),DEFAULT_MAX_SPEED,DEFAULT_ACCELERATION,DEFAULT_color);
    }

    /**
     * @return
     */
    public BoatType getType() {
        return type;
    }

    /**
     * @param type
     * @return
     */
    public boolean setType(BoatType type) {
        this.type = type;
        return true;
    }

    /**
     * @return
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team
     * @return
     */
    public boolean setTeam(Team team) {
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
        return new RowBoat(this.getName(),this.getMaxSpeed(),this.getAcceleration(),this.getColor());
    }
}
