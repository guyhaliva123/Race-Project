package utilities.designPatterns;

import game.racers.Racer;

import java.util.ArrayList;

public class brokenState implements racerState{
    @Override
    public void alert(Racer racer) {
        racer.getArena().getBrokenRacers().add(racer);
        racer.getArena().getActiveRacers().remove(racer);
        racer.getArena().setBrokenRacers(mergeSort(racer.getArena().getBrokenRacers()));
    }

    public static ArrayList<Racer> mergeSort(ArrayList<Racer> racers) {
        if (racers.size() <= 1) {
            return racers;
        }

        int mid = racers.size() / 2;
        ArrayList<Racer> leftList = new ArrayList<>(racers.subList(0, mid));
        ArrayList<Racer> rightList = new ArrayList<>(racers.subList(mid, racers.size()));

        leftList = mergeSort(leftList);
        rightList = mergeSort(rightList);

        return merge(leftList, rightList);
    }

    public static ArrayList<Racer> merge(ArrayList<Racer> leftList, ArrayList<Racer> rightList) {
        ArrayList<Racer> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < leftList.size() && j < rightList.size()) {
            Racer leftRacer = leftList.get(i);
            Racer rightRacer = rightList.get(j);

            if (leftRacer.getCurrentLocation().getX() >= rightRacer.getCurrentLocation().getX()) {
                result.add(leftRacer);
                i++;
            } else {
                result.add(rightRacer);
                j++;
            }
        }

        while (i < leftList.size()) {
            result.add(leftList.get(i));
            i++;
        }

        while (j < rightList.size()) {
            result.add(rightList.get(j));
            j++;
        }

        return result;
    }
    public String getRacerState(){
        return "Broken";
    }
}
