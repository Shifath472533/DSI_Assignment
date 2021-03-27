public class Subject {
    private String name;
    private boolean taken;
    private double earnings;
    private int taughDays;

    public Subject(String name) {
        this.name = name;
        this.taken = false;
        this.earnings = 0.0;
        this.taughDays = 0;
    }

    public Subject(boolean taken, int earnings) {
        this.taken = taken;
        this.earnings = earnings;
    }

    public boolean isTaken() {
        return this.taken;
    }

    public String getName() {
        return name;
    }

    public int getTaughDays() {
        return taughDays;
    }

    public void setTaughDays(int taughDays) {
        this.taughDays = taughDays;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }
}
