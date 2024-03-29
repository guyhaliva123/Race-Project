package game.arenas.naval;
import game.arenas.Arena;
import utilities.EnumContainer;

public class NavalArena extends Arena {
    final static double DEFAULT_FRICTION = 0.7;
    final static int DEFAULT_MAX_RACERS = 5;
    final static int DEFAULT_LENGTH = 1000;
    private EnumContainer.Water water =  EnumContainer.Water.SWEET;
    private EnumContainer.WaterSurface surface =  EnumContainer.WaterSurface.FLAT;
    private EnumContainer.Body body = EnumContainer.Body.LAKE;

    /**
     * @return
     */
    public static double getDefaultFriction(){return DEFAULT_FRICTION;}

    /**
     * @return
     */
    public static int getDefaultMaxRacers() {
        return DEFAULT_MAX_RACERS;
    }

    /**
     * @return
     */
    public static int getDefaultLength() {
        return DEFAULT_LENGTH;
    }

    /**
     * @return
     */
    public EnumContainer.Water getWater() {
        return water;
    }

    /**
     * @param water
     * @return
     */
    public boolean setWater(EnumContainer.Water water) {
        this.water = water;
        return true;
    }

    /**
     * @return
     */
    public EnumContainer.WaterSurface getSurface() {
        return surface;
    }

    /**
     * @param surface
     * @return
     */
    public boolean setSurface(EnumContainer.WaterSurface surface) {
        this.surface = surface;
        return true;
    }

    /**
     * @return
     */
    public EnumContainer.Body getBody() {
        return body;
    }

    /**
     * @param body
     * @return
     */
    public boolean setBody(EnumContainer.Body body) {
        this.body = body;
        return true;
    }

    /**
     * @param length
     * @param maxRacers
     */
    //Naval Arena builder.
    public NavalArena(double length , int maxRacers)
    {
        super(length,maxRacers,getDefaultFriction());
    }
    //Default Naval Arena builder.
    public NavalArena(){
        this(getDefaultLength(),getDefaultMaxRacers());
    }

}
