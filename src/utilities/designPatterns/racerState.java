package utilities.designPatterns;

import game.racers.Racer;
public interface racerState {
    public void alert(Racer racer);
    public String getRacerState();
}
