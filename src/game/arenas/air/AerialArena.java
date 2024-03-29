package game.arenas.air;
import game.arenas.Arena;
import utilities.EnumContainer;


public class AerialArena extends Arena{
    final static double DEFAULT_FRICTION = 0.4;
    final static int DEFAULT_MAX_RACERS = 6;
    final static int DEFAULT_LENGTH = 1500;
    private EnumContainer.Vision vision = EnumContainer.Vision.SUNNY;
    private EnumContainer.Weather weather = EnumContainer.Weather.DRY;
    private EnumContainer.Height height = EnumContainer.Height.HIGH;
    private EnumContainer.Wind wind = EnumContainer.Wind.HIGH;

    /**
     * @return
     */
    public static double getDefaultFriction() {
        return DEFAULT_FRICTION;
    }

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
    public EnumContainer.Vision getVision() {
        return vision;
    }

    /**
     * @param vision
     * @return
     */
    public boolean setVision(EnumContainer.Vision vision) {
        this.vision = vision;
        return true;
    }

    /**
     * @return
     */
    public EnumContainer.Weather getWeather() {
        return weather;
    }

    /**
     * @param weather
     * @return
     */
    public boolean setWeather(EnumContainer.Weather weather) {
        this.weather = weather;
        return true;
    }

    /**
     * @return
     */
    public EnumContainer.Height getHeight() {
        return height;
    }

    /**
     * @param height
     * @return
     */
    public boolean setHeight(EnumContainer.Height height) {
        this.height = height;
        return true;
    }

    /**
     * @return
     */
    public EnumContainer.Wind getWind() {
        return wind;
    }

    /**
     * @param wind
     * @return
     */
    public boolean setWind(EnumContainer.Wind wind) {
        this.wind = wind;
        return true;
    }

    /**
     * @param length
     * @param maxRacers
     */
    //Aerial Arena builder.
    public AerialArena(double length , int maxRacers){
        super(length,maxRacers,getDefaultFriction());
    }
    //Default Aerial Arena builder.
    public AerialArena(){
         this(getDefaultLength(),getDefaultMaxRacers());
    }

}
