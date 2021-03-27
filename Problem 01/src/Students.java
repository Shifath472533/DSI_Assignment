import java.util.ArrayList;

public class Students {
    private ArrayList<Student> students;
    private double totalEarnings;

    Students(){
        students = new ArrayList<>();
        totalEarnings = 0.0;
    }

    public void addStudent(int id, String name, boolean[] subjects){
        Student student = new Student(id, name);
        student.addSubjects(subjects);
        students.add(student);
    }
    public void editStudent(int id, String name, int days, int subName){
        for(Student student: students){
            if(student.getId() == id && student.getName().equals(name))
                student.addDays(days, subName);
        }
        this.totalEarnings += days;
    }

    public void editStudent(int id, String name, double marks){
        for(Student student: students){
            if(student.getId() == id && student.getName().equals(name))
                student.addMarks(marks);
        }
    }


    public void deleteStudent(int id, String name){
        boolean found = false;
        for(int i=0; i< students.size(); i++){
            Student student = students.get(i);
            if(student.getId() == id && student.getName().equals(name)){
                Student student1 = students.remove(i);
                found = true;
            }
        }
        if(!found) System.out.println("No student found with id " + id + "  and name " + name);
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<ArrayList<String>> getStudentsInfo() {
        ArrayList<ArrayList<String>> rowsList = new ArrayList<>();
        for (Student student : students) {
            ArrayList<String> row = new ArrayList<>();
            row.add(student.getId().toString());
            row.add(student.getName());
            row.add(student.getEarnings().toString());
            row.add(student.getAverageMarks());
            rowsList.add(row);
        }
        return rowsList;
    }

    public void printStudentDetails(int id){
        for(Student student: students){
            if(student.getId() == id){
                System.out.println("Id: " + student.getId());
                System.out.println("Name: " + student.getName());
                System.out.println("Taken Subjects: ");
                int i = 0;
                for(Subject sub: student.getSubjects()){
                    i++;
                    if(sub.isTaken()){
                        System.out.println(i + ". " + sub.getName());
                    }
                }
                System.out.println("Average Marks: " + student.getAverageMarks());
                System.out.println("Days taught: " + student.getDaysTaught());
                System.out.println("Total Earnings: BDT " + student.getEarnings() + " Tk.");
                System.out.println();
            }
        }
    }

    public int getTotalDays(){
        int total = 0;
        for(Student student: students){
            total += student.getDaysTaught();
        }
        return total;
    }

    public double getEarnings(int id){
        double earnings = 0.0;
        for(Student student: students){
            earnings += student.getSubjects()[id].getEarnings();
        }
        return earnings;
    }

    public double getTotalMarks(){
        double totalMarks = 0.0;
        for(Student student: students){
            totalMarks += student.getTotalMarks();
        }
        return totalMarks;
    }

    public double getNoOfExams(){
        double noOfExams = 0.0;
        for(Student student: students){
            noOfExams += student.getNoOfExams();
        }
        return noOfExams;
    }

}
