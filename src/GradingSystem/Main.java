package GradingSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

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

//        Lecturer l = new Lecturer();
//        l.displayStudents();
//
//        Lecturer l = new Lecturer();
//        System.out.println(l.login(1,"12345678"));
//        l.displayStudents();
//        Lecturer lecturer1 = new Lecturer(2,"Dr. Mano","12345678");
//        lecturer1.addLecturer();
//        Lecturer l = new Lecturer();
//        ArrayList<Lecturer> arr = l.listLecturers();
//        for (int i = 0; i < arr.size(); i++) {
//            System.out.println(arr.get(i).toString());
//        }
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