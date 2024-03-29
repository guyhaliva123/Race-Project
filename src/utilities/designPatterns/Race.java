package utilities.designPatterns;

import game.arenas.Arena;
import game.racers.Racer;
import java.util.ArrayList;
public class Race {
    private ArrayList<Racer> racerArrayList;
    private Arena arena;
    public ArrayList<Racer> getRacerArrayList() {
        return racerArrayList;
    }

    public void setRacerArrayList(ArrayList<Racer> racerArrayList) {
        this.racerArrayList = racerArrayList;
    }

    public Arena getArena() {
        return arena;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

}
