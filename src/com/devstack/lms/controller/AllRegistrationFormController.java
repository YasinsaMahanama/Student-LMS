package com.devstack.lms.controller;

import com.devstack.lms.db.DatabaseAccessCode;
import com.devstack.lms.entity.AllRegistration;
import com.devstack.lms.entity.Course;
import com.devstack.lms.utill.PaymentType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AllRegistrationFormController {

    public ComboBox<String> cmbCourse;
    public TableView<AllRegistration> tblAllRegistration;
    public TableColumn<AllRegistration, Date> colDate;
    public TableColumn<AllRegistration, String> colStudentName;
    public TableColumn<AllRegistration, PaymentType> colPaymentType;
    public AnchorPane context;

    private Course selectedCourse;

    public void initialize() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStudentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        colPaymentType.setCellValueFactory(new PropertyValueFactory<>("paymentType"));
        loadAllCourse();

        cmbCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setCourseDetails((String) newValue);
            }
        });
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

            List<AllRegistration> registrations = databaseAccessCode.loadAllDetails(courseId);
            ObservableList<AllRegistration> tmObservableList = FXCollections.observableArrayList(registrations);

            if (tmObservableList.isEmpty()) {
                new Alert(Alert.AlertType.WARNING, "Students are not registered for this course", ButtonType.OK).show();
                tblAllRegistration.setItems(FXCollections.observableArrayList());
            }

            tblAllRegistration.setItems(tmObservableList);

        }catch (Exception e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }

    private void clearFields(){

        cmbCourse.setValue(null);

        selectedCourse = null;
    }
}
