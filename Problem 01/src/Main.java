import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // class -> 8, 9, 10
        Students[] classes = {new Students("class8"), new Students("class9"), new Students("class10")};
        for(int i=0; i<3; i++) classes[i].load();
        Scanner scanner = new Scanner(System.in);
        while(true){
            printMenu1();
            int option = getOption(5);
            if(option == 0) break;
            switch (option) {
                case 1 -> {
                    int className = getClassName();
                    if(className == 0) break;
                    int id = getId(classes[className-1]);
                    String name = getName(true);
                    boolean[] subjects = new boolean[3];
                    System.out.println("What subjects do you teach? Reply with 'y' or 'n' (without quotes)");
                    String reply;
                    String[] subNames = {"Math", "English", "Bangla"};
                    for (int i = 0; i < 3; i++) {
                        System.out.println("Do you teach \"" + subNames[i] + "\" ?");
                        reply = scanner.next();
                        while (!reply.equals("y") && !reply.equals("n")) {
                            System.out.println("Please reply with 'y' or 'n");
                            System.out.println("Do you teach \"" + subNames[i] + "\" ?");
                            reply = scanner.next();
                        }
                        subjects[i] = reply.equals("y");
                    }
                    classes[className-1].addStudent(id, name, subjects);
                }
                case 2 -> {
                    int className = getClassName();
                    if(className == 0) break;
                    int id = getId();
                    while (!checkId(id, classes[className-1])){
                        System.out.println("Please Enter a valid id that exists!! or press 0 to go to previous menu");
                        id = getId(false);
                        if(id == 0) break;
                    }
                    if(id == 0) break;
                    String name = getName(true);
                    while (!checkName(name, classes[className-1])){
                        System.out.println("Please Enter a name that exists!! or press 0 to go to previous menu");
                        name = getName(false);
                        if(name.equals("0")) break;
                    }
                    if(name.equals("0")) break;
                    int subOption = getEditOption();
                    if (subOption == 0) break;
                    int subName = getSubject();
                    if(subName == 0) break;
                    while (subOption!=1 && subOption!=2) subOption = scanner.nextInt();
                    switch (subOption){
                        case 1 -> {
                            int days = getDays();
                            classes[className-1].editStudent(id, name, days, subName);
                        }
                        case 2 -> {
                            double marks = getMarks();
                            classes[className-1].editStudent(id, name, marks);
                        }
                    }
                }
                case 3 -> {
                    int className = getClassName();
                    int id = getId();
                    String name = getName(true);
                    classes[className-1].deleteStudent(id, name);
                }

                case 4 -> {
                    int className = getClassName();
                    if(classes[className-1].getStudents().size() == 0){
                        System.out.println();
                        System.out.println("No student found in this class.");
                        System.out.println();
                        break;
                    }
                    TableGenerator tableGenerator = new TableGenerator();
                    ArrayList<String> headersList = new ArrayList<>();
                    headersList.add("Id");
                    headersList.add("Name");
                    headersList.add("Total Earnings");
                    headersList.add("Average Marks");
                    ArrayList<ArrayList<String>> rowsList = classes[className-1].getStudentsInfo();
                    System.out.println(tableGenerator.generateTable(headersList, Collections.unmodifiableList(rowsList)));
                    while (true){
                        System.out.println("Enter a valid Id to see details of that student. Press 0 to see previous Menu");
                        int id = getId();
                        if(id == 0) break;
                        if(checkId(id, classes[className-1])) {
                            classes[className-1].printStudentDetails(id);
                        }
                    }
                }
                case 5 -> {
                    printMenu5();
                    int opt = getOption(6);
                    switch (opt){
                        case 1 -> System.out.println((classes[0].getTotalDays()
                                +classes[1].getTotalDays()
                                +classes[2].getTotalDays()) + " days");
                        case 2 -> {
                            System.out.println("Class 8: " + classes[0].getTotalDays() + " days");
                            System.out.println("Class 9: " + classes[1].getTotalDays() + " days");
                            System.out.println("Class 10: " + classes[1].getTotalDays() + " days");
                        }
                        case 3 -> System.out.println("BDT " + (classes[0].getTotalEarnings()
                                +classes[1].getTotalEarnings()
                                +classes[2].getTotalEarnings()) + " Tk.");
                        case 4 -> {
                            System.out.println("Class 8: BDT " + classes[0].getTotalEarnings() + "  Tk.");
                            System.out.println("Class 9: BDT " + classes[1].getTotalEarnings() + "  Tk.");
                            System.out.println("Class 10: BDT " + classes[1].getTotalEarnings() + "  Tk.");
                        }
                        case 5 -> {
                            double mathEarning = classes[0].getEarnings(0) + classes[1].getEarnings(0) + classes[2].getEarnings(0);
                            double englishEarning = classes[0].getEarnings(1) + classes[1].getEarnings(1) + classes[2].getEarnings(1);
                            double banglaEarning = classes[0].getEarnings(2) + classes[1].getEarnings(2) + classes[2].getEarnings(2);
                            System.out.println("Earnings in Math :  BDT " + mathEarning + "  Tk.");
                            System.out.println("Earnings in English :  BDT " + englishEarning + "  Tk.");
                            System.out.println("Earnings in bangla :  BDT " + banglaEarning + "  Tk.");
                        }
                        case 6 -> {
                            double totalMarks = classes[0].getTotalMarks() + classes[1].getTotalMarks() + classes[2].getTotalMarks();
                            double noOfExams = classes[0].getNoOfExams() + classes[1].getNoOfExams() + classes[2].getNoOfExams();
                            double avgMarks = Double.isNaN(totalMarks/noOfExams) ? 0.0 : (totalMarks/noOfExams);
                            System.out.println("Average Marks : " + Math.max(avgMarks, 0.0));
                        }
                    }
                }
            }
        }
    }

    public static void printMenu1(){
        System.out.println("Please Choose any of the options below to continue by pressing numbers between 1 and 5");
        System.out.println("1. Add a student");
        System.out.println("2. Edit a student");
        System.out.println("3. Delete a student");
        System.out.println("4. See the list of students individually");
        System.out.println("5. See overall info");

        System.out.println("Enter 0 to exit.");
    }

    public static void printMenu5(){
        System.out.println("Please Choose any of the options below to continue by pressing numbers between 1 and 6");
        System.out.println("1. The total days taught across all classes");
        System.out.println("2. Individual days taught in each class");
        System.out.println("3. Total earnings");
        System.out.println("4. Individual earnings of each class");
        System.out.println("5. Individual earnings of each subject");
        System.out.println("6. Average marks of all students");
        System.out.println("Enter 0 to exit.");
    }

    public static int getOption(int max){
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        boolean err = true;
        while (err){
            try{
                option = Integer.parseInt(scanner.nextLine());
                if( option<0 || option>max){
                    System.out.println("Please choose a valid option.");
                    continue;
                }
                err = false;
            }catch (NumberFormatException ex) {
                System.out.println("Please choose a valid option.");
            }
        }
        return option;
    }
    public static int getSubject(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please select any of the following Subjects. " +
                "Enter a number between 1 and 3 based on the options.");
        System.out.println("1. Math");
        System.out.println("2. English");
        System.out.println("3. bangla");
        System.out.println("Enter 0 to go to previous menu.");
        return getOption(3);
    }

    public static int getClassName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose any of the following classes. " +
                "Enter a number between 1 and 3 based on the options.");
        System.out.println("1. Class 8");
        System.out.println("2. Class 9");
        System.out.println("3. Class 10");
        System.out.println("Enter 0 to go to previous menu.");
        return getOption(3);
    }

    public static int getEditOption(){
        System.out.println("Choose any of the following options.");
        System.out.println("1. Add number of days taught.");
        System.out.println("2. Add marks in exam");
        System.out.println("Enter 0 to go to previous menu.");
        return getOption(2);
    }

    public static String getName(boolean print){
        Scanner scanner = new Scanner(System.in);
        if(print) System.out.println("Enter name of the student.");
        String name = "";
        boolean firstTime = true;
        while (!isValidName(name)){
            if(!firstTime) System.out.println("Enter a name with valid format.");
            firstTime = false;
            name = "";
            name = name.concat(scanner.nextLine());
            if(name.equals("0")) return name;
        }
        return name;
    }

    public static int getValidInt(){
        Scanner scanner = new Scanner(System.in);
        int value = -1;
        boolean err = true;
        while (err || value<0){
            try{
                value = Integer.parseInt(scanner.nextLine());
                if(value <0){
                    System.out.println("Please enter a valid value which is greater than or equal to 0.");
                }
                err = false;
            }catch (NumberFormatException ex) {
                System.out.println("Please enter a valid value which is greater than or equal to 0.");
            }
        }
        return value;
    }


    public static int getId(){
        System.out.println("Enter id of the student.");
        return getValidInt();
    }

    public static int getId(boolean print){
        if(print) System.out.println("Enter id of the student.");
        return getValidInt();
    }

    public static int getId(Students students){
        int id = getId();
        while (checkId(id, students)){
            System.out.println("This has been inserted already or less than 1. " +
                    "Please re-enter a valid id which has not been inserted yet.");
            id = getId(false);
        }
        return id;
    }


    public static int getDays(){
        System.out.println("Enter the number of days you have taught.");
        return getValidInt();
    }

    public static double getMarks(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Marks of the student.");
        double marks = 0.0;
        boolean err = true;
        while (err || marks<0.0 || marks>100.0){
            try{
                marks = Double.parseDouble(scanner.nextLine());
                err = false;
            }catch (NumberFormatException ex) {
                System.out.println("Please enter valid marks between 0 and 100.");
            }
        }
        return marks;
    }

    public static boolean checkName(String name, Students students){
        for(Student student: students.getStudents()){
            if(student.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static boolean checkId(int id, Students students){
        for(Student student: students.getStudents()){
            if(student.getId() == id && id > 0){
                return true;
            }
        }
        return false;
    }

    public static boolean isValidName(String name) {
        if(name.length() == 0) return false;
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }


}