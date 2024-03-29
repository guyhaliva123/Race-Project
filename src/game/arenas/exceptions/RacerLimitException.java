package game.arenas.exceptions;

public class RacerLimitException extends Exception {
    /**
     * @param maxRacers
     * @param serialNumber
     */
    //Exception if the arena has reached is max racers limit.
    public RacerLimitException(int maxRacers , int serialNumber)
    {
        super("Arena is full! ("+maxRacers+" active racers exist) "+serialNumber+" was not added.");
    }
}
