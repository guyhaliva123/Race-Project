package utilities.designPatterns;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;

public interface CarRaceBuilderInterface {
    public void buildRacersList() throws RacerTypeException,RacerLimitException;
    public void buildArena();
    public Race buildRace();
}
