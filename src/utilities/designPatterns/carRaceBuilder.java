package utilities.designPatterns;

import game.arenas.exceptions.RacerLimitException;
import game.arenas.exceptions.RacerTypeException;
import game.racers.Racer;
import game.racers.land.Car;
import utilities.EnumContainer;

import java.util.ArrayList;
import java.util.Random;

public class carRaceBuilder implements CarRaceBuilderInterface {
    private FactoryMethod arenaFactory = new FactoryMethod();
    private ArrayList<Racer> MyRacers;
    private Race race;
    private int NumOfRacers;

    public carRaceBuilder(int num){
        this.NumOfRacers=num;
        this.MyRacers = new ArrayList<>();
        this.race = new Race();
    }

    //using FactoryMethod DP.
    @Override
    public void buildArena(){
        race.setArena(arenaFactory.getFactoryArena("Land",this.NumOfRacers));
    }

    //using ProtoType DP.
    @Override
    public void buildRacersList() throws RacerTypeException, RacerLimitException{
        //create instance of random to rand numbers 1-5 so that the racers will be created in different colors.
        Random rnd = new Random();

        //create racer to followed by copied racers.
        Car carRacer = new Car("guy",200,25, EnumContainer.Color.BLACK,4);

        //add the racer to the racers list.
        MyRacers.add(carRacer);

        //fill the racer list randomly following the racer who is already created.
        //using ProtoType DP to clone the car racer.
        //and choose a color by random number for every new car racer.
        for (int i = 0 ; i < NumOfRacers-1 ; i++){

            int temp = rnd.nextInt(5)+1;

            if (temp == 1){
                Racer copiedRacer = carRacer.clone();
                copiedRacer.upgrade(EnumContainer.Color.RED);
                MyRacers.add(copiedRacer);
            }
            else if (temp == 2){
                Racer copiedRacer = carRacer.clone();
                copiedRacer.upgrade(EnumContainer.Color.GREEN);
                MyRacers.add(copiedRacer);
            }
            else if (temp == 3){
                Racer copiedRacer = carRacer.clone();
                copiedRacer.upgrade(EnumContainer.Color.BLUE);
                MyRacers.add(copiedRacer);
            }
            else if (temp == 4){
                Racer copiedRacer = carRacer.clone();
                copiedRacer.upgrade(EnumContainer.Color.BLACK);
                MyRacers.add(copiedRacer);
            }
            else{
                Racer copiedRacer = carRacer.clone();
                copiedRacer.upgrade(EnumContainer.Color.YELLOW);
                MyRacers.add(copiedRacer);
            }
        }
        //add the copied racers list to the arena
        for (int i = 0; i < NumOfRacers;i++)
            race.getArena().addRacer(MyRacers.get(i));

        //build parts
        race.setRacerArrayList(MyRacers);
        race.getArena().initRace();

    }

    //return the final product of the Builder DP
    @Override
    public Race buildRace(){
        return this.race;
    }
}
