package proj.GradingSystem;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " + projectPath);
        File currentDir = new File(projectPath);
        checkDirectoryContents(currentDir);

        Lecturer lecturer1 = new Lecturer(1,"Dr. Ahmed","12345678");
        lecturer1.addLecturer();
        Course c1 = new Course(1,"OS2",lecturer1, 3);
        c1.addCourse();
        Student student1 = new Student(2.8, "Youssef", 1, "abcdefgh", 3, "CS");
        student1.addStudent();
        Student student2 = new Student(3, "Mohammed", 2, "qwertyui", 3, "IS");
        student2.addStudent();
        Student student3 = new Student(3.2, "Saaed", 3, "asdfghjk", 3, "IT");
        student3.addStudent();
        Student student4 = new Student(3.4, "Amr", 4, "zxcvbnmm", 3, "CS");
        student4.addStudent();
        Student student5 = new Student(3.6, "Omar", 5, "poiuytre", 3, "IS");
        student5.addStudent();
        RegisteredCourses rg1 = new RegisteredCourses(1, 77, student1, c1);
        rg1.addRegisteredCourse();
        RegisteredCourses rg2 = new RegisteredCourses(2,80,student2,c1);
        rg2.addRegisteredCourse();
        RegisteredCourses rg3 = new RegisteredCourses(3,86,student3,c1);
        rg3.addRegisteredCourse();
        RegisteredCourses rg4 = new RegisteredCourses(4,88,student4,c1);
        rg4.addRegisteredCourse();
        RegisteredCourses rg5 = new RegisteredCourses(5,91,student5,c1);
        rg5.addRegisteredCourse();



        //CONSOLE APPLICATION



//        Scanner choice = new Scanner(System.in);
//        System.out.println("Enter (1) If student.\n" +
//                "Enter (2) If Lecturer");
//        int ch = choice.nextInt();
//
//        Scanner idUser = new Scanner(System.in);
//        System.out.println("Please enter your ID: ");
//        int id = idUser.nextInt();
//
//        Scanner passUser = new Scanner(System.in);
//        System.out.println("Please enter your Password: ");
//        String password = passUser.nextLine();
//
//        if (ch == 1){
//            Student s = new Student();
//
//            if (s.login(id,password)){
//                System.out.println("\nLogin Successfully as a Student");
//                int x = 1;
//                while(x==1){
//                    System.out.println("Enter (1) To view your grades.\n" +
//                            "Enter (0) To exit.");
//                    Scanner option = new Scanner(System.in);
//                    int op = option.nextInt();
//                    if (op == 1){
//                        s.displayGrades(id);
//                    }
//                    else if (op == 0){
//                        x = 0;
//                        System.out.println("\nExit Successfully");
//                    }
//                }
//            }
//            else{
//                System.out.println("Invalid ID or Password. Exiting...");
//            }
//        }
//        else if (ch == 2){
//            Lecturer l = new Lecturer();
//
//            if (l.login(id,password)){
//                System.out.println("\nLogin Successfully as a Lecturer");
//                int x = 1;
//                while(x==1){
//                    System.out.println("Enter (1) To Display all students enrolled in your course.\n" +
//                            "Enter (2) To search for a student.\n" +
//                            "Enter (3) To update a student grade.\n" +
//                            "Enter (0) To exit.");
//                    Scanner option = new Scanner(System.in);
//                    int op = option.nextInt();
//                    if (op == 1){
//                        l.displayStudents(id);
//                    }
//                    else if (op == 2){
//                        Scanner stID = new Scanner(System.in);
//                        System.out.println("\nPlease enter student ID: ");
//                        int studentID = stID.nextInt();
//                        l.searchStudentGrade(studentID);
//                    }
//                    else if (op == 3){
//                        Scanner stID = new Scanner(System.in);
//                        System.out.println("\nPlease enter student ID: ");
//                        int studentID = stID.nextInt();
//
//                        Scanner newGrade = new Scanner(System.in);
//                        System.out.println("\nPlease enter the student's new grade: ");
//                        int grade = newGrade.nextInt();
//
//                        l.updateStudentGrade(studentID, grade);
//
//                    }
//                    else if (op == 0){
//                        x = 0;
//                        System.out.println("\nExit Successfully");
//                    }
//
//                }
//            }
//            else{
//                System.out.println("Invalid ID or Password. Exiting...");
//            }
//        }



        //CONSOLE APPLICATION




//
//        Lecturer l = new Lecturer();
//        l.displayStudents(1);

//        Student s = new Student();
//        s.displayGrades(1);
//        System.out.println();
//        s.displayGrades(2);
//        System.out.println();
//        s.displayGrades(3);
//        System.out.println();
//        s.displayGrades(4);
//        System.out.println();
//        s.displayGrades(5);

//        Lecturer l = new Lecturer();
//        System.out.println(l.login(1,"12345678"));
//        l.displayStudents();
//        Lecturer lecturer1 = new Lecturer(2,"Dr. Mano","12345678");
//        lecturer1.addLecturer();
//        Lecturer l = new Lecturer();
//        ArrayList<Lecturer> arr = l.listLecturers();

//        RegisteredCourses s = new RegisteredCourses();
//        ArrayList<RegisteredCourses> arr = s.listRegisteredCourses();
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i).toString());
//        }
//        Lecturer l = new Lecturer();
//        l.updateStudentGrade(2,100);
//        l.searchStudentGrade(2);
    }

    public static void checkDirectoryContents(File dir) {
        File[] files = dir.listFiles();
        boolean studentFile = true;
        boolean lecturerFile = true;
        boolean courseFile = true;
        boolean registeredCourseFile = true;

        for (File file : files) {

            if (file.getName().contains("Student.bin")) {
                studentFile = false;
            }
            else if (file.getName().contains("Lecturer.bin")) {
                lecturerFile = false;
            }
            else if (file.getName().contains("Course.bin")) {
                courseFile = false;
            }
            else if (file.getName().contains("RegisteredCourses.bin")) {
                registeredCourseFile = false;
            }
        }


        if (studentFile)
        {
            Student x = new Student();
            x.commitToFile();
        }
        if (lecturerFile) {
            Lecturer x = new Lecturer();
            x.commitToFile();
        }
        if (courseFile) {
            Course x = new Course();
            x.commitToFile();
        }
        if (registeredCourseFile) {
            RegisteredCourses x = new RegisteredCourses();
            x.commitToFile();
        }
    }



}