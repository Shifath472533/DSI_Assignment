public class Student {
    private int id;
    private String name;
    private static final String[] subNames = {"Math", "English", "Bangla"};
    // Math, English, Bangla
    private Subject[] subjects;
    private double totalMarks;
    private int noOfExams;
    private int daysTaught;
    private double earnings;

    Student(int id, String name){
        this.id = id;
        this.name = name;
        subjects = new Subject[3];
        for(int i=0;i<3;i++) subjects[i] = new Subject(subNames[i]);
        this.totalMarks = 0.0;
        this.noOfExams = 0;
        this.daysTaught = 0;
        this.earnings = 0.0;
    }

    public Student(String[] data){
        this.id = Integer.parseInt(data[0]);
        this.name = data[1];
        subjects = new Subject[3];
        try {
            for (int i = 2; i < 5; i++) subjects[i-2] = new Subject(data[i].split("\t"));
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Error occurred...");
            System.out.println(data.length);
            for(String s: data) System.out.println(s);
            System.out.println("END");
        }
        this.totalMarks = Double.parseDouble(data[5]);
        this.noOfExams = Integer.parseInt(data[6]);
        this.daysTaught = Integer.parseInt(data[7]);
        this.earnings = Double.parseDouble(data[8]);
    }

    public void addSubjects(boolean[] subjects){
        for(int i=0; i<3; i++) this.subjects[i].setTaken(subjects[i]);
    }

    public void addDays(int noOfDays, int subName){
        this.daysTaught += noOfDays;
        this.earnings += (double) noOfDays;
        Subject sub = this.subjects[subName-1];
        sub.setTaughtDays(sub.getTaughtDays() + noOfDays);
        sub.setEarnings(sub.getEarnings() + (double) noOfDays);
    }

    public void addMarks(double mark){
        this.totalMarks += mark;
        this.noOfExams++;
    }

    public Double getEarnings() {
        return earnings;
    }

    public String getName() {
        System.out.println(name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Subject getSubject(int position) {
        return subjects[position];
    }

    public void setSubject(int position, Subject value) {
        this.subjects[position] = value;
    }

    public String getAverageMarks() {
        if(noOfExams==0 || totalMarks==0) return "0.0";
        Double avgMarks = totalMarks / noOfExams;
        return avgMarks.toString();
    }

    public void setTotalMarks(double totalMarks) {
        this.totalMarks = totalMarks;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public int getNoOfExams() {
        return noOfExams;
    }

    public void setNoOfExams(int noOfExams) {
        this.noOfExams = noOfExams;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDaysTaught() {
        return daysTaught;
    }

    public void setDaysTaught(int daysTaught) {
        this.daysTaught = daysTaught;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public String getString(){
        String studentInfo = "";
        studentInfo = studentInfo.concat(Integer.toString(id) + "\t\t");
        studentInfo = studentInfo.concat(name + "\t\t");
        studentInfo = studentInfo.concat(subjects[0].getString() + "\t\t");
        studentInfo = studentInfo.concat(subjects[1].getString() + "\t\t");
        studentInfo = studentInfo.concat(subjects[2].getString() + "\t\t");
        studentInfo = studentInfo.concat(Double.toString(totalMarks) + "\t\t");
        studentInfo = studentInfo.concat(Integer.toString(noOfExams) + "\t\t");
        studentInfo = studentInfo.concat(Integer.toString(daysTaught) + "\t\t");
        studentInfo = studentInfo.concat(Double.toString(earnings));
        return studentInfo;
    }
}
