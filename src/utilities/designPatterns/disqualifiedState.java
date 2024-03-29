package utilities.designPatterns;

import game.racers.Racer;

public class disqualifiedState implements racerState{
    @Override
    public void alert(Racer racer) {
        racer.getArena().getDisqualifiedRacers().add(racer);
        racer.getArena().getActiveRacers().remove(racer);
        System.out.println("***** Racer "+racer.getName()+" is disqualified. *****");
    }
    public String getRacerState(){
        return "Disqualified";
    }
}
