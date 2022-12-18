package GradingSystem;

import BinaryFileManager.Manager;
import BinaryFileManager.ReaderManager;
import BinaryFileManager.WriterManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Lecturer implements Login, Serializable ,RManager{
    private int id;
    private String name;
    private String password;

    //Bin File Manager
    private final String lecturerFileName = "Lecturer.bin";
    public static ArrayList<Lecturer> Lecturers = new ArrayList<Lecturer>();

    public Lecturer(){}
    public Lecturer(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    private Lecturer getLecturerById(int  Id) {
        for (int i = 0; i < Lecturers.size(); i++) {
            if (Lecturers.get(i).id == Id){
                return Lecturers.get(i);
            }
        }
        return null;
    }

    public ArrayList<Lecturer> listLecturers() {
        loadFromFile();
        return Lecturers;
    }
    public void displayStudents(int id){
        ArrayList<RegisteredCourses> arr = new ArrayList<RegisteredCourses>();
        RegisteredCourses regCourses = new RegisteredCourses();
        arr = regCourses.returnStudents(this.getLecturerById(id));

        //display arr
        System.out.println("array :   ");
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i).toString());
        }
        System.out.println("array end :   ");
    }

    public void searchStudentGrade(int stId){
        RegisteredCourses regCourses = new RegisteredCourses();
        ArrayList<RegisteredCourses> rg = new ArrayList<RegisteredCourses>();
        rg = regCourses.returnStudent(stId);

        //display arr
        System.out.println("name :   ");
        System.out.println(rg.get(0).getStud().getName() + "\n");
        for (int i = 0; i < rg.size(); i++) {
            System.out.println(rg.get(i).getGrade());
        }
        System.out.println("grade end :   ");
    }

    public void updateStudentGrade(int stId, int newGrade){
        RegisteredCourses regCourses = new RegisteredCourses();
        ArrayList<RegisteredCourses> rg = new ArrayList<RegisteredCourses>();
        rg = regCourses.returnStudent(stId);

        //display arr
        System.out.println("name :   ");
        System.out.println(rg.get(0).getStud().getName() + "\n");
        for (int i = 0; i < rg.size(); i++) {
            System.out.println(rg.get(i).getGrade());
        }
        System.out.println("grade end :   ");
    }

    @Override
    public boolean login(int id, String password) {
        loadFromFile();
        Lecturer lect = new Lecturer();
        lect = lect.getLecturerById(id);
        return lect != null && Objects.equals(lect.getPassword(), password);
    }

    public boolean addLecturer(){
        loadFromFile();
        Lecturers.add(this);
        return commitToFile();
    }

    public boolean commitToFile(){
        WriterManager wm = new WriterManager(lecturerFileName,Lecturers);
        Thread write = new Thread(wm);
        write.setName("threadWrite to lecturer");
        write.start();
        //wait till thread is dead
        while(write.isAlive()) {}
        return  wm.isWritten();
    }

    @Override
    public void loadFromFile() {
        ReaderManager rm = new ReaderManager(lecturerFileName);
        Thread read = new Thread(rm);
        read.setName("threadRead to lecturer");
        read.start();
        //wait till thread is dead
        while(read.isAlive()) {}
        if (rm.getRes() == null){
            System.out.println("Cant read from lecturer file");
        }else{
            Lecturers = (ArrayList<Lecturer>) rm.getRes();
        }
    }
    @Override
    public String toString() {
        return "\nID: " + id + " Name:" + name + "\n" + "Password: " + password;
    }
}
