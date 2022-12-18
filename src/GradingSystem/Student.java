package GradingSystem;


//enum Minor {
//    IT,
//    CS,
//    IS
//}

import BinaryFileManager.ReaderManager;
import BinaryFileManager.WriterManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Student implements Login, Serializable, RManager {
    private double gpa;
    private String name;
    private int id;
    private String password;
    private int year;
    private String major;

    //Bin File Manager
    private final String studentFileName = "Student.bin";
    public static ArrayList<Student> Students = new ArrayList<Student>();

    public Student(){}

    public Student(double gpa, String name, int id, String password, int year, String major) {
        this.gpa = gpa;
        this.name = name;
        this.id = id;
        this.password = password;
        this.year = year;
        this.major = major;
    }

    //setters

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    //getters
    public int getYear() {
        return year;
    }

    public double getGpa() {
        return gpa;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }
    public String getPassword() {
        return password;
    }

    private Student getStudentById(int  Id) {
        for (int i = 0; i < Students.size(); i++) {
            if (Students.get(i).id == Id){
                return Students.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean login(int id, String password) {
        loadFromFile();
        Student stud = new Student();
        stud = stud.getStudentById(id);
        if (stud != null && Objects.equals(stud.getPassword(), password)){
            return true;
        }
        return false;
    }

    public ArrayList<Student> listStudents() {
        loadFromFile();
        return Students;
    }
    public void displayGrades(){
        ArrayList<RegisteredCourses> arr = new ArrayList<RegisteredCourses>();
        RegisteredCourses regCourses = new RegisteredCourses();
        arr = regCourses.returnStudentCourses(this);

        //display arr
        System.out.println("array :   ");
        System.out.println(arr);
        System.out.println("array end :   ");

    }

    public boolean addStudent(){
        loadFromFile();
        Students.add(this);
        return commitToFile();
    }

    @Override
    public void loadFromFile() {
        ReaderManager rm = new ReaderManager(studentFileName);
        Thread read = new Thread(rm);
        read.setName("threadRead to student");
        read.start();
        //wait till thread is dead
        while(read.isAlive()) {}
        if (rm.getRes() == null){
            System.out.println("Cant read from student file");
        }else{
            Students = (ArrayList<Student>) rm.getRes();
        }
    }

    public boolean commitToFile(){
        WriterManager wm = new WriterManager(studentFileName,Students);
        Thread write = new Thread(wm);
        write.setName("threadWrite to student");
        write.start();
        //wait till thread is dead
        while(write.isAlive()) {}
        return  wm.isWritten();
    }
    @Override
    public String toString() {
        return "\nID: " + id + " Name:" + name + "\n" + "Password: " + password +
                "\nGPA: " + gpa + " Year: " + year + " Major: " + major;
    }
}
