package game.arenas.exceptions;

public class RacerTypeException extends Exception {
    /**
     * @param className
     * @param arenaName
     */
    //Exception for wrong match between racer and type of Arena(override of addRacer).
    public RacerTypeException(String className , String arenaName) {
        super("Invaild Racer of type " + className + " for " + arenaName);
    }
}
