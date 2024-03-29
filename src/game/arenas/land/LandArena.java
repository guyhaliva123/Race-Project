package game.arenas.land;
import game.arenas.Arena;
import utilities.EnumContainer;

public class LandArena extends Arena {
    final static double DEFAULT_FRICTION = 0.5;
    final static int DEFAULT_MAX_RACERS = 8;
    final static int DEFAULT_LENGTH = 800;
    private EnumContainer.Coverage coverage =  EnumContainer.Coverage.GRASS;
    private EnumContainer.LandSurface surface =  EnumContainer.LandSurface.FLAT;

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
    public EnumContainer.Coverage getCoverage() {
        return coverage;
    }

    /**
     * @param coverage
     * @return
     */
    public boolean setCoverage(EnumContainer.Coverage coverage) {
        this.coverage = coverage;
        return true;
    }

    /**
     * @return
     */
    public EnumContainer.LandSurface getSurface() {
        return surface;
    }

    /**
     * @param surface
     * @return
     */
    public boolean setSurface(EnumContainer.LandSurface surface) {
        this.surface = surface;
        return true;
    }

    /**
     * @param length
     * @param maxRacers
     */
    //Land Arena builder.
    public LandArena(double length , int maxRacers) {
        super(length, maxRacers, getDefaultFriction());
    }
    //Default Land Arena builder.
    public LandArena(){
        this(getDefaultLength(),getDefaultMaxRacers());
    }

}
