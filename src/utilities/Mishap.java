package utilities;

public class Mishap {
    private boolean fixable;
    private double reductionFactor;
    private int turnsToFix;

    /**
     * @param fixable
     * @param turnsToFix
     * @param reductionFactor
     */
    public Mishap(boolean fixable,int turnsToFix , double reductionFactor) {
        setFixable(fixable);
        setTurnsToFix(turnsToFix);
        setReductionFactor(reductionFactor);
    }

    /**
     *
     */
    public void nextTurn()
    {
        if(isFixable())
            setTurnsToFix(getTurnsToFix()-1);
    }

    /**
     * @return
     */
    public boolean isFixable() {
        return fixable;
    }

    /**
     * @param fixable
     * @return
     */
    public boolean setFixable(boolean fixable) {
        this.fixable = fixable;
        return fixable;
    }

    /**
     * @return
     */
    public double getReductionFactor() {
        return reductionFactor;
    }

    /**
     * @param reductionFactor
     * @return
     */
    public boolean setReductionFactor(double reductionFactor) {
        if (reductionFactor >= 0)
        {
            this.reductionFactor = reductionFactor;
            return true;
        }
        else return false;

    }

    /**
     * @return
     */
    public int getTurnsToFix() {
        return turnsToFix;
    }

    /**
     * @param turnsToFix
     * @return
     */
    public boolean setTurnsToFix(int turnsToFix) {
        if (turnsToFix>=0)
        {
            this.turnsToFix = turnsToFix;
            return true;
        }
        else return false;
    }

    /**
     * @return 
     */
    @Override
    public String toString() {
        return String.format("(%b,%d,%.2f)",fixable,turnsToFix,reductionFactor);
    }
}
