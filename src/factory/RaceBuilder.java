package factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import game.arenas.Arena;
import game.racers.Racer;
import utilities.EnumContainer;
 public class RaceBuilder {
    static RaceBuilder instance;
    private ClassLoader classLoader;
    private Class<?> classObject;
    private Constructor<?> constructor;

    private RaceBuilder(){}
    /**
     * @return
     */
    public static RaceBuilder getInstance() {
        if (instance == null)
            instance = new RaceBuilder();
        return instance;
    }

    /**
     * @param arenaType
     * @param length
     * @param maxRacers
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    /*using java reflection to dynamic load the matching builder of the (this) Arena.*/
    public Arena buildArena(String arenaType, double length, int maxRacers)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        classLoader = ClassLoader.getSystemClassLoader();
        classObject = classLoader.loadClass(arenaType);
        constructor = classObject.getConstructor(double.class, int.class);

        return (Arena) constructor.newInstance(length, maxRacers);

    }

    /**
     * @param racerType
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    //using java reflection to dynamic load the matching builder of the (this) Racer.
    public Racer buildRacer(String racerType, String name, double maxSpeed, double acceleration, EnumContainer.Color color)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        classLoader = ClassLoader.getSystemClassLoader();
        classObject = classLoader.loadClass(racerType);
        constructor = classObject.getConstructor(String.class, double.class, double.class, EnumContainer.Color.class);

        return (Racer) constructor.newInstance(name, maxSpeed, acceleration, color);
    }

    /**
     * @param racerType
     * @param name
     * @param maxSpeed
     * @param acceleration
     * @param color
     * @param numOfWheels
     * @return
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    //using java reflection to dynamic load the matching builder of the (this) Racer.
    public Racer buildWheeledRacer(String racerType, String name, double maxSpeed, double acceleration, EnumContainer.Color color, int numOfWheels)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        classLoader = ClassLoader.getSystemClassLoader();
        classObject = classLoader.loadClass(racerType);
        constructor = classObject.getConstructor(String.class,double.class,double.class,EnumContainer.Color.class,int.class);

        return (Racer) constructor.newInstance(name,maxSpeed,acceleration,color,numOfWheels);
    }
}