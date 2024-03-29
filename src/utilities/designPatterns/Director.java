package utilities.designPatterns;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;

public class Director {
    //instance of the interface
    private CarRaceBuilderInterface builder;
    //initialize the Director with the interface instance.
    public Director(CarRaceBuilderInterface Builder){
        this.builder = Builder;
    }
    public Race getRace(){
        return builder.buildRace();
    }
    public void buildRace()throws RacerTypeException,RacerLimitException{
        builder.buildArena();
        builder.buildRacersList();
    }
}
