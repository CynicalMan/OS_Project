package proj.GradingSystem;


//enum Minor {
//    IT,
//    CS,
//    IS
//}

import proj.BinaryFileManager.ReaderManager;
import proj.BinaryFileManager.WriterManager;

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

    public Student getStudentById(int  Id) {
        loadFromFile();
        for (int i = 0; i < Students.size(); i++) {
            if (Students.get(i).getId() == Id){
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
    public void displayGrades(int id){
        ArrayList<RegisteredCourses> arr = new ArrayList<RegisteredCourses>();
        RegisteredCourses regCourses = new RegisteredCourses();
        arr = regCourses.returnStudentCourses(this.getStudentById(id));

        //display arr
        System.out.println();
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).getStud().getName() + " " + arr.get(i).getSub().getName() + " " + arr.get(i).getGrade());
        }
        System.out.println();

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
        return "\nID: " + id + " | Name:" + name + "\n" + "GPA: " + gpa + " | Year: " + year + " | Major: " + major;
    }
}
