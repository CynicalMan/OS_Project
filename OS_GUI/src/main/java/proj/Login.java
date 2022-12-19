package proj;

import proj.GradingSystem.Student;
import proj.GradingSystem.Lecturer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;


public class Login {

    public Login() {

    }

    @FXML
    private Button resetBtn;
    @FXML
    private Button loginBtn;
    @FXML
    private Label wrongLogin;
    @FXML
    private Label testL;
    @FXML
    private TextField id;
    @FXML
    private PasswordField pass;

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {
        Main m = new Main();
        Student s = new Student();
        Lecturer lect = new Lecturer();
        if(s.login(Integer.parseInt(id.getText()),pass.getText())) {
            wrongLogin.setText("Success!");
            m.changeScene("Student.fxml");
        } else if (lect.login(Integer.parseInt(id.getText()),pass.getText())) {
            wrongLogin.setText("Success!");
            m.changeScene("Lecturer.fxml");
        }else if(id.getText().isEmpty() && pass.getText().isEmpty()) {
            wrongLogin.setText("Please enter your data.");
        }else {
            wrongLogin.setText("Wrong username or password!");
        }
    }
    public void reset(ActionEvent event) throws IOException {
        wrongLogin.setText("");
        id.setText("");
        pass.setText("");
    }

}
