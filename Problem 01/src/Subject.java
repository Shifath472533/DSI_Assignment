public class Subject {
    private String name;
    private boolean taken;
    private double earnings;
    private int taughtDays;

    public Subject(String name) {
        this.name = name;
        this.taken = false;
        this.earnings = 0.0;
        this.taughtDays = 0;
    }

    public Subject(boolean taken, int earnings) {
        this.taken = taken;
        this.earnings = earnings;
    }

    public Subject(String[] data){
        this.name = data[0];
        this.taken = Boolean.parseBoolean(data[1]);
        this.earnings = Double.parseDouble(data[2]);
        this.taughtDays = Integer.parseInt(data[3]);
    }

    public boolean isTaken() {
        return this.taken;
    }

    public String getName() {
        return name;
    }

    public int getTaughtDays() {
        return taughtDays;
    }

    public void setTaughtDays(int taughtDays) {
        this.taughtDays = taughtDays;
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

    public String getString(){
        String subjectInfo = "";
        subjectInfo = subjectInfo.concat(name);
        subjectInfo = taken ? subjectInfo.concat("\ttrue") : subjectInfo.concat("\tfalse");
        subjectInfo = subjectInfo.concat("\t" + Double.toString(earnings));
        subjectInfo = subjectInfo.concat("\t" + Integer.toString(taughtDays));
        return subjectInfo;
    }
    public void load(){
//        String[] data = "";

    }
}
