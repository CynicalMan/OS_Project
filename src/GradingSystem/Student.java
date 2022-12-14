package src.GradingSystem;



enum Major {
    IT,
    CS,
    IS
}


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
    private Major major;
//    private Minor minor;

    //setters
    public void setPassword(String password) {
            this.password = password;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMajor(Major major) {
        this.major = major;
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

    public Major getMajor() {
        return major;
    }

    public String getPassword() {
        return password;
    }

    public int getSubjectGrade(RegisteredSubject reSub){
        return reSub.getGrade();
    }
}
