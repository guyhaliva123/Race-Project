package game.racers;

public class Wheeled{
    private int numOfWheels;

    /**
     * @param numOfWheels
     */
    public Wheeled(int numOfWheels){
        this.numOfWheels = numOfWheels;
    }
    public Wheeled(){
        this(0);
    }

    public int getNumOfWheels() {
        return numOfWheels;
    }

    /**
     * @param numOfWheels
     * @return
     */
    public boolean setNumOfWheels(int numOfWheels) {
        if (numOfWheels >= 0) {
            this.numOfWheels = numOfWheels;
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    public String describeSpecific() {
        return "numOfWheels=" + numOfWheels;
    }
}
