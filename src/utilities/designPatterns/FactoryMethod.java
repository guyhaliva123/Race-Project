package utilities.designPatterns;

import game.arenas.Arena;
import game.arenas.air.AerialArena;
import game.arenas.land.LandArena;
import game.arenas.naval.NavalArena;

public class FactoryMethod {

    public Arena getFactoryArena(String arenaType,int N){

        Arena factoryArena = null;

        if (arenaType == "Land")
            factoryArena = new LandArena(LandArena.getDefaultLength(),N);
        else if(arenaType == "Naval")
            factoryArena = new NavalArena(NavalArena.getDefaultLength(),N);
        else
            factoryArena = new AerialArena(AerialArena.getDefaultLength(),N);

        return factoryArena;
    }
}
