package GradingSystem;

public class Lecturer {
    private String name;
    private String password;

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void updateGrade(int grade,RegisteredSubject reSub) {
       reSub.setGrade(grade);
    }


}
