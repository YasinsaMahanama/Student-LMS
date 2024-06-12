package com.devstack.lms.controller;

import com.devstack.lms.db.DatabaseAccessCode;
import com.devstack.lms.entity.Course;
import com.devstack.lms.entity.Registration;
import com.devstack.lms.entity.Student;
import com.devstack.lms.utill.PaymentType;
import com.devstack.lms.view.tm.CourseTM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RegistrationFormController {
    public AnchorPane context;
    public TextField txtStudentName;
    public TextField txtCourseName;
    public TextField txtStudentEmail;
    public TextField txtCourseFee;
    public Button btnSave;
    public ComboBox<String> cmbCourse;
    public ComboBox<String> cmbStudent;
    public RadioButton rBtnCash;
    public ToggleGroup paymentType;

    private Student selectedStudent;
    private Course selectedCourse;

    public void initialize() {
        loadAllCourse();
        loadAllStudents();

        cmbCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                setCourseDetails((String) newValue);
            }
        });

        cmbStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null) {
                setStudentDetails((String) newValue);
            }
        });
    }

    private void setStudentDetails(String newValue) {
        String[] splitData = newValue.split("\\|");
        String studentId = splitData[0].trim();

        try{
            DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
            selectedStudent = databaseAccessCode.findStudent(studentId);

            if(selectedStudent == null) {
                new Alert(Alert.AlertType.WARNING, "Student Not Found", ButtonType.OK).show();
                return;
            }

            txtStudentName.setText(selectedStudent.getStudentName());
            txtStudentEmail.setText(selectedStudent.getEmail());

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllStudents() {
        ObservableList<String> studentObservableList = null;

        try{

            DatabaseAccessCode db = new DatabaseAccessCode();
            studentObservableList = FXCollections.observableArrayList(db.findAllStudents().stream().map(e->e.getStudentId()+" | "+e.getStudentName()).collect(Collectors.toList()));
            cmbStudent.setItems(studentObservableList);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCourseDetails(String newValue) {
        String[] splitData = newValue.split("\\|");
        String courseId = splitData[0].trim();

        try{
            DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
            selectedCourse = databaseAccessCode.findCourse(courseId);

            if(selectedCourse == null) {
                new Alert(Alert.AlertType.WARNING, "Course Not Found", ButtonType.OK).show();
                return;
            }

            txtCourseName.setText(selectedCourse.getCourseName());
            txtCourseFee.setText(String.valueOf(selectedCourse.getCourseFee()));

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void loadAllCourse() {

        ObservableList<String> courseObservableList = null;

        try{

            DatabaseAccessCode db = new DatabaseAccessCode();
            courseObservableList = FXCollections.observableArrayList(db.findAllCourse().stream().map(e->e.getCourseId()+" | "+e.getCourseName()).collect(Collectors.toList()));
            cmbCourse.setItems(courseObservableList);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void registerNowOnAction(ActionEvent actionEvent) {
         if(selectedCourse==null || selectedCourse == null){
             new Alert(Alert.AlertType.WARNING, "Please return to home and come back.... or try after choose potential data", ButtonType.OK).show();
             return;
         }


        try {
            Registration registration = new Registration(
                    UUID.randomUUID().toString(),
                    new Date(),
                    null,
                    rBtnCash.isSelected()? PaymentType.CASH:PaymentType.CARD,
                    selectedStudent.getStudentId(),
                    selectedCourse.getCourseId()
            );
            DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
            boolean isSaved = databaseAccessCode.registerCourse(registration);

            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Student has been Saved!", ButtonType.CLOSE).show();
                clearFields();
            }
            else {
                new Alert(Alert.AlertType.WARNING, "Try Again.", ButtonType.CLOSE).show();
            }
        }catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }

    private void clearFields(){
        txtStudentName.clear();
        txtCourseName.clear();
        txtStudentEmail.clear();
        txtCourseFee.clear();
        cmbCourse.setValue(null);
        cmbStudent.setValue(null);

        selectedCourse = null;
        selectedStudent = null;
    }
}
