package com.devstack.lms.view.tm;

import javafx.scene.control.ButtonBar;

public class CourseTM {
    private String courseId;
    private String courseName;
    private double courseFee;
    private ButtonBar buttonBar;

    public CourseTM() {
    }

    public CourseTM(String courseId, String courseName, double courseFee, ButtonBar buttonBar) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseFee = courseFee;
        this.buttonBar = buttonBar;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public ButtonBar getButtonBar() {
        return buttonBar;
    }

    public void setButtonBar(ButtonBar buttonBar) {
        this.buttonBar = buttonBar;
    }
}
