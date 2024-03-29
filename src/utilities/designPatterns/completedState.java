package utilities.designPatterns;

import game.racers.Racer;

public class completedState implements racerState{
    @Override
    public void alert(Racer racer) {
        racer.getArena().crossFinishLine(racer);
        System.out.println("Racer "+racer.getName()+" has completed the race.");
        System.out.print("details of the completed racer: ");
        racer.introduce();
    }
    public String getRacerState(){
        return "Completed";
    }
}
