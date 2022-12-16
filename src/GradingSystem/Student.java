package GradingSystem;


//enum Minor {
//    IT,
//    CS,
//    IS
//}

public class Student {


    private double gpa;
    private String name;
    private int id;
    private String password;
    private int year;
    private String major;
//    private Minor minor;

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

    public int getSubjectGrade(RegisteredCoursse reSub){
        return reSub.getGrade();
    }
}
