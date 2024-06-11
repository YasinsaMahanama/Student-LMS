package com.devstack.lms.controller;

import com.devstack.lms.db.DatabaseAccessCode;
import com.devstack.lms.entity.Course;
import com.devstack.lms.view.tm.CourseTM;
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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CourseFormController {

    public TextField txtCourseName;
    public TextField txtCourseFee;
    public Button btnSave;
    public TextField txtSearch;
    public TableView<CourseTM> tblCourse;
    public TableColumn<CourseTM, String> colCourseName;
    public TableColumn<CourseTM, Double> colCourseFee;
    public TableColumn<CourseTM, ButtonBar> colOption;
    public AnchorPane context;

    private String searchText = "";
    private Course selectedCourse = null ;

    public void initialize() {
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colCourseFee.setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        colCourseFee.setCellFactory(column -> new TableCell<CourseTM, Double>() {
            private final DecimalFormat format = new DecimalFormat("0.00");

            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(format.format(item));
                }
            }
        });
        colOption.setCellValueFactory(new PropertyValueFactory<>("buttonBar"));

        loadAllCourse();

        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            searchText = newValue;
            loadAllCourse();
        });
    }

    public void btnNewCourseOnAction(ActionEvent actionEvent) {
        btnSave.setText("Save Course");
        clearFields();
        selectedCourse = null;
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("DashboardForm");
    }

    private void loadAllCourse() {

        ObservableList<CourseTM> tmObservableList = FXCollections.observableArrayList();

        try {
            DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();

            List<Course> allCourse = databaseAccessCode.findAllCourse(searchText);

            for (Course course : allCourse) {
                ButtonBar bar = new ButtonBar();
                Button deleteButton = new Button("Delete");
                Button updateButton = new Button("Update");
                bar.getButtons().addAll(deleteButton, updateButton);

                CourseTM courseTM = new CourseTM(
                        course.getCourseId(),
                        course.getCourseName(),
                        course.getCourseFee(),
                        bar
                );

                deleteButton.setOnAction(event -> {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.YES, ButtonType.NO);

                    Optional<ButtonType> buttonType = alert.showAndWait();
                    if (buttonType.get() == ButtonType.YES) {
                        try {
                            DatabaseAccessCode dbAccessCode = new DatabaseAccessCode();
                            boolean isDeleted = dbAccessCode.deleteCourse(courseTM.getCourseId());

                            if (isDeleted) {
                                new Alert(Alert.AlertType.INFORMATION, "Course deleted successfully").show();
                                loadAllCourse();
                            } else {
                                new Alert(Alert.AlertType.WARNING, "Something Went Wrong and try again.").show();
                            }

                        } catch (SQLException | ClassNotFoundException e) {
                            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                        }
                    }
                });

                updateButton.setOnAction(event -> {
                    btnSave.setText("Update Course");

                    selectedCourse = course;

                    txtCourseName.setText(course.getCourseName());
                    txtCourseFee.setText(String.valueOf(course.getCourseFee()));
                });

                tmObservableList.add(courseTM);
            }
            tblCourse.setItems(tmObservableList);

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
        }
    }

    public void saveCourseOnAction(ActionEvent actionEvent) {
        if(btnSave.getText().equalsIgnoreCase("Save Course")) {
            try {
                Course course = new Course(
                        UUID.randomUUID().toString(),
                        txtCourseName.getText().toLowerCase().trim(),
                        Double.parseDouble(txtCourseFee.getText())
                );
                DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
                boolean isSaved = databaseAccessCode.saveCourse(course);

                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Course has been Saved!", ButtonType.CLOSE).show();
                    clearFields();
                    loadAllCourse();
                }
                else {
                    new Alert(Alert.AlertType.WARNING, "Try Again.", ButtonType.CLOSE).show();
                }
            }catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
            }
        }
        else {
            if(selectedCourse != null){
                try {
                    Course course = new Course(
                            selectedCourse.getCourseId(),
                            txtCourseName.getText().trim(),
                            Double.parseDouble(txtCourseFee.getText())
                    );
                    DatabaseAccessCode databaseAccessCode = new DatabaseAccessCode();
                    boolean isSaved = databaseAccessCode.updateCourse(course);

                    if (isSaved) {
                        new Alert(Alert.AlertType.INFORMATION, "Course has been Updated!", ButtonType.CLOSE).show();
                        clearFields();
                        loadAllCourse();
                        btnSave.setText("Save Course");
                    }
                    else {
                        new Alert(Alert.AlertType.WARNING, "Try Again.", ButtonType.CLOSE).show();
                    }
                }catch (SQLException | ClassNotFoundException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).show();
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Please Select a Course", ButtonType.CLOSE).show();
            }
        }
    }

    private void clearFields() {
        txtCourseName.clear();
        txtCourseFee.clear();
    }

    public void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene((new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml")))));
        stage.setTitle(location);
        stage.centerOnScreen();
    }
}
