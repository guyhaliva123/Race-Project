package game.arenas;

import game.arenas.air.AerialArena;
import game.arenas.land.LandArena;
import game.arenas.naval.NavalArena;
import game.racers.Racer;
import java.util.ArrayList;
import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.air.AerialRacer;
import game.racers.land.LandRacer;
import game.racers.naval.NavalRacer;
import utilities.Observer;
import utilities.Point;


public abstract class Arena implements Observer {
    private ArrayList<Racer> activeRacers ;
    private ArrayList<Racer> brokenRacers;
    private ArrayList<Racer> disqualifiedRacers;
    private ArrayList<Racer> completedRacers;
    private final double FRICTION;
    private final int MAX_RACERS;
    final static int MIN_Y_GAP = 70;
    private double length;

    /**
     * @param length
     * @param maxRacers
     * @param friction
     */
    //Arena builder to set up values as user wants.
    public Arena(double length, int maxRacers,double friction) {
        this.setLength(length);
        this.MAX_RACERS = maxRacers;
        this.FRICTION = friction;
        this.activeRacers = new ArrayList<>();
        this.brokenRacers = new ArrayList<>();
        this.disqualifiedRacers = new ArrayList<>();
        this.completedRacers = new ArrayList<>();
    }
    //default Arena builder.
    public Arena(){
        this(0,0,0);
    }

    /**
     * @return
     */
    public double getLength() {
        return length;
    }

    /**
     * @param length
     * @return
     */
    public boolean setLength(double length) {
        this.length = length;
        return true;
    }

    /**
     * @return
     */
    public ArrayList<Racer> getActiveRacers() {
        return activeRacers;
    }
    public void setActiveRacers(ArrayList<Racer> activeRacers) {
        this.activeRacers = activeRacers;
    }

    /**
     * @return
     */
    public ArrayList<Racer> getCompletedRacers() {
        return completedRacers;
    }
    public void setCompletedRacers(ArrayList<Racer> completedRacers) {
        this.completedRacers = completedRacers;
    }

    public ArrayList<Racer> getBrokenRacers() {
        return brokenRacers;
    }

    public void setBrokenRacers(ArrayList<Racer> brokenRacers) {
        this.brokenRacers = brokenRacers;
    }

    public ArrayList<Racer> getDisqualifiedRacers() {
        return disqualifiedRacers;
    }

    public void setDisqualifiedRacers(ArrayList<Racer> disqualifiedRacers) {
        this.disqualifiedRacers = disqualifiedRacers;
    }

    /**
     * @return
     */
    public double getFRICTION() {
        return FRICTION;
    }

    /**
     * @return
     */
    public int getMAX_RACERS() {
        return MAX_RACERS;
    }

    /**
     * @param newRacer
     * @throws RacerTypeException
     * @throws RacerLimitException
     */
    //function that adds new racer to active racers array under the condition that the max racers limit is not at is limit
    //and that the new racer is instance of the matching Arena.
    public void addRacer(Racer newRacer) throws RacerTypeException, RacerLimitException {
       if(this instanceof AerialArena){
           if(!(newRacer instanceof AerialRacer)){
               throw new RacerTypeException(newRacer.className(), "Aerial Arena");
           }
       }
       else if(this instanceof LandArena){
            if(!(newRacer instanceof LandRacer)) {
                throw new RacerTypeException(newRacer.className(), "Land Arena");
            }
        }
       else if(this instanceof NavalArena){
            if(!(newRacer instanceof NavalRacer)) {
                throw new RacerTypeException(newRacer.className(), "Aerial Arena");
            }
        }
       if (this.getActiveRacers().size()+1>this.getMAX_RACERS())
           throw new RacerLimitException(this.getMAX_RACERS(), newRacer.getSerial_number());
       this.getActiveRacers().add(newRacer);
       //register listener.
       newRacer.registerObserver(this);

    }

    /**
     *
     */
    //function to init new race and set up the racers in their start position.
    public void initRace(){
        int gap = 0;
        for (int i = 0;i < this.activeRacers.size();i++){
            this.activeRacers.get(i).initRace(this,new Point(0,gap),new Point(this.getLength(),gap));
            gap+= MIN_Y_GAP;
        }
    }

    /**
     * @return
     */
    public boolean hasActiveRacers()
    {
        if(activeRacers.size() == 0)
        {
            return false;
        }else
            return true;
    }

    /**
     *
     */
    //function to update the location of every racer after his turn.
    public void playTurn(){
            for (int i = 0; i < this.activeRacers.size();i++){
                Point point = new Point(this.activeRacers.get(i).move(getFRICTION()));
                this.activeRacers.get(i).setCurrentLocation(point);
                if (this.activeRacers.get(i).getCurrentLocation().getX()>=getLength()){
                    crossFinishLine(this.activeRacers.get(i));
                    i--;
                }
            }
    }

    /**
     * @param racer
     */
    //function to add the racer to the completed racers array after he crosses the finish line.
    public void crossFinishLine(Racer racer){
        this.getCompletedRacers().add(racer);
        this.getActiveRacers().remove(racer);
    }
    public boolean hasBrokenRacers(){
        if(brokenRacers.size() == 0)
        {
            return false;
        }else
            return true;
    }

    /**
     *
     */
    //function to show the results of the race.
    public void showResults(){
        for (int i =0 ; i < this.completedRacers.size();i++){
            System.out.print("#"+i+" ->");
            this.completedRacers.get(i).introduce();
        }

    }
    //override to update function from observer interface
    @Override
    public synchronized void update(Object obj) {
        ((Racer)obj).getState().alert((Racer) obj);
        System.out.println("notify observer of "+((Racer) obj).getName());
    }
}
